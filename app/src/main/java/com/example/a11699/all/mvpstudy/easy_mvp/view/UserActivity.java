package com.example.a11699.all.mvpstudy.easy_mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.a11699.all.R;
import com.example.a11699.all.mvpstudy.easy_mvp.dao.IUserView;
import com.example.a11699.all.mvpstudy.easy_mvp.presenter.UserPresenter;

public class UserActivity extends AppCompatActivity implements IUserView {
    private UserPresenter mUserPresenter;
    EditText id_id,firstname,lastname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mUserPresenter = new UserPresenter(this);
        id_id = findViewById(R.id.id_id);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);

    }

    public void cun(View vieEditTextw) {
        mUserPresenter.saveUser(getID(), getFirstName(),
                getLastName());
    }

    public void du(View view) {
        mUserPresenter.loadUser(getID());
    }


    @Override
    public void setFirstName(String firstName) {
        // TODO Auto-generated method stub
        firstname.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        // TODO Auto-generated method stub
        lastname.setText(lastName);
    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return Integer.parseInt(id_id.getText().toString());
    }

    @Override
    public String getFirstName() {

        return firstname.getText().toString();
    }

    @Override
    public String getLastName() {

        return lastname.getText().toString();
    }

}
