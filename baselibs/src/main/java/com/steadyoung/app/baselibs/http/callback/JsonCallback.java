/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.steadyoung.app.baselibs.http.callback;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;
import com.steadyoung.app.baselibs.ApiGlobal;
import com.steadyoung.app.baselibs.R;
import com.steadyoung.app.baselibs.bean.HttpResult;
import com.steadyoung.app.commonlibs.util.common.Convert;
import com.steadyoung.app.commonlibs.util.common.LogUtils;
import com.steadyoung.app.commonlibs.util.common.ToastUtils;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * ================================================
 * 作    者：steadyoung
 * 版    本：1.0
 * 创建日期：2018/4/16
 * 描    述：默认将返回的数据解析成需要的Bean,可以是 BaseBean，String，List，Map
 * 修订历史：
 * ================================================
 */
public abstract class
JsonCallback<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;
    /**
     * header中是否需要token和userId，默认是会传递的
     */
    private boolean hasToken = true;

    /**
     *
     * @param hasToken
     */
    public JsonCallback(boolean hasToken) {
        this.hasToken = hasToken;
    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public JsonCallback() {

    }


    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现
        
        //添加版本号和token 现在添加到TokenInterceptor 全局拦截器里面去了
//        request.headers(HttpService.VERSION_NAME, HttpService.VERSION_CODE);
        if(hasToken){
//            request.headers(HttpService.TOKEN_NAME, Cache.get().getAsString(Globals.TOKEN))
//                    .headers(HttpService.USER_ID, AppCache.getInstance().getUserId());
        }

    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public T convertResponse(Response response) throws Throwable {

        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用

        //详细自定义的原理和文档，看这里： https://github.com/jeasonlzy/okhttp-OkGo/wiki/JsonCallback

        if (type == null) {
            if (clazz == null) {
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                JsonConvert<T> convert = new JsonConvert<>(clazz);
                return convert.convertResponse(response);
            }
        }

        JsonConvert<T> convert = new JsonConvert<>(type);
        return convert.convertResponse(response);
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        httpError(response);
    }

    /**
     * 网络请求错误统一处理
     * @param response
     */
    private void httpError(com.lzy.okgo.model.Response response){
        if (response == null) {
            ToastUtils.showShort(R.string.net_error);
            return;
        }
        if (response.getException() != null){
            ToastUtils.showShort(response.getException().getMessage());
            response.getException().printStackTrace();
        }else{
            ToastUtils.showShort(R.string.net_error);
        }
//        responseLog(response);
    }

    @Override
    public void onSuccess(com.lzy.okgo.model.Response<T> response) {
        handleResponse(response);
    }

    private void handleResponse(com.lzy.okgo.model.Response<T> response) {
        try {
            if(response == null){
                ToastUtils.showShort(R.string.net_error_no_reponse);
                return;
            }

//            responseLog(response);
//            httpLogD(response.body());
            T body = response.body();
            if(body == null){
                ToastUtils.showShort(R.string.net_error_no_reponse);
                return;
            }
            onResponseSuccess(body);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShort(e.getMessage());
        }
//        responseBodyLog(response);
    }

    /**
     * 打印请求日志
     * @param response
     */
    private  void responseLog(com.lzy.okgo.model.Response response) {
        Call call = response.getRawCall();
        if (call != null) {
            httpLogD("请求成功  请求方式：" + call.request().method() + "\n" + "url：" + call.request().url());

            Headers requestHeadersString = call.request().headers();
            Set<String> requestNames = requestHeadersString.names();
            StringBuilder sb = new StringBuilder();
            for (String name : requestNames) {
                sb.append(name).append(" ： ").append(requestHeadersString.get(name)).append("\n");
            }
            httpLogD(sb.toString());
        } else {
            httpLogD("--");
            httpLogD("--");
        }

        Response rawResponse = response.getRawResponse();
        if (rawResponse != null) {
            Headers responseHeadersString = rawResponse.headers();
            Set<String> names = responseHeadersString.names();
            StringBuilder sb1 = new StringBuilder();
            sb1.append("stateCode ： ").append(rawResponse.code()).append("\n");
            for (String name : names) {
                sb1.append(name).append(" ： ").append(responseHeadersString.get(name)).append("\n");
            }
            LogUtils.e(sb1.toString());
        }
    }

    /**
     * 打印成功获取到的数据
     * @param response
     * @param <T>
     */
    private <T> void responseBodyLog(com.lzy.okgo.model.Response<T> response) {
        StringBuilder sb;
        T body = response.body();
        if (body == null) {
            httpLogD("--");
        } else {
            if (body instanceof String) {
                httpLogD((String) body);
            } else if (body instanceof List) {
                sb = new StringBuilder();
                List list = (List) body;
                for (Object obj : list) {
                    sb.append(obj.toString()).append("\n");
                }
                httpLogD(sb.toString());
            } else if (body instanceof Set) {
                sb = new StringBuilder();
                Set set = (Set) body;
                for (Object obj : set) {
                    sb.append(obj.toString()).append("\n");
                }
                httpLogD(sb.toString());
            } else if (body instanceof Map) {
                sb = new StringBuilder();
                Map map = (Map) body;
                Set keySet = map.keySet();
                for (Object key : keySet) {
                    sb.append(key.toString()).append(" ： ").append(map.get(key)).append("\n");
                }
                httpLogD(sb.toString());
            } else if (body instanceof File) {
                File file = (File) body;
                httpLogD("数据内容即为文件内容\n下载文件路径：" + file.getAbsolutePath());
            } else if (body instanceof Bitmap) {
                httpLogD("图片的内容即为数据");
            } else {
                httpLogD(Convert.formatJson(body));
            }
        }
    }

    /**
     * 判断是否请求成功
     * @param body
     * @return
     */
    protected boolean verifyBody(HttpResult body){
        if(body.getCode() != ApiGlobal.SUCCESS_CODE || body.getData() == null){
            if(TextUtils.isEmpty(body.getMsg())){
                ToastUtils.showLong(R.string.net_error);
            }else{
                ToastUtils.showLong(body.getMsg());
            }

            return false;
        }
        return true;
    }

    public abstract void onResponseSuccess(T body);

    /**
     * 打印网络日志
     * @param log
     */
    private void httpLogD(String log){
        Log.i("httpLogD",log);
    }
}
