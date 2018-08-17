package com.steadyoung.app.baselibs.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/4.
 * Email : steadyoung@foxmail.com
 * Auth  : wayne
 * fragment适配器
 */

public class BaseFragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments;
    private String[] mTitles;

    public BaseFragmentAdapter(FragmentManager fm, ArrayList<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    public BaseFragmentAdapter(FragmentManager fm, ArrayList<Fragment> mFragments, String[] mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null || mTitles.length == 0 || position >= mTitles.length ? "": mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments == null || mFragments.size() == 0 || position >= mFragments.size() ? null : mFragments.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);不要销毁item
    }

}
