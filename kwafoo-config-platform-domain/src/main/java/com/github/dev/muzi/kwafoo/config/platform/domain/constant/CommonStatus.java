package com.github.dev.muzi.kwafoo.config.platform.domain.constant;

/**
 * 【枚举】 系统异常信息通用定义
 * Create by Muzi Li on 2019-12-09
 */
public enum CommonStatus {

    // 成功
    SUCCESS(200, "业务执行成功"),

    // 失败
    FAILURE(500, "业务执行失败"),


    // 控制器通用异常
    PARAMETER_ERROR(501, "参数不合法"),

    PROCESS_ERROR(502, "处理失败"),

    MQ_ERROR(503, "消息处理失败"),

    RPC_ERROR(504, "RPC失败"),

    REDIS_ERROR(505, "缓存处理失败"),

    MYSQL_ERROR(506, "DB处理失败"),

    // 其他特殊异常
    USER_NO_LOGIN_ERROR(801, "用户需要登录"),

    OTHER_ERROR(802, "其他错误");

    private Integer code;
    private String msg;

    private CommonStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getResponseMsg(Integer code) {
        for (CommonStatus wrapperEnumError : CommonStatus.values()) {
            if (code.equals(wrapperEnumError.getCode())) {
                return wrapperEnumError.getMsg();
            }
        }
        return OTHER_ERROR.getMsg();
    }

}
