package com.steadyoung.app.commonlibs.util.permission;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 * Email : steadyoung@foxmail.com
 * Auth  : wayne
 * Desc  : RxPermissionUtil
 */
public class RxPermissionUtil {
    private RxPermissionUtil() {
    }

    //需要申请的权限
    private static String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    //检测权限
    public static String[] checkPermission(Context context){
        List<String> data = new ArrayList<>();//存储未申请的权限
        for (String permission : permissions) {
            int checkSelfPermission = ContextCompat.checkSelfPermission(context, permission);
            if(checkSelfPermission == PackageManager.PERMISSION_DENIED){//未申请
                data.add(permission);
            }
        }
        return data.toArray(new String[data.size()]);
    }
//
//    /**
//     * 初始化权限事件
//     */
//    private void initPermission() {
//        //检查权限
//        String[] permissions = RxPermissionUtil.checkPermission(this);
//        if (permissions.length == 0) {
//            //权限都申请了
//            //是否登录
//        } else {
//            //申请权限
//            ActivityCompat.requestPermissions(this, permissions, 100);
//        }
//    }
}
