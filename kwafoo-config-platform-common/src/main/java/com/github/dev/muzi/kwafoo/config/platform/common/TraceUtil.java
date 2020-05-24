package com.github.dev.muzi.kwafoo.config.platform.common;

/**
 * 【常用类】 记录请求的userId
 * Create by Muzi Li on 2019-09-12
 */
public final class TraceUtil {

    private TraceUtil(){}

    private static ThreadLocal<String> threadLocal
                = new ThreadLocal<>();

    public static void initThread(String userId){
        threadLocal.set(userId);
    }

    public static String getUserId(){
        return threadLocal.get();
    };
}
