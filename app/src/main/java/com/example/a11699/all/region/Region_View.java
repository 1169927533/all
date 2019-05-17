package com.example.a11699.all.region;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.a11699.all.R;

/**
 * 作者：余智强
 * 2019/5/11
 */
//https://blog.csdn.net/u010853130/article/details/85076887
    //书上代码已经过时，需要参考网上教程
public class Region_View extends View {
    private Bitmap mBitmap;
    private int clipWidth = 0;
    private int width, height;
    private static final int CLIP_HEIGHT = 30;
    private Region mRgn;

    private Path mPath;
    public Region_View(Context context) {
        super(context);
        init();
    }

    public Region_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    void init() {
        mPath = new Path();
         setLayerType(LAYER_TYPE_SOFTWARE,null);
         mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.card_view_image);
         width = mBitmap.getWidth();
         height = mBitmap.getHeight();
         mRgn = new Region();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = 0;
        mPath.reset();
        while(i*CLIP_HEIGHT <= height){
            if(i%2 == 0){
              //  mRgn.union(new Rect(0,i*CLIP_HEIGHT,clipWidth,(i+1)*CLIP_HEIGHT));
                mPath.addRect(new RectF(0,i*CLIP_HEIGHT,clipWidth,(i+1)*CLIP_HEIGHT),Path.Direction.CCW);
            }else {
                //mRgn.union(new Rect(width-clipWidth,i*CLIP_HEIGHT,width,(i+1)*CLIP_HEIGHT));

                mPath.addRect(new RectF(width,i*CLIP_HEIGHT,width-clipWidth,(i+1)*CLIP_HEIGHT),Path.Direction.CCW);
            }
            i++;
        }
        canvas.clipPath(mPath);
        canvas.drawBitmap(mBitmap,0,0,new Paint());
        if(clipWidth>width){
            return;
        }
        clipWidth+=50;
        invalidate();
    }


    void drawRegion(Canvas canvas, Region rgn, Paint paint) {
        RegionIterator iter = new RegionIterator(rgn);
        Rect rect = new Rect();

        while (iter.next(rect)) {
            canvas.drawRect(rect, paint);
        }
    }
}
