package com.github.dev.muzi.kwafoo.config.platform.domain.constant;

/**
 * 【枚举】 系统异常信息通用定义
 * Create by Muzi Li on 2019-12-09
 */
public enum CommonStatusConstant {

    // 成功
    SUCCESS(200, "业务执行成功"),

    // 失败
    FAILURE(500, "业务执行失败"),


    // 控制器通用异常
    CONTROLLER_COMMON_ERROR(50000001, "控制器处理错误"),

    CONTROLLER_ARGUMENTS_ERROR(50000002, "控制器请求参数错误"),

    SERVICE_COMMON_ERROR(50000003, "业务层处理失败"),

    SERVICE_ARGUMENTS_ERROR(50000004, "业务层请求参数错误"),

    SERVICE_TRANSACTION_ERROR(50000005, "业务层事务错误"),

    SERVICE_RPC_ERROR(50000006, "RPC接口未获取到数据"),

    SERVICE_MQ_ERROR(50000007, "MQ消息发送失败"),

    // 其他特殊异常
    USER_NO_LOGIN_ERROR(80000001, "用户需要登录"),

    OTHER_ERROR(80000002, "其他错误");

    private Integer code;
    private String msg;

    private CommonStatusConstant(Integer code, String msg) {
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
        for (CommonStatusConstant wrapperEnumError : CommonStatusConstant.values()) {
            if (code.equals(wrapperEnumError.getCode())) {
                return wrapperEnumError.getMsg();
            }
        }
        return OTHER_ERROR.getMsg();
    }

}
