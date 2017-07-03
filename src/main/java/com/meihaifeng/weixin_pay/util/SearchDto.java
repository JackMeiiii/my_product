package com.meihaifeng.weixin_pay.util;

import com.meihaifeng.common.DateUtils;
import net.sf.json.JSONObject;

import java.io.Serializable;

/**
 *   检索调用 DTO
 *   search
 * */
public class SearchDto implements Serializable {

    private static final long serialVersionUID = 5576237395711742681L;

    private  Integer  pageNo = 1;

    /**
     * pageSize 10
     * */
    private Integer  pageSize = 10;

    /**
     * 检索 公共列
     * {
     *     "a":"a",
     *     "b":"b"
     *     }
     * */
    private JSONObject filed = new JSONObject() ;

    private Long appUserId;

    private Long time = DateUtils.getTimeSecondLong();

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public JSONObject getFiled() {
        return filed;
    }

    public void setFiled(JSONObject filed) {
        this.filed = filed;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }
}