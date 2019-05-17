package com.example.a11699.all.chouti;

import android.graphics.Color;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a11699.all.R;

public class ChoutiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chouti);
        SlidingPaneLayout layout = (SlidingPaneLayout)
                findViewById(R.id.sliding_pane_layout);
        layout.setSliderFadeColor(Color.TRANSPARENT);
    }


}
