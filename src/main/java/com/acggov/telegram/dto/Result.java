package com.acggov.telegram.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created: Nekoer
 * Desc: 结果集
 * Date: 2020/5/10 14:29
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result implements Serializable  {

    private int code;
    private Object msg;
    private Object data;
    private Object error;

    public Result(int code, Object msg, Object data, Object error) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.error = error;
    }

    public Result() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg=" + msg +
                ", data=" + data +
                ", error=" + error +
                '}';
    }
}
