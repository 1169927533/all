package com.example.a11699.all.Huabiaoge;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.a11699.all.R;

public class Huabiaoge extends AppCompatActivity {
    Canvas canvas;
    ZidingyiView zidingyiView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huabiaoge);
        zidingyiView = findViewById(R.id.zidingyiview);

    }

    public void dd(View view) {

           zidingyiView.set(new float[]{
                   400, 400, 500, 500,
                   600, 400, 700, 350,
                   800, 300, 900, 300
           });
        zidingyiView.invalidate();

    }
}
