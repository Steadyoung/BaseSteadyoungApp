package com.steadyoung.app.module.simple;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.steadyoung.app.R;
import com.steadyoung.app.base.BaseFragment;
import com.steadyoung.app.baselibs.base.BasePresenter;

import butterknife.BindView;

/**
 * File description.
 *
 * @author steadyoung
 * @date 2018/8/16 16:27
 * @email steadyoung@foxmail.com
 */
public class SimpleFragment extends BaseFragment {

    private final static String TITLE = "TITLE";
    @BindView(R.id.center_tv)
    TextView centerTv;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_simple;
    }

    @Override
    protected void initData() {
        String title = getArguments().getString(TITLE);
        centerTv.setText(title);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 创建fragment
     *
     * @return
     */
    public static SimpleFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
