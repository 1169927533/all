package com.example.a11699.all.mvpstudy.easy_mvp.presenter;

import com.example.a11699.all.mvpstudy.easy_mvp.bean.UserBean;
import com.example.a11699.all.mvpstudy.easy_mvp.dao.IUserModel;
import com.example.a11699.all.mvpstudy.easy_mvp.dao.IUserView;
import com.example.a11699.all.mvpstudy.easy_mvp.model.UserModel;

/**
 * 作者：余智强
 * 2019/3/16
 */
public class UserPresenter {
    private IUserView mUserView;
    private IUserModel mUserModel;

    public UserPresenter(IUserView view) {
        mUserView = view;
        mUserModel = new UserModel();
    }

    public void saveUser(int id, String firstName, String lastName) {
        mUserModel.setID(id);
        mUserModel.setFirstName(firstName);
        mUserModel.setLastName(lastName);
    }

    public void loadUser(int id) {
        UserBean user = mUserModel.load(id);
        mUserView.setFirstName(user.getFirstName());
        mUserView.setLastName(user.getLastName());
    }
}
