package com.steadyoung.app.baselibs.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/12.
 * Email : steadyoung@foxmail.com
 * Auth  : wayne
 * Desc  : 网络请求返回结果
 */

public class HttpResult<T> implements Serializable {
    private static final long serialVersionUID = 8868070663112319719L;
    /**最新版本*/
    protected  double maxVersion;
    /**当前版本*/
    protected  double currentVersion;
    /**响应码*/
    protected int code;
    /**响应信息*/
    protected String msg;
    /**响应数据*/
    protected T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public double getMaxVersion() {
        return maxVersion;
    }

    public void setMaxVersion(double maxVersion) {
        this.maxVersion = maxVersion;
    }

    public double getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(double currentVersion) {
        this.currentVersion = currentVersion;
    }

    @Override
    public String toString() {
        return "HttpResult{\n" +
                "\tmaxVersion = " + maxVersion + "\n" +
                "\tcurrentVersion = " + currentVersion + "\n" +
                "\tcode = " + code + "\n" +
                "\tmsg = " + msg + "\n" +
                "\tdata = " + data + "\n" +
                '}';
    }
}
