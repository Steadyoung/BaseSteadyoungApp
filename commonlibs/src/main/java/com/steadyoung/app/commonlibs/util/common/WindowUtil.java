package com.steadyoung.app.commonlibs.util.common;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/9/6.
 * Email : steadyoung@foxmail.com
 * Auth  : wayne
 * Desc  : WindowUtil
 */
public class WindowUtil {

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getWindowWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕高度
     * @param context
     * @return
     */
    public static int getWindowHeight(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }



}
