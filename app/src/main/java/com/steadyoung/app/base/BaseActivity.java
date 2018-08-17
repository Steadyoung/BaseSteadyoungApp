package com.steadyoung.app.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;

import com.lzy.okgo.OkGo;
import com.steadyoung.app.baselibs.R;
import com.steadyoung.app.baselibs.base.BasePresenter;
import com.steadyoung.app.baselibs.base.BaseView;
import com.steadyoung.app.commonlibs.statusbar.AlexStatusBarUtils;
import com.steadyoung.app.commonlibs.statusbar.StatusBarUtils;
import com.steadyoung.app.commonlibs.widget.dialog.LoadDialog;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2017/9/7.
 * Email : steadyoung@foxmail.com
 * Auth  : wayne
 * Desc  : Activity基类
 */
public abstract class BaseActivity<V extends BaseView, T extends BasePresenter<V>> extends RxFragmentActivity implements BaseView {

    protected T mPresenter;

    /**加载提示框*/
    protected LoadDialog loadDialog;

    private Unbinder unbinder;

    /**设置布局ID*/
    protected abstract @LayoutRes int getLayoutResId();
    /**初始化数据*/
    protected abstract void initData();
    /**初始化事件监控*/
    protected abstract void initEvent();

    /**用于创建Presenter和判断是否使用MVP模式(由子类实现)*/
    protected abstract T createPresenter();

    /**获取group layout,实现背景与状态栏融为一体*/
    protected abstract int getGroupLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity", "onCreate: " + getClass().getSimpleName());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //判断是否使用MVP模式
        mPresenter = createPresenter();
        if (mPresenter != null) {
            //因为之后所有的子类都要实现对应的View接口
            mPresenter.attachView((V) this);
            mPresenter.attachActivity(this);
        }
        setContentView(getLayoutResId());
        unbinder = ButterKnife.bind(this);
        initStatusBar();
        initData();
        initEvent();

    }

    /**
     * 判断是否需要修改状态栏融入背景
     */
    private void initStatusBar(){
        if(getGroupLayoutId() > 0 ){
            //状态栏界面背景一致
            AlexStatusBarUtils.setTransparentStatusBar(this, findViewById(getGroupLayoutId()));
            StatusBarUtils.setImmersiveStatusBar(this,true);
        }else{
            StatusBarUtils.setStatusBarColor(this,getResources().getColor(R.color.app_top_bg));
            StatusBarUtils.setDarkStatusBar(this);
        }

    }

    @Override
    protected void onDestroy() {
        OkGo.getInstance().cancelTag(this);
        if(loadDialog != null ) {
            if(loadDialog.isShowing()){
                loadDialog.dismiss();
            }
            loadDialog = null;
        }
        if(unbinder != null){
            unbinder.unbind();
        }
        if (mPresenter != null) {
            mPresenter.detach();
        }

        super.onDestroy();
        Log.d("BaseActivity", "onDestroy: "  + getClass().getSimpleName());
    }

    @Override
    public void showWaitingDialog() {
        if(loadDialog == null){
            loadDialog = new LoadDialog(this);
        }
        loadDialog.show();
    }

    @Override
    public void hideWaitingDialog() {
        if(loadDialog != null){
            loadDialog.dismiss();
        }
    }

}
