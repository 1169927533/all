package com.example.a11699.all.fragment.View;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.a11699.all.R;

public class MyView extends View {
    //定义相关变量 图片移动的x 和 y
    public float bitmapX;
    public float bitmapY;
    public MyView(Context context) {
        super(context);
        bitmapX=0;
        bitmapY=200;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        //根据图片生成位图
        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(), R.drawable.image14);
        //绘制图片
        canvas.drawBitmap(bitmap,bitmapX,bitmapY,paint);
        //判断图片是否回收,木有回收的话强制收回图片
        if(bitmap.isRecycled())
        {
            bitmap.recycle();
        }
    }
}
