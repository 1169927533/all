package com.example.a11699.all.SurfaceView;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a11699.all.R;

public class SurfaceView extends AppCompatActivity implements View.OnClickListener {
    private Button surfaceView_Sinusoidal, surfaceView_suibianhua;
    DrawXian_Fragment drawXian_fragment  ;
    Huaban_Fragment huaban_fragment  ;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        initview();
        fragmentManager = getSupportFragmentManager();
    }

    void initview() {
        surfaceView_Sinusoidal = findViewById(R.id.surfaceView_Sinusoidal);
        surfaceView_suibianhua = findViewById(R.id.surfaceView_suibianhua);
        surfaceView_Sinusoidal.setOnClickListener(this);
        surfaceView_suibianhua.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (v.getId()) {
            case R.id.surfaceView_Sinusoidal:
                if(drawXian_fragment == null){
                   drawXian_fragment = new DrawXian_Fragment();
                   fragmentTransaction.add(R.id.surfaceView_framentLayout,drawXian_fragment);
                }else{
                    fragmentTransaction.show(drawXian_fragment);
                }
                break;
            case R.id.surfaceView_suibianhua:
                if(huaban_fragment == null){
                    huaban_fragment = new Huaban_Fragment();
                    fragmentTransaction.add(R.id.surfaceView_framentLayout,huaban_fragment);
                }else{
                    fragmentTransaction.show(huaban_fragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(drawXian_fragment != null)fragmentTransaction.hide(drawXian_fragment);
        if(huaban_fragment != null)fragmentTransaction.hide(huaban_fragment);
    }
}
