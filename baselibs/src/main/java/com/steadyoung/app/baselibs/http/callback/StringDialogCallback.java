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

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.base.Request;
import com.steadyoung.app.commonlibs.widget.dialog.LoadDialog;

/**
 * ================================================
 * 作    者：steadyoung
 * 版    本：1.0
 * 创建日期：2018/4/16
 * 描    述：
 * 修订历史：
 * ================================================
 */
public abstract class StringDialogCallback extends StringCallback {

    private LoadDialog loadDialog;
    private Activity activity;

    public StringDialogCallback(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        if(loadDialog == null){
            loadDialog = new LoadDialog(activity);
        }
        if (!loadDialog.isShowing()) {
            loadDialog.show();
        }
    }

    @Override
    public void onFinish() {
        if (loadDialog != null && loadDialog.isShowing()) {
            loadDialog.dismiss();
        }
        loadDialog = null;
        activity = null;
    }
}
