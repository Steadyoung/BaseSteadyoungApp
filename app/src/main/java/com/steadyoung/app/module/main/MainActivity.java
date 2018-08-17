package com.steadyoung.app.module.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.steadyoung.app.R;
import com.steadyoung.app.base.BaseActivity;
import com.steadyoung.app.baselibs.base.BaseFragmentAdapter;
import com.steadyoung.app.baselibs.base.BasePresenter;
import com.steadyoung.app.commonlibs.util.common.LogUtils;
import com.steadyoung.app.commonlibs.util.common.ToastUtils;
import com.steadyoung.app.commonlibs.widget.BottomNavigationViewEx;
import com.steadyoung.app.module.simple.SimpleFragment;
import com.steadyoung.app.widget.PopupMenuUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bnve)
    BottomNavigationViewEx bnve;
    @BindView(R.id.main_vp)
    ViewPager mainVp;
    @BindView(R.id.pop_iv_img)
    ImageView popIvImg;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

        //初始化底部导航栏属性
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);
        bnve.setLargeTextSize(14);
        bnve.setSmallTextSize(12);

        //初始化fragment集合数据
        ArrayList<Fragment> fragmentList = new ArrayList<>(4);
        fragmentList.add(SimpleFragment.newInstance(getString(R.string.test1)));
        fragmentList.add(SimpleFragment.newInstance(getString(R.string.test2)));
        fragmentList.add(SimpleFragment.newInstance(getString(R.string.test3)));
        fragmentList.add(SimpleFragment.newInstance(getString(R.string.test4)));

        //初始化fragment到viewpager和底部导航栏绑定
        BaseFragmentAdapter fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList);
        mainVp.setAdapter(fragmentAdapter);
        //中间使用自定义弹出按钮，自定义BottomNavigationViewEx和viewpager绑定避免图标和fragment选择对应不上
//        bnve.setupWithViewPager(mainVp);
    }

    @Override
    protected void initEvent() {

        //设置BottomNavigationViewEx item 点击选择时间
        bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = 0;
                switch (item.getItemId()) {
                    case R.id.i_test1:
                        position = 0;
                        break;
                    case R.id.i_test2:
                        position = 1;
                        break;
                    case R.id.i_test3:
                        position = 2;
                        break;
                    case R.id.i_test4:
                        position = 3;
                        break;
                    case R.id.i_empty: {
                        return false;
                    }
                    default:
                        break;
                }
                if(previousPosition != position) {
                    mainVp.setCurrentItem(position, false);
                    previousPosition = position;
                    LogUtils.i( "-----bnve-------- previous item:" + bnve.getCurrentItem() + " current item:" + position + " ------------------");
                }

                return true;
            }
        });

        //ViewPager 滑动选择事件
        mainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.i("-----ViewPager-------- previous item:" + bnve.getCurrentItem() + " current item:" + position + " ------------------");
                // 2 is center
                if (position >= 2)
                {   // if page is 2, need set bottom item to 3, and the same to 3 -> 4，
                    // 选择项下标为2那么是中间加号的位置，跳过直接选择下一项，加号后面的都要加一位
                    position++;
                }
                bnve.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        PopupMenuUtil.getInstance().setOnMenuClickListener(new PopupMenuUtil.OnMenuClickListener() {
            @Override
            public void onTest1() {
                ToastUtils.showShort(R.string.test1);
            }

            @Override
            public void onTest2() {
                ToastUtils.showShort(R.string.test2);
            }

            @Override
            public void onTest3() {
                ToastUtils.showShort(R.string.test3);
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getGroupLayoutId() {
        return 0;
    }

    @OnClick({R.id.pop_iv_img, R.id.bnve, R.id.main_vp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pop_iv_img:
                PopupMenuUtil.getInstance()._show(MainActivity.this,popIvImg);
                break;
            default:
                break;
        }
    }
}
