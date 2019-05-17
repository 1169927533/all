package com.example.a11699.all.Huabiaoge;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * 作者：余智强
 * 2019/1/8
 */
public class HelpDraw {
    /**
     * 绘制表格
     *
     * @param canvas  画布
     * @param winSize 屏幕尺寸
     * @param paint   画笔
     */
    public static void drawGrid(Canvas canvas, Point winSize, Paint paint) {
        //初始化网格画笔
        paint.setStrokeWidth(2);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        //设置虚线效果 new Float[]{可见长的宽度，不可见长的宽度},便移量
        paint.setPathEffect(new DashPathEffect(new float[]{10, 5}, 0));
        //画布上画东西的时候 需要给画布提供一个路径 和 画笔
        //所以就可以自定义path画出不同的效果
        canvas.drawPath(HelpPath.gridPath(50, winSize), paint);
    }

    /**
     * 绘制坐标系
     * @param canvas 画布
     * @param coo 坐标系原点
     * @param winSize 屏幕尺寸
     * @param paint 画笔
     */
    public static void drawCoo(Canvas canvas, Point coo, Point winSize, Paint paint) {
        //初始化网格画笔
        paint.setStrokeWidth(4);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        //设置虚线效果new float[]{可见长度，不可见长度}，偏移值
        paint.setPathEffect(null);
        //绘制直线
        canvas.drawPath(HelpPath.cooPath(coo, winSize), paint);
        //左箭头
        canvas.drawLine(winSize.x, coo.y, winSize.x - 40, coo.y - 20, paint);
        canvas.drawLine(winSize.x, coo.y, winSize.x - 40, coo.y + 20, paint);
        //下箭头
        canvas.drawLine(coo.x, winSize.y, coo.x - 20, winSize.y - 40, paint);
        canvas.drawLine(coo.x, winSize.y, coo.x + 20, winSize.y - 40, paint);
        //为坐标系绘制文字
        drawText4Coo(canvas, coo, winSize, paint);
    }

    /**
     * 为坐标系绘制文字
     * @param canvas 画布
     * @param coo 坐标原点
     * @param winSize 屏幕尺寸
     * @param paint 画笔
     */
    private static void drawText4Coo(Canvas canvas,Point coo,Point winSize,Paint paint){
        //绘制文字
        paint.setTextSize(50);
        canvas.drawText("x", winSize.x - 60, coo.y - 40, paint);
        canvas.drawText("y", coo.x - 40, winSize.y - 60, paint);
        paint.setTextSize(25);
        //X正轴文字
        for (int i = 1; i < (winSize.x - coo.x) / 50; i++) {
            paint.setStrokeWidth(2);
            canvas.drawText(100 * i + "", coo.x - 20 + 100 * i, coo.y + 40, paint);
            paint.setStrokeWidth(5);
            canvas.drawLine(coo.x + 100 * i, coo.y, coo.x + 100 * i, coo.y - 10, paint);
        }
        //X负轴文字
        for (int i = 1; i < coo.x / 50; i++) {
            paint.setStrokeWidth(2);
            canvas.drawText(-100 * i + "", coo.x - 20 - 100 * i, coo.y + 40, paint);
            paint.setStrokeWidth(5);
            canvas.drawLine(coo.x - 100 * i, coo.y, coo.x - 100 * i, coo.y - 10, paint);
        }
        //y正轴文字
        for (int i = 1; i < (winSize.y - coo.y) / 50; i++) {
            paint.setStrokeWidth(2);
            canvas.drawText(100 * i + "", coo.x + 20, coo.y + 10 + 100 * i, paint);
            paint.setStrokeWidth(5);
            canvas.drawLine(coo.x, coo.y + 100 * i, coo.x + 10, coo.y + 100 * i, paint);
        }
        //y负轴文字
        for (int i = 1; i < coo.y / 50; i++) {
            paint.setStrokeWidth(2);
            canvas.drawText(-100 * i + "", coo.x + 20, coo.y + 10 - 100 * i, paint);
            paint.setStrokeWidth(5);
            canvas.drawLine(coo.x, coo.y - 100 * i, coo.x + 10, coo.y - 100 * i, paint);
        }
    }
}
