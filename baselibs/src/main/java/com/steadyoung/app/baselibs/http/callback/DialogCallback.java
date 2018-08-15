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

import android.app.Activity;
import android.os.Handler;

import com.lzy.okgo.request.base.Request;
import com.steadyoung.app.commonlibs.widget.dialog.LoadDialog;

/**
 * ================================================
 * 作    者：steadyoung
 * 版    本：1.0
 * 创建日期：2016/1/14
 * 描    述：对于网络请求是否需要弹出进度对话框
 * 修订历史：
 * ================================================
 */
public abstract class DialogCallback<T> extends JsonCallback<T> {
    /**
     * 加载框
     */
    private LoadDialog loadDialog;
    private Activity mActivity;
    /**
     * 延迟弹出加载框时间 单位：毫秒
     */
    private long delayTime = 0;
    /**
     * 是否请求结束
     */
    private boolean isFinish = false;

    public DialogCallback(Activity activity) {
        super();
        this.mActivity = activity;
    }

    public DialogCallback(Activity activity,boolean hasToken) {
        super(hasToken);
        this.mActivity = activity;
    }

    /**
     *
     * @param activity
     * @param delayTime
     */
    public DialogCallback(Activity activity,boolean hasToken,long delayTime) {
        super(hasToken);
        this.mActivity = activity;
        this.delayTime = delayTime;
    }

    /**
     *
     * @param activity
     * @param delayTime
     */
    public DialogCallback(Activity activity,long delayTime) {
        super();
        this.mActivity = activity;
        this.delayTime = delayTime;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!isFinish){
                    showDialog();
                }
            }
        },delayTime);
    }

    /**
     * 显示加载框
     */
    private void showDialog() {
        if(loadDialog == null ){
            loadDialog = new LoadDialog(mActivity);
        }
        if (!loadDialog.isShowing()) {
            loadDialog.show();
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        isFinish = true;
        //网络请求结束后关闭对话框
        if (loadDialog != null && loadDialog.isShowing()) {
            loadDialog.dismiss();
        }
    }
    private int index;

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    /**
     * 清理
     */
    public void clear(){
        loadDialog = null;
        mActivity = null;
    }
}
