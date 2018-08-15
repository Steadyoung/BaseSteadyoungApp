package com.steadyoung.app.baselibs.http;

import android.support.annotation.StringRes;

import com.steadyoung.app.baselibs.R;


/**
 * File description.
 *
 * @author steadyoung
 * @date 2018/8/8 09:56
 * @email steadyoung@foxmail.com
 */
public enum HttpErrorEnum {
    //1002 Header中userId和token不匹配（已失效）
    LOGIN_EXPIRED                               (1000 , R.string.login_expired_tip)

    ;

    /**
     * code
     */
    private int code;

    /**
     * 文字资源
     */
    @StringRes
    private int stringRes;

    HttpErrorEnum(int code,@StringRes int stringRes) {
        this.code = code;
        this.stringRes = stringRes;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStringRes() {
        return stringRes;
    }

    public void setStringRes(int stringRes) {
        this.stringRes = stringRes;
    }


    /**
     * 获取受益人类型
     * @param code
     * @return
     */
    public static HttpErrorEnum getHttpErrorEnum(int code) {
        for (HttpErrorEnum httpErrorEnum : HttpErrorEnum.values()) {
            if (httpErrorEnum.getCode() == code) {
                return httpErrorEnum;
            }
        }
        //没有获取到就返回空
        return null;
    }

    /**
     * 获取受益人类型文字资源
     * @param code
     * @return
     */
    @StringRes
    public static int getHttpErrorEnumStrRes(int code) {
        HttpErrorEnum accountStatus = getHttpErrorEnum(code);
        if(accountStatus != null){
            return accountStatus.getStringRes();
        }
        //没有获取到就返回空字符串
        return R.string.str_null;
    }

}
