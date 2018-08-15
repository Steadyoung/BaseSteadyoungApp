package com.steadyoung.app.baselibs;

/**
 * File description.
 *
 * @author steadyoung
 * @date 2018/8/15 16:34
 * @email steadyoung@foxmail.com
 */
public interface ApiGlobal {

    /**
     * 全局的超时时间:单位（毫秒）
     */
    int DEFAULT_MILLISECONDS = 15000;

    /**
     * 请求成功返回码
     */
    int SUCCESS_CODE = 0;


    /**
     * 登录过期返回码
     */
    int LOGIN_EXPIRED_CODE = 1000;



    String TOKEN_NAME = "token";
}
