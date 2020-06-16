package com.github.dev.muzi.kwafoo.config.platform.service.redis;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RedisOperation {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisOperation.class);

    private static final String TBL_PREFIX = "[TBL]";
    private static final String TL_PREFIX = "[TL]";
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 基于redis lua token 进行限流操作。
     *
     * @param token   用户请求身份标识
     * @param num     次数
     * @param seconds 秒数
     * @return true 允许继续请求  false超出限制
     */
    public boolean tokenIsLimitAllowed(String token, int num, int seconds) {
        // 执行 lua 脚本
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        // 指定 lua 脚本
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/token_limit.lua")));
        // 指定返回类型
        redisScript.setResultType(Long.class);
        // 参数一：lua限流脚本，参数二：token，参数三：num次数，参数四：seconds秒
        Long result = redisTemplate.execute(redisScript, Collections.singletonList(TL_PREFIX + token), num, seconds);
        // result 0 不允许  1 允许 null特殊情况判断
        return result != null && result > 0;
    }

    /**
     * 基于redis lua token bucket 进行限流操作。
     */
    public boolean tokenIsLimitAllowed(String tbName, int capacity, int quota, int period) {
        String key = TBL_PREFIX + tbName;
        TokenBucket tb = tokenBucketLimitAllow(key, capacity, quota, period, 1);
        LOGGER.info("\n\n {}", JSON.toJSONString(tb));
        return tb != null && tb.limit != null && tb.limit == 0;
    }

    /**
     * 基于redis lua token bucket 进行限流操作。
     * tbName       令牌桶名称
     * capacity     令牌桶容量
     * quota        时间窗口内限额
     * period       时间窗口 / 秒
     * quantity     单次获取令牌数
     */
    public TokenBucket tokenBucketLimitAllow(String tbName, int capacity, int quota, int period, int quantity) {
        DefaultRedisScript<Object> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/token_bucket_limit.lua")));
        redisScript.setResultType(Object.class);
        Object res = redisTemplate.execute(redisScript, Collections.singletonList(tbName), capacity, quota, period, quantity);
        List<Integer> list = JSON.parseObject(JSON.toJSONString(res),ArrayList.class);
        return new TokenBucket(list);
    }

    public static class TokenBucket {
        /*
         * 是否限流
         */
        private Integer limit;
        /*
         * 容量
         */
        private Integer capacity;
        /*
         * 余量
         */
        private Integer remain;

        public TokenBucket() {
        }

        public TokenBucket(List<Integer> list) {
            if (list == null || list.size() != 3) {
                throw new IllegalArgumentException("array is null");
            }
            this.limit = list.get(0);
            this.capacity = list.get(1);
            this.remain = list.get(2);
        }

        public Integer getLimit() {
            return limit;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        public Integer getCapacity() {
            return capacity;
        }

        public void setCapacity(Integer capacity) {
            this.capacity = capacity;
        }

        public Integer getRemain() {
            return remain;
        }

        public void setRemain(Integer remain) {
            this.remain = remain;
        }
    }
}
