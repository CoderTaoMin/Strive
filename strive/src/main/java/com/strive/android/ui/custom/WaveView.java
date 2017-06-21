package com.strive.android.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 清风徐来 on 2017/06/20
 * 类说明:波纹效果View
 */
public class WaveView extends View {

    private Path mTopPath, mBottomPath;
    private Paint mTopPaint, mBottomPaint;

    private DrawFilter mDrawFilter;

    private float φ;
    /**
     * 高度的回调
     */
    private OnWaveAnimationListener onWaveAnimationListener;

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化路径
        mTopPath = new Path();
        mBottomPath = new Path();
        //初始化画笔
        mTopPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTopPaint.setAntiAlias(true);
        mTopPaint.setStyle(Paint.Style.FILL);
        mTopPaint.setColor(Color.WHITE);

        mBottomPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBottomPaint.setAntiAlias(true);
        mBottomPaint.setStyle(Paint.Style.FILL);
        mBottomPaint.setColor(Color.WHITE);
        mBottomPaint.setAlpha(80);
        //画布抗锯齿
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.setDrawFilter(mDrawFilter);

        mTopPath.reset();
        mBottomPath.reset();

        φ -= 0.1f;

        float y, y2;

        double ω = 2 * Math.PI / getWidth();

        mTopPath.moveTo(getLeft(), getBottom());
        mBottomPath.moveTo(getLeft(), getBottom());
        /**
         *  y=Asin(ωx+φ)+k
         *  A—振幅越大，波形在y轴上最大与最小值的差值越大
         *  ω—角速度， 控制正弦周期(单位角度内震动的次数)
         *  φ—初相，反映在坐标系上则为图像的左右移动。这里通过不断改变φ,达到波浪移动效果
         *  k—偏距，反映在坐标系上则为图像的上移或下移。
         */
        for (float x = 0; x <= getWidth(); x += 20) {
            y = (float) (8 * Math.cos(ω * x + φ) + 8);
            y2 = (float) (8 * Math.sin(ω * x + φ));
            mTopPath.lineTo(x, y);
            mBottomPath.lineTo(x, y2);
            //y坐标回调
            if (onWaveAnimationListener != null) {
                onWaveAnimationListener.OnWaveAnimation(y);
            }
        }
        mTopPath.lineTo(getRight(), getBottom());
        mBottomPath.lineTo(getRight(), getBottom());

        canvas.drawPath(mTopPath, mTopPaint);
        canvas.drawPath(mBottomPath, mBottomPaint);
        postInvalidateDelayed(50);
    }

    public void setOnWaveAnimationListener(OnWaveAnimationListener onWaveAnimationListener) {
        this.onWaveAnimationListener = onWaveAnimationListener;
    }

    public interface OnWaveAnimationListener {
        void OnWaveAnimation(float y);
    }
}


