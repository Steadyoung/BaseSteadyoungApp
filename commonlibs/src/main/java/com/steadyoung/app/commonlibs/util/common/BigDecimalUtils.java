package com.steadyoung.app.commonlibs.util.common;

import java.math.BigDecimal;

/**
 * File description.
 *
 * @author steadyoung
 * @date 2018/8/10 16:59
 * @email steadyoung@foxmail.com
 */
public class BigDecimalUtils {

    /**
     * 四舍五入取整
     * @param d
     * @return
     */
    public static long roundLong(double d){
        return new BigDecimal(d).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
    }

    /**
     * 四舍五入取整
     * @param d
     * @return
     */
    public static int roundInt(double d){
        LogUtils.i("roundInt:d = " + d);
        return new BigDecimal(d).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }
}
