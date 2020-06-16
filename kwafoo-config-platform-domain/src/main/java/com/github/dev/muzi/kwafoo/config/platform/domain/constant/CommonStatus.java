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

    ARGUMENTS(501, "请求参数异常"),

    TRANSACTION(502, "事务处理异常"),

    NO_LOGIN(801, "业务执行失败"),

    ERROR(802, "系统错误");

    private Integer code;
    private String msg;

    CommonStatus(Integer code, String msg) {
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
        return ERROR.getMsg();
    }

}
