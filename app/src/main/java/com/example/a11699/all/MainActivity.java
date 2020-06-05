package com.example.a11699.all;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.Toast;

import com.example.a11699.all.CustomLayoutManger.customLayoutManagerActivity;
import com.example.a11699.all.Huabiaoge.Huabiaoge;
import com.example.a11699.all.SurfaceView.SurfaceView;

import com.example.a11699.all.backTan.BackTanActivity;
import com.example.a11699.all.beisaier.shoushi_view.Bei_two;
import com.example.a11699.all.bolang.bolang.Bolang_Activity;
import com.example.a11699.all.buguizeliudongbolang.BoLangActivity;
import com.example.a11699.all.chouti.ChoutiActivity;
import com.example.a11699.all.contextstudy.ContextActivity;
import com.example.a11699.all.dialogment.Main2Activity;
import com.example.a11699.all.fragment.AnimaFragment;
import com.example.a11699.all.gongxiang.gongxiangActivity;
import com.example.a11699.all.huizhixiaozhu.activity.Hua_pig;
import com.example.a11699.all.jindu.JinDuActivity;
import com.example.a11699.all.layoutanimation.LayoutAnimationStudy;
import com.example.a11699.all.liubuju.LiuActivity;
import com.example.a11699.all.loukong.LouKongActivity;
import com.example.a11699.all.mvpstudy.easy_mvp.view.UserActivity;
import com.example.a11699.all.myprocassbar.MyProcessBarActivity;
import com.example.a11699.all.myviewpage.MyViewpagerActivity;
import com.example.a11699.all.qiyu.getdialog.GetGoodsActivity;
import com.example.a11699.all.qiyu.paydialog.PayDialogFragment;
import com.example.a11699.all.region.Study_region;
import com.example.a11699.all.shengsuo.activity.Xiaoguo_1;
import com.example.a11699.all.shuibowen.ShuiBo;
import com.example.a11699.all.videoplay.MyVideoPlayer;
import com.example.a11699.all.voiceband.VoiceBandActivity;
import com.example.a11699.all.wangyi.Wangyi;
import com.example.a11699.all.wujiaoxing.WuJiaoxing;
import com.example.a11699.all.wulianwang.Wuliwang_Activity;
import com.example.a11699.all.youhuafanhui.YouhuaBackActivity;
import com.example.a11699.all.yuanbingtongjitu.YuanBingTongJiTu;
import com.example.a11699.all.zhizhuwang.ZhiZhuwang;
import com.example.a11699.all.zidingyiView.DrawZheLine;
import com.example.a11699.all.zidingyi_view_study.activity.study_zidingyi_view;
import com.example.a11699.all.zidingyi_view_study.save_layer_study.Savalayer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button wulianwang_btn, mvp_btn, pait_pig, ziding_btn_view, main_gongxiang, drawZhexian, studyFragent, main_sufaceview, main_recycleview, main_paintbiaoge, dialogment;
    private Button btn_backtan, btn_voiceband, chouti_btn, wujiaoxing, region, shuibowen, zhizhuwabg, save_layer, youhuafanhui_btn, shengsuo_btn, shengsuo_donghua, shengsuo_beisai, bolang;
    private Button btn_mypagestudy, btn_context, btn_jindutiao, btn_bolang, btn_liu, btn_pay, btn_aoyun, btn_yuanbingtojitu, btn_getgoods;
    private Button btn_layoutanimal, btn_voiceband2, btn_yindao, btn_myprocessbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        judgePermission();
    }

    void initview() {
        btn_yindao = findViewById(R.id.btn_yindao);
        btn_voiceband2 = findViewById(R.id.btn_voiceband2);
        btn_layoutanimal = findViewById(R.id.btn_layoutanimal);
        main_gongxiang = findViewById(R.id.main_gongxiang);
        drawZhexian = findViewById(R.id.drawZhexian);
        studyFragent = findViewById(R.id.studyFragent);
        main_sufaceview = findViewById(R.id.main_sufaceview);
        main_recycleview = findViewById(R.id.main_recycleview);
        main_paintbiaoge = findViewById(R.id.main_paintbiaoge);
        dialogment = findViewById(R.id.dialogment);
        btn_context = findViewById(R.id.btn_context);
        ziding_btn_view = findViewById(R.id.ziding_btn_view);
        save_layer = findViewById(R.id.save_layer);
        pait_pig = findViewById(R.id.pait_pig);
        zhizhuwabg = findViewById(R.id.zhizhuwabg);
        wujiaoxing = findViewById(R.id.wujiaoxing);
        region = findViewById(R.id.region);
        mvp_btn = findViewById(R.id.mvp_btn);
        wulianwang_btn = findViewById(R.id.wulianwang_btn);
        chouti_btn = findViewById(R.id.chouti_btn);
        btn_bolang = findViewById(R.id.bolang);
        btn_aoyun = findViewById(R.id.btn_aoyun);
        btn_voiceband = findViewById(R.id.btn_voiceband);
        btn_yuanbingtojitu = findViewById(R.id.btn_yuanbingtojitu);
        btn_mypagestudy = findViewById(R.id.btn_mypagestudy);
        youhuafanhui_btn = findViewById(R.id.youhuafanhui_btn);
        shengsuo_btn = findViewById(R.id.shengsuo_btn);
        shengsuo_donghua = findViewById(R.id.shengsuo_donghua);
        shengsuo_beisai = findViewById(R.id.shengsuo_beisai);
        btn_liu = findViewById(R.id.btn_liu);
        shuibowen = findViewById(R.id.shuibowen);
        btn_getgoods = findViewById(R.id.btn_getgoods);
        btn_pay = findViewById(R.id.btn_pay);
        btn_backtan = findViewById(R.id.btn_backtan);
        btn_jindutiao = findViewById(R.id.btn_jindutiao);
        btn_myprocessbar = findViewById(R.id.btn_myprocessbar);
        btn_bolang = findViewById(R.id.btn_bolang);
        shuibowen.setOnClickListener(this);
        main_gongxiang.setOnClickListener(this);
        shengsuo_btn.setOnClickListener(this);
        drawZhexian.setOnClickListener(this);
        studyFragent.setOnClickListener(this);
        main_sufaceview.setOnClickListener(this);
        main_recycleview.setOnClickListener(this);
        main_paintbiaoge.setOnClickListener(this);
        btn_layoutanimal.setOnClickListener(this);
        dialogment.setOnClickListener(this);
        ziding_btn_view.setOnClickListener(this);
        pait_pig.setOnClickListener(this);
        btn_voiceband2.setOnClickListener(this);
        mvp_btn.setOnClickListener(this);
        btn_context.setOnClickListener(this);
        wulianwang_btn.setOnClickListener(this);
        chouti_btn.setOnClickListener(this);
        youhuafanhui_btn.setOnClickListener(this);
        shengsuo_donghua.setOnClickListener(this);
        btn_backtan.setOnClickListener(this);
        shengsuo_beisai.setOnClickListener(this);
        btn_voiceband.setOnClickListener(this);
        btn_bolang.setOnClickListener(this);
        btn_bolang.setOnClickListener(this);
        save_layer.setOnClickListener(this);
        zhizhuwabg.setOnClickListener(this);
        region.setOnClickListener(this);
        wujiaoxing.setOnClickListener(this);
        btn_mypagestudy.setOnClickListener(this);
        btn_yuanbingtojitu.setOnClickListener(this);
        btn_myprocessbar.setOnClickListener(this);

        SlidingPaneLayout slidingPaneLayout = new SlidingPaneLayout(this);
        btn_jindutiao.setOnClickListener(this);
        btn_liu.setOnClickListener(this);
        btn_pay.setOnClickListener(this);
        btn_aoyun.setOnClickListener(this);
        btn_getgoods.setOnClickListener(this);
        btn_yindao.setOnClickListener(this);
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
            case R.id.btn_backtan:
                //回弹效果
                Intent intent22 = new Intent(MainActivity.this, BackTanActivity.class);
                startActivity(intent22);
            case R.id.btn_mypagestudy:
                Intent intent23 = new Intent(MainActivity.this, MyViewpagerActivity.class);
                startActivity(intent23);
                break;
            case R.id.btn_jindutiao:
                Intent intent24 = new Intent(MainActivity.this, JinDuActivity.class);
                startActivity(intent24);
                break;
            case R.id.btn_bolang:
                Intent intent25 = new Intent(MainActivity.this, BoLangActivity.class);
                startActivity(intent25);
                break;
            case R.id.btn_liu:
                Intent intent26 = new Intent(MainActivity.this, LiuActivity.class);
                startActivity(intent26);
                break;
            case R.id.btn_pay:
                Intent intent27 = new Intent(MainActivity.this, PayDialogFragment.class);
                startActivity(intent27);
                break;
            case R.id.btn_aoyun:
              /*  Intent intent28 = new Intent(MainActivity.this, AoyunWuHuanActivity.class);
                startActivity(intent28);
              */

                break;
            case R.id.btn_yuanbingtojitu:
                Intent intent28 = new Intent(MainActivity.this, YuanBingTongJiTu.class);
                startActivity(intent28);
                break;
            case R.id.btn_getgoods:
                Intent intent29 = new Intent(MainActivity.this, GetGoodsActivity.class);
                startActivity(intent29);
                break;
            case R.id.btn_context:
                Intent intent30 = new Intent(MainActivity.this, ContextActivity.class);
                startActivity(intent30);
                break;
            case R.id.btn_voiceband:
                Intent intent31 = new Intent(MainActivity.this, VoiceBandActivity.class);
                startActivity(intent31);
                break;
            case R.id.btn_layoutanimal:
                Intent intent32 = new Intent(MainActivity.this, LayoutAnimationStudy.class);
                startActivity(intent32);
                break;
            case R.id.btn_voiceband2:
                Intent intent33 = new Intent(MainActivity.this, MyVideoPlayer.class);
                startActivity(intent33);
                break;
            case R.id.btn_yindao:
                Intent intent34 = new Intent(MainActivity.this, LouKongActivity.class);
                startActivity(intent34);
                break;
            case R.id.btn_myprocessbar:
                Intent intent35 = new Intent(MainActivity.this, MyProcessBarActivity.class);
                startActivity(intent35);
                break;
        }

    }

    protected void judgePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查该权限是否已经获取
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            List<String> permissionList = new ArrayList<String>();

            /*if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.READ_PHONE_STATE);
            }*/
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }

            if (!permissionList.isEmpty()) {
                String[] permissions = permissionList.toArray(new String[permissionList.size()]);
                ActivityCompat.requestPermissions(this, permissions, 1);
            } else {

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (permissions.length > 0) {
                    int locationFlags = 0;
                    for (int i = 0; i < permissions.length; i++) {

                        if (permissions[i].equals(Manifest.permission.READ_PHONE_STATE)) {
                            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                                locationFlags += 1;
                            } else {
                                Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        if (permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                                locationFlags += 1;
                            } else {
                                Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        if (permissions[i].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                                locationFlags += 1;
                            } else {
                                Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }

                    if (locationFlags == 3) {

                    }
                }
            }
        }

    }
}
