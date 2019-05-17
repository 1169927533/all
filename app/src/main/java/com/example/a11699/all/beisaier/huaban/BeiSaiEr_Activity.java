package com.example.a11699.all.beisaier.huaban;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a11699.all.R;

//画板效果
public class BeiSaiEr_Activity extends AppCompatActivity implements View.OnClickListener{
    Button button,button1,button2;
    ZiDingYi_View ziDingYi_view ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bei_sai_er_);
        button = findViewById(R.id.button111);
        button1 = findViewById(R.id.button222);
        button2 = findViewById(R.id.button3);
        ziDingYi_view = findViewById(R.id.ziDingYi_View);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button111:
                ziDingYi_view.undo();
                break;
            case R.id.button222:
                ziDingYi_view.redo();
                break;
            case R.id.button3:
                ziDingYi_view.removeAllPaint();
                break;
        }
    }
}
