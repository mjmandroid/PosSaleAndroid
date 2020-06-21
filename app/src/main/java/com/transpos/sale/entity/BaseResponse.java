package com.transpos.sale.entity;

public class BaseResponse {

    /**
     * 0成功  其他都是失败
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
