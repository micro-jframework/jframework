package com.neatlife.jframework.exception;

/**
 * @author suxiaolin
 */
public enum StatusCode {
    /**
     * 异常枚举
     */
    UNKNOWN_ERR("未知错误", 0),
    USERNAME_OR_PASSWORD_ERR("用户名密码错误", 1),
    REANLNAME_OR_IDNUMBER_ERR("姓名身份证号错误", 2),
    SMS_CODE_ERR("验证码错误", 3),
    REDIS_ERR("Redis操作异常", 4),
    GENERAL_ERR("一般异常", 5),
    HTTP_POST_ERR("http post 请求异常", 6),
    HTTP_GET_ERR("http get 请求异常", 7);

    // 成员变量
    private String name;
    private Integer index;

    /**
     * 构造方法
     *
     * @param name
     * @param index
     * @return
     */
    StatusCode(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(Integer index) {
        for (StatusCode c : StatusCode.values()) {
            if (c.getIndex().equals(index)) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
