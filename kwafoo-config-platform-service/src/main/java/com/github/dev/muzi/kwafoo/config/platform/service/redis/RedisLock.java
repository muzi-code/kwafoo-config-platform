package com.github.dev.muzi.kwafoo.config.platform.service.redis;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class RedisLock implements Lock {
    private static Logger LOGGER = LoggerFactory.getLogger(RedisLock.class);
    /**
     * 锁的前缀
     */
    private static final String LOCK_PREFIX = "[RL]";
    /**
     * 重试时间
     */
    private static final int DEFAULT_ACQUIRY_RETRY_MILLIS = 10;
    /**
     * 保存自己线程的UUID
     */
    private ThreadLocal<String> local = new ThreadLocal<>();

    private RedisLock() {
    }

    public RedisLock(String lockKey, RedisTemplate<String, Object> redisTemplate) {
        this.lockKey = LOCK_PREFIX + lockKey;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 锁的key
     */
    private String lockKey;
    /**
     * redis的操作对象
     */
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void lock() {
        if (tryLock()) {
            return;
        }
        try {
            Thread.sleep(DEFAULT_ACQUIRY_RETRY_MILLIS);
        } catch (InterruptedException e) {
            LOGGER.info("lock sleep failure", e);
        }
        lock();
    }

    @Override
    public boolean tryLock() {
        // 代表当前操作的线程
        String value = UUID.randomUUID().toString();

        // setNX 锁key
        Boolean ret = redisTemplate.opsForValue().setIfAbsent(lockKey, value, 3000, TimeUnit.MILLISECONDS);

        // 抢到了锁
        if (BooleanUtils.isTrue(ret)) {
            local.set(value);
            return true;
        }

        // 抢锁失败
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        // 执行 lua 脚本
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        // 指定 lua 脚本
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/atomic_del_key.lua")));
        // 指定返回类型
        redisScript.setResultType(Long.class);
        // 参数一：redisScript，参数二：key列表，参数三：arg（可多个）
        Long result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), local.get());
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new RuntimeException("Redis分布式锁不支持lockInterruptibly");
    }

    @Override
    public Condition newCondition() {
        throw new RuntimeException("Redis分布式锁不支持Condition");
    }
}
