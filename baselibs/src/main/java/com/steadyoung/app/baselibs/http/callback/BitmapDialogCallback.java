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
import android.graphics.Bitmap;

import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.request.base.Request;
import com.steadyoung.app.commonlibs.widget.dialog.LoadDialog;

/**
 * ================================================
 * 作    者：steadyoung
 * 版    本：1.0
 * 创建日期：2018/4/16
 * 描    述：请求图图片的时候显示对话框
 * 修订历史：
 * ================================================
 */
public abstract class BitmapDialogCallback extends BitmapCallback {

    private LoadDialog dialog;
    private Activity activity;

    public BitmapDialogCallback(Activity activity) {
        super(1000, 1000);
        this.activity = activity;
    }

    @Override
    public void onStart(Request<Bitmap, ? extends Request> request) {
        if(dialog == null){
            dialog = new LoadDialog(activity);
        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onFinish() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = null;
        activity = null;
    }
}
