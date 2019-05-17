package com.example.a11699.all.mvpstudy.easy_mvp.dao;

/**
 * 作者：余智强
 * 2019/3/16
 */
public interface IUserView {

    int getID();
    String getFirstName();
    String getLastName();
    void setFirstName(String firstName);
    void setLastName(String lastName);
}
