package com.example.a11699.all.wujiaoxing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.a11699.all.R;

public class WuJiaoxing extends AppCompatActivity {
    CustomView customView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wu_jiaoxing);
        customView = findViewById(R.id.wujiao);
        customView.setOnItemSelectedListener(new CustomView.OnItemSelectedListener() {
            @Override
            public void onItemSelect(String content) {
                Toast.makeText(WuJiaoxing.this,content,Toast.LENGTH_LONG).show();
            }
        });
    }
}
