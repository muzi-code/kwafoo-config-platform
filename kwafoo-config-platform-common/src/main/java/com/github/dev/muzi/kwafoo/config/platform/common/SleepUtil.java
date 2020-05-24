package com.github.dev.muzi.kwafoo.config.platform.common;

import java.util.concurrent.TimeUnit;

/**
 * 【常用类】线程休眠辅助
 *  Create by Muzi Li on 2019-09-12
 */
public class SleepUtil {
	
	/**
	 * 按秒休眠
	 * @param seconds 秒数
	 */
    public static final void second(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
    
    /**
     * 按毫秒数休眠
     * @param seconds 毫秒数
     */
    public static final void ms(int seconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}
