package com.steadyoung.app.baselibs.base;


import android.app.Activity;
import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/9.
 * Email : steadyoung@foxmail.com
 * Auth  : wayne
 * Desc  : BaseView
 */

public interface BaseView {
    /**
     * 显示等待加载框
     */
    void showWaitingDialog();

    /**
     * 隐藏等待加载框
     */
    void hideWaitingDialog();

}
