package com.example.a11699.all.youhuafanhui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.a11699.all.R;

public class YouhuaBackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youhua_back);
    }

    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }


    public void nextPage(View view) {
        startActivity(new Intent(this,NextActivity.class));
    }
}
