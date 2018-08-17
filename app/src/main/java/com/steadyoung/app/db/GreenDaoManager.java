package com.steadyoung.app.db;

import com.steadyoung.app.baselibs.base.BaseApplication;
import com.steadyoung.greendao.gen.DaoMaster;
import com.steadyoung.greendao.gen.DaoSession;
import com.steadyoung.greendao.gen.TestBeanDao;

/**
 * Created by Administrator on 2017/9/8.
 * Email : steadyoung@foxmail.com
 * Auth  : wayne
 * Desc  : 数据库管理类
 */
public class GreenDaoManager {

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private GreenDaoManager() {
        init();
    }

    /**
     * 静态内部类，实例化对象使用
     */
    private static class SingleInstanceHolder {
        private static final GreenDaoManager INSTANCE = new GreenDaoManager();
    }

    /**
     * 对外唯一实例的接口
     *
     * @retur
     *
     */
    public static GreenDaoManager getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }

    /**
     * 初始化数据
     */
    private void init() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApplication.getApp(), GreenDaoConfig.DB_NAME);
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getmDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getmDaoSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

    /**
     * 获取TestBeanDao（TestBean数据库操作类）
     * @return
     */
    public TestBeanDao getUserDao(){
        return mDaoSession.getTestBeanDao();
    }

}
