package com.steadyoung.app.baselibs.base;

import android.app.Activity;

import com.lzy.okgo.OkGo;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/4/9.
 * Email : steadyoung@foxmail.com
 * Auth  : wayne
 * Desc  : BasePresenter
 */

public abstract class BasePresenter<V extends BaseView> {

    protected Reference<V> mViewRef;
    protected Reference<Activity> mActivityRef;

    /**
     * 绑定view和activity
     * @param view
     * @param activity
     */
    private void attach(V view,Activity activity){
        attachView(view);
        attachActivity(activity);
    }

    //绑定view
    public void attachView(V view){
        mViewRef = new WeakReference<V>(view);
    }

    //绑定activity
    public void attachActivity(Activity activity){
        mActivityRef = new WeakReference<Activity>(activity);
    }

    protected V getView(){
        return mViewRef.get();
    }

    protected Activity getActivity(){
        return mActivityRef.get();
    }

    public boolean isActivityAttached(){
        return mViewRef != null&&mViewRef.get()!=null;
    }

    public boolean isViewAttached(){
        return mActivityRef != null&&mActivityRef.get()!=null;
    }

    public void detach(){
        OkGo.getInstance().cancelTag(this);
        if(mViewRef!=null){
            mViewRef.clear();
            mViewRef = null;
        }
        if(mActivityRef!=null){
            mActivityRef.clear();
            mActivityRef = null;
        }
    }

}
