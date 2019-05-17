package com.example.a11699.all;

import android.content.Intent;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a11699.all.CustomLayoutManger.customLayoutManagerActivity;
import com.example.a11699.all.Huabiaoge.Huabiaoge;
import com.example.a11699.all.SurfaceView.SurfaceView;
import com.example.a11699.all.beisaier.shoushi_view.Bei_two;
import com.example.a11699.all.bolang.bolang.Bolang_Activity;
import com.example.a11699.all.chouti.ChoutiActivity;
import com.example.a11699.all.dialogment.Main2Activity;
import com.example.a11699.all.fragment.AnimaFragment;
import com.example.a11699.all.gongxiang.gongxiangActivity;
import com.example.a11699.all.huizhixiaozhu.activity.Hua_pig;
import com.example.a11699.all.mvpstudy.easy_mvp.view.UserActivity;
import com.example.a11699.all.region.Region_View;
import com.example.a11699.all.region.Study_region;
import com.example.a11699.all.shengsuo.activity.Xiaoguo_1;
import com.example.a11699.all.shuibowen.ShuiBo;
import com.example.a11699.all.wangyi.Wangyi;
import com.example.a11699.all.wujiaoxing.WuJiaoxing;
import com.example.a11699.all.wulianwang.Wuliwang_Activity;
import com.example.a11699.all.youhuafanhui.YouhuaBackActivity;
import com.example.a11699.all.zhizhuwang.ZhiZhuwang;
import com.example.a11699.all.zidingyiView.DrawZheLine;
import com.example.a11699.all.zidingyi_view_study.activity.study_zidingyi_view;
import com.example.a11699.all.zidingyi_view_study.save_layer_study.Savalayer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button wulianwang_btn, mvp_btn, pait_pig, ziding_btn_view, main_gongxiang, drawZhexian, studyFragent, main_sufaceview, main_recycleview, main_paintbiaoge, dialogment;
    private Button chouti_btn,wujiaoxing,region,shuibowen,zhizhuwabg,save_layer, youhuafanhui_btn,shengsuo_btn,shengsuo_donghua,shengsuo_beisai,bolang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    void initview() {
        main_gongxiang = findViewById(R.id.main_gongxiang);
        drawZhexian = findViewById(R.id.drawZhexian);
        studyFragent = findViewById(R.id.studyFragent);
        main_sufaceview = findViewById(R.id.main_sufaceview);
        main_recycleview = findViewById(R.id.main_recycleview);
        main_paintbiaoge = findViewById(R.id.main_paintbiaoge);
        dialogment = findViewById(R.id.dialogment);
        ziding_btn_view = findViewById(R.id.ziding_btn_view);
        save_layer = findViewById(R.id.save_layer);
        pait_pig = findViewById(R.id.pait_pig);
        zhizhuwabg = findViewById(R.id.zhizhuwabg);
        wujiaoxing= findViewById(R.id.wujiaoxing);
        region = findViewById(R.id.region);
        mvp_btn = findViewById(R.id.mvp_btn);
        wulianwang_btn = findViewById(R.id.wulianwang_btn);
        chouti_btn = findViewById(R.id.chouti_btn);
        bolang = findViewById(R.id.bolang);
        youhuafanhui_btn = findViewById(R.id.youhuafanhui_btn);
        shengsuo_btn = findViewById(R.id.shengsuo_btn);
        shengsuo_donghua = findViewById(R.id.shengsuo_donghua);
        shengsuo_beisai = findViewById(R.id.shengsuo_beisai);
        shuibowen = findViewById(R.id.shuibowen);
        shuibowen.setOnClickListener(this);
        main_gongxiang.setOnClickListener(this);
        shengsuo_btn.setOnClickListener(this);
        drawZhexian.setOnClickListener(this);
        studyFragent.setOnClickListener(this);
        main_sufaceview.setOnClickListener(this);
        main_recycleview.setOnClickListener(this);
        main_paintbiaoge.setOnClickListener(this);
        dialogment.setOnClickListener(this);
        ziding_btn_view.setOnClickListener(this);
        pait_pig.setOnClickListener(this);
        mvp_btn.setOnClickListener(this);
        wulianwang_btn.setOnClickListener(this);
        chouti_btn.setOnClickListener(this);
        youhuafanhui_btn.setOnClickListener(this);
        shengsuo_donghua.setOnClickListener(this);
        shengsuo_beisai.setOnClickListener(this);
        bolang.setOnClickListener(this);
        save_layer.setOnClickListener(this);
        zhizhuwabg.setOnClickListener(this);
        region.setOnClickListener(this);
        wujiaoxing.setOnClickListener(this);
        SlidingPaneLayout slidingPaneLayout = new SlidingPaneLayout(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_gongxiang:
                Intent intent = new Intent(MainActivity.this, gongxiangActivity.class);
                startActivity(intent);
                break;
            case R.id.drawZhexian:
                Intent intent1 = new Intent(MainActivity.this, DrawZheLine.class);
                startActivity(intent1);
                break;
            case R.id.studyFragent:
                Intent intent2 = new Intent(MainActivity.this, AnimaFragment.class);
                startActivity(intent2);
                break;
            case R.id.main_sufaceview:
                Intent intent3 = new Intent(MainActivity.this, SurfaceView.class);
                startActivity(intent3);
                break;
            case R.id.main_recycleview:
                Intent intent4 = new Intent(MainActivity.this, customLayoutManagerActivity.class);
                startActivity(intent4);
                break;
            case R.id.main_paintbiaoge:
                Intent intent5 = new Intent(MainActivity.this, Huabiaoge.class);
                startActivity(intent5);
                break;
            case R.id.dialogment:
                Intent intent6 = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent6);
                break;
            case R.id.ziding_btn_view:
                Intent intent7 = new Intent(MainActivity.this, study_zidingyi_view.class);
                startActivity(intent7);
                break;
            case R.id.pait_pig:
                Intent intent8 = new Intent(MainActivity.this, Hua_pig.class);
                startActivity(intent8);
                break;
            case R.id.mvp_btn:
                Intent intent9 = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent9);
                break;
            case R.id.wulianwang_btn:
                Intent intent10 = new Intent(MainActivity.this, Wuliwang_Activity.class);
                startActivity(intent10);
                break;
            case R.id.chouti_btn:
                Intent intent11 = new Intent(MainActivity.this, ChoutiActivity.class);
                startActivity(intent11);
                break;
            case R.id.youhuafanhui_btn:
                Intent intent12 = new Intent(MainActivity.this, YouhuaBackActivity.class);
                startActivity(intent12);
                break;
            case R.id.shengsuo_btn:
                Intent intent13 = new Intent(MainActivity.this, Xiaoguo_1.class);
                startActivity(intent13);
                break;
            case R.id.shengsuo_donghua:
                Intent intent14 = new Intent(MainActivity.this, Wangyi.class);
                startActivity(intent14);
                break;
            case R.id.shengsuo_beisai:
                Intent intent15 = new Intent(MainActivity.this, Bei_two.class);
                startActivity(intent15);
                break;
            case R.id.bolang:
                Intent intent16 = new Intent(MainActivity.this, Bolang_Activity.class);
                startActivity(intent16);
                break;
            case R.id.save_layer:
                Intent intent17 = new Intent(MainActivity.this, Savalayer.class);
                startActivity(intent17);
                break;
            case R.id.zhizhuwabg:
                Intent intent18 = new Intent(MainActivity.this, ZhiZhuwang.class);
                startActivity(intent18);
                break;
            case R.id.shuibowen:
                Intent intent19 = new Intent(MainActivity.this, ShuiBo.class);
                startActivity(intent19);
                break;
            case R.id.region:
                Intent intent20 = new Intent(MainActivity.this, Study_region.class);
                startActivity(intent20);
                break;
            case R.id.wujiaoxing:
                Intent intent21 = new Intent(MainActivity.this, WuJiaoxing.class);
                startActivity(intent21);
                break;
        }

    }

}
