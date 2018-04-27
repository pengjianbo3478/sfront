package com.gl365.app.remote.member;

import java.io.Serializable;

/**
 * Created by wangmeihua on 2017/6/21.
 */
public class ResultDto<T> implements Serializable {
    private String result;
    private String description;

    /**
     * data : 结果数据
     */
    private T data;

    public ResultDto() {

    }
    public ResultDto(T data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
