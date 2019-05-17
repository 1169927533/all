package com.example.a11699.all.dialogment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.a11699.all.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void sure_confirm(View view) {
        ConfirmDialog.newInstance("1")
                .setMargin(60)
                .setOutCancel(false)
                .show(getSupportFragmentManager());
    }
}
