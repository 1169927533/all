package com.example.a11699.all.beisaier.huaban;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.graphics.Path;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * 作者：余智强
 * 2019/4/30
 */
public class ZiDingYi_View extends View {
    private Canvas  mCanvas;
    private Path    mPath;
    private Paint   mBitmapPaint;
    private Bitmap mBitmap;
    private Paint mPaint;

    private ArrayList<DrawPath> savePath;
    private ArrayList<DrawPath> deletePath;
    private DrawPath dp;

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private int bitmapWidth;
    private int bitmapHeight;

    public ZiDingYi_View(Context c) {
        super(c);
        //得到屏幕的分辨率
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) c).getWindowManager().getDefaultDisplay().getMetrics(dm);

        bitmapWidth = dm.widthPixels;
        bitmapHeight = dm.heightPixels - 2 * 45;

        initCanvas();
        savePath = new ArrayList<DrawPath>();
        deletePath = new ArrayList<DrawPath>();

    }
    public ZiDingYi_View(Context c, AttributeSet attrs) {
        super(c,attrs);
        //得到屏幕的分辨率
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) c).getWindowManager().getDefaultDisplay().getMetrics(dm);

        bitmapWidth = dm.widthPixels;
        bitmapHeight = dm.heightPixels - 2 * 45;

        initCanvas();
        savePath = new ArrayList<DrawPath>();
        deletePath = new ArrayList<DrawPath>();
    }
    //初始化画布
    public void initCanvas(){

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xFF00FF00);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(10);

        mBitmapPaint = new Paint(Paint.DITHER_FLAG);



        //画布大小
        mBitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight,
                Bitmap.Config.RGB_565);
        mCanvas = new Canvas(mBitmap);  //所有mCanvas画的东西都被保存在了mBitmap中

        mCanvas.drawColor(Color.WHITE);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);     //显示旧的画布
        if (mPath != null) {
            // 实时的显示
            canvas.drawPath(mPath, mPaint);
        }
    }
    //路径对象
    class DrawPath{
        Path path;
        Paint paint;
    }

    /**
     * 撤销的核心思想就是将画布清空，
     * 将保存下来的Path路径最后一个移除掉，
     * 重新将路径画在画布上面。
     */
    public void undo(){

        System.out.println(savePath.size()+"--------------");
        if(savePath != null && savePath.size() > 0){
            //调用初始化画布函数以清空画布
            initCanvas();

            //将路径保存列表中的最后一个元素删除 ,并将其保存在路径删除列表中
            DrawPath drawPath = savePath.get(savePath.size() - 1);
            deletePath.add(drawPath);
            savePath.remove(savePath.size() - 1);

            //将路径保存列表中的路径重绘在画布上
            Iterator<DrawPath> iter = savePath.iterator();      //重复保存
            while (iter.hasNext()) {
                DrawPath dp = iter.next();
                mCanvas.drawPath(dp.path, dp.paint);

            }
            invalidate();// 刷新
        }
    }
    /**
     * 恢复的核心思想就是将撤销的路径保存到另外一个列表里面(栈)，
     * 然后从redo的列表里面取出最顶端对象，
     * 画在画布上面即可
     */
    public void redo(){
        if(deletePath.size() > 0){
            //将删除的路径列表中的最后一个，也就是最顶端路径取出（栈）,并加入路径保存列表中
            DrawPath dp = deletePath.get(deletePath.size() - 1);
            savePath.add(dp);
            //将取出的路径重绘在画布上
            mCanvas.drawPath(dp.path, dp.paint);
            //将该路径从删除的路径列表中去除
            deletePath.remove(deletePath.size() - 1);
            invalidate();
        }
    }
    /*
     * 清空的主要思想就是初始化画布
     * 将保存路径的两个List清空
     * */
    public void removeAllPaint(){
        //调用初始化画布函数以清空画布
        initCanvas();
        invalidate();//刷新
        savePath.clear();
        deletePath.clear();
    }

    /*
     * 保存所绘图形
     * 返回绘图文件的存储路径
     * */
    public String saveBitmap(){
        //获得系统当前时间，并以该时间作为文件名
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyyMMddHHmmss");
        Date curDate   =   new   Date(System.currentTimeMillis());//获取当前时间
        String   str   =   formatter.format(curDate);
        String paintPath = "";
        str = str + "paint.png";
        File dir = new File("/sdcard/notes/");
        File file = new File("/sdcard/notes/",str);
        if (!dir.exists()) {
            dir.mkdir();
        }
        else{
            if(file.exists()){
                file.delete();
            }
        }

        try {
            FileOutputStream out = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            //保存绘图文件路径
            paintPath = "/sdcard/notes/" + str;


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return paintPath;
    }


    private void touch_start(float x, float y) {
        mPath.reset();//清空path
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }
    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            //mPath.quadTo(mX, mY, x, y);
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);//源代码是这样写的，可是我没有弄明白，为什么要这样？
            mX = x;
            mY = y;
        }
    }
    private void touch_up() {
        mPath.lineTo(mX, mY);
        mCanvas.drawPath(mPath, mPaint);
        savePath.add(dp);
        mPath = null;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                mPath = new Path();
                dp = new DrawPath();
                dp.path = mPath;
                dp.paint = mPaint;

                touch_start(x, y);
                invalidate(); //清屏
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }


}
 /*   private Path mPath = new Path();
    private Paint mPaint;
    Canvas canvas1;

    public ZiDingYi_View(Context context) {
        super(context);
    }

    public ZiDingYi_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        canvas1 = new Canvas();
    }

    int sa = 1;

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        if (sa == 1) {
            mPath.moveTo(100, 300);
            mPath.quadTo(200, 100, 300, 300);
            sa = 5;
        }

        //   canvas.drawPath(mPath,mPaint);

        canvas.drawPath(mPath, mPaint);

    }

    float x1, y1, x2, y2;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //当手指按下的时候
                x1 = event.getX();
                y1 = event.getY();
                return true;

            case MotionEvent.ACTION_MOVE:

                x2 = event.getX();
                y2 = event.getY();
                if (y1 - y2 > 50) {
                    Toast.makeText(getContext(), "向上滑", Toast.LENGTH_SHORT).show();
                } else if (y2 - y1 > 50) {
                    mPath.moveTo(100, 300);
                    mPath.quadTo(200, y2 - y1, 300, 300);
                    //  invalidate();
                    // Toast.makeText(getContext(), "向下滑", Toast.LENGTH_SHORT).show();
                } else if (x1 - x2 > 50) {
                    Toast.makeText(getContext(), "向左滑", Toast.LENGTH_SHORT).show();
                } else if (x2 - x1 > 50) {
                    Toast.makeText(getContext(), "向右滑", Toast.LENGTH_SHORT).show();
                }
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
}
*/