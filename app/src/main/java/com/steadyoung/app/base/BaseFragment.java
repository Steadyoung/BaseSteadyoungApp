package com.steadyoung.app.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.steadyoung.app.baselibs.base.BasePresenter;
import com.steadyoung.app.baselibs.base.BaseView;
import com.steadyoung.app.module.simple.SimpleFragment;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/9/4.
 * Email : steadyoung@foxmail.com
 * Auth  : wayne
 * Desc  : Fragment基类
 */

public abstract class BaseFragment<V extends BaseView, T extends BasePresenter<V>> extends RxFragment implements View.OnClickListener,BaseView {

    protected T mPresenter;

    /**设置布局ID*/
    protected abstract int getLayoutResId();
    /**初始化数据*/
    protected abstract void initData();
    /**初始化事件监控*/
    protected abstract void initEvent();
    /**用于创建Presenter和判断是否使用MVP模式(由子类实现)*/
    protected abstract T createPresenter();

    protected View mView;

    protected Context mContext;

    protected Activity mActivity;

    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是否使用MVP模式
        mPresenter = createPresenter();
        if (mPresenter != null) {
            //因为之后所有的子类都要实现对应的View接口
            mPresenter.attachView((V) this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mView = getView();
        mContext = getContext();
        mActivity = getActivity();
        if (mPresenter != null) {
            mPresenter.attachActivity(mActivity);
        }
        unbinder = ButterKnife.bind(this,mView);
        initData();
        initEvent();
    }

    @Override
    public void onDestroy() {
        OkGo.getInstance().cancelTag(this);
        mView = null;
        mContext = null;
        mActivity = null;
        if(unbinder != null){
            unbinder.unbind();
            unbinder = null;
        }
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDestroy();
    }

    @Override
    public void showWaitingDialog() {
        ((BaseView)mActivity).showWaitingDialog();
    }

    @Override
    public void hideWaitingDialog() {
        ((BaseView)mActivity).hideWaitingDialog();
    }

}
