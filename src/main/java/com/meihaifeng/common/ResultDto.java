package com.meihaifeng.common;

import java.io.Serializable;

/**
 * 接口返回结果集
 *@result
 * */
public class ResultDto implements Serializable {

    private static final long serialVersionUID = 5576237395711742681L;

    /**
     * 大于0表示失败，0成功
     * */
    private Integer code = 0;

    private String msg = "";

    private Long time = DateUtils.getTimeSecondLong();

    private Object data = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}