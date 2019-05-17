package com.example.a11699.all.beisaier.lujingjiazaidonghua;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：余智强
 * 2019/5/2
 */
public class LuJing_View extends View {
    private Paint mPaint;
    private Path mDstPath,mCirclePath;
    private PathMeasure mPathMeasure;
    public LuJing_View(Context context) {
        super(context);
    }

    public LuJing_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4)      ;
    }
}
