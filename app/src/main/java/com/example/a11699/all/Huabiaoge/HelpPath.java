package com.example.a11699.all.Huabiaoge;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

/**
 * 作者：余智强
 * 2019/1/8
 */

public class HelpPath {
    /**
     * 绘制表格 用path才能绘制虚线
     * @param step 小正方形的宽度
     * @param winSize 屏幕尺寸
     * @return
     */
    public static Path gridPath(int step, Point winSize){
        Path path = new Path();
        for(int i = 0;i <winSize.y/step+1;i++){
            path.moveTo(0,step*i);
            path.lineTo(winSize.x,step*i);
        }
        for(int i = 0;i <winSize.x/step+1;i++){
            path.moveTo(step * i ,0);
            path.lineTo(step * i,winSize.y);
        }
        return  path;
    }

    /**
     * 绘制坐标系
     * @param coo 坐标原点
     * @param winSize 屏幕尺寸
     * @return 返回的是路径
     */
    public static Path cooPath(Point coo,Point winSize){
        Path path = new Path();
        //x正半轴线
        path.moveTo(coo.x, coo.y);
        path.lineTo(winSize.x, coo.y);
        //x负半轴线
        path.moveTo(coo.x, coo.y);
        path.lineTo(coo.x - winSize.x, coo.y);
        //y负半轴线
        path.moveTo(coo.x, coo.y);
        path.lineTo(coo.x, coo.y - winSize.y);
        //y负半轴线
        path.moveTo(coo.x, coo.y);
        path.lineTo(coo.x, winSize.y);
        return path;
    }
}
