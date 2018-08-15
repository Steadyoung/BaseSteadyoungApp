package com.steadyoung.app.commonlibs.util.common;

import android.support.annotation.StringRes;

/**
 * Created by Administrator on 2017/9/21.
 * Email : steadyoung@foxmail.com
 * Auth  : wayne
 * Desc  : String数据格式转换相关工具类
 */

public class StringUtil {
    /**保留两位小数的格式*/
    private final static String DEFAULT_FORMAT = "%.2f";
    private final static String DEFAULT_FORMAT_0 = "%.0f";

    /**
     * 保留两位小数
     * @param obj
     * @return
     */
    public static String keepTwoDecimal(Object obj){
        return String.format(DEFAULT_FORMAT,obj);
    }

    /**
     * 保留两位小数
     * @param obj
     * @return
     */
    public static String keep0Decimal(Object obj){
        return String.format(DEFAULT_FORMAT_0,obj);
    }


    public static String resToString(@StringRes int strRes){
        return Utils.getApp().getResources().getString(strRes);
    }
}
