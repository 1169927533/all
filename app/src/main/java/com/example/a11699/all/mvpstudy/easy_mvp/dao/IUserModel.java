package com.example.a11699.all.mvpstudy.easy_mvp.dao;

import com.example.a11699.all.mvpstudy.easy_mvp.bean.UserBean;

/**
 * 作者：余智强
 * 2019/3/16
 */
public interface IUserModel {
    void setID(int id);
    void setFirstName(String firstName);
    void setLastName(String lastName);

    UserBean load(int id);
}
