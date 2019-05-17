package com.example.a11699.all.mvpstudy.easy_mvp.bean;

/**
 * 作者：余智强
 * 2019/3/16
 */
public class UserBean {
    private String mFirstName ;
    private String mLastName ;
    public UserBean (String firstName, String lastName) {
        this .mFirstName = firstName;
        this .mLastName = lastName;
    }
    public String getFirstName() {
        return mFirstName ;
    }
    public String getLastName() {
        return mLastName ;
    }
}
