package com.example.a11699.all.youhuafanhui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.a11699.all.R;

import java.util.Random;

public class NextActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        RelativeLayout containerRl = (RelativeLayout) findViewById(R.id.container);

        //随机色

        Random random = new Random();
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);

        containerRl.setBackgroundColor(Color.argb(255,red,green,blue));

    }

    public void nextPage(View v) {
        startActivity(new Intent(this, NextActivity.class));
    }
}
