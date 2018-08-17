package com.steadyoung.app.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * File description.
 *
 * @author steadyoung
 * @date 2018/8/15 17:00
 * @email steadyoung@foxmail.com
 */
@Entity
public class TestBean {
    @Id
    private long id;

    private String name;

    @Generated(hash = 607398710)
    public TestBean(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 2087637710)
    public TestBean() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
