package com.strive.android.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 清风徐来 on 2016/11/7
 * 类说明:流式布局
 */
public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //测量wrap_content
        int width = 0;
        int height = 0;

        //记录每行的宽高
        int lineWidth = 0;
        int lineHeight = 0;

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            //首先测量子View,这样在后面可以通过子view的getMeasuredHeight,Width得到测量结果
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            //然后通过子View获取父容器的LayoutParams,View.getLayoutParams获取的是父容器的LayoutParams
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            //获取子View的宽高
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            //当当前的子View+行宽大于容器的宽度
            if (lineWidth + childWidth > widthSize) {
                width = Math.max(width, lineWidth);//换行之后
                lineWidth = childWidth;
                height += lineHeight;//高度叠加
                lineHeight = childHeight;
            } else {//不换行
                width += childWidth;
                height = Math.max(lineHeight, childHeight);
            }
            //特殊处理最后一个控件
            if (i == childCount - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }

        }
        setMeasuredDimension(
                widthMode == MeasureSpec.EXACTLY ? widthSize : width,
                heightMode == MeasureSpec.EXACTLY ? heightSize : height
        );

    }

    private List<List<View>> mAllViews = new ArrayList<>();//所有View,以行的形式存储
    private List<Integer> mLineHeights = new ArrayList<>();//所有的行高

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        mLineHeights.clear();

        int width = getWidth();//获取当前ViewGroup的宽度
        int lineWidth = 0;
        int lineHeight = 0;
        List<View> lineViews = new ArrayList<>();//存储每行的View
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            if (lineWidth + childWidth + lp.leftMargin + lp.rightMargin > width) {//需要换行
                mLineHeights.add(lineHeight);
                mAllViews.add(lineViews);
                //重置行宽与行高
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                lineViews = new ArrayList<>();//重置行views
            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.bottomMargin + lp.topMargin);
            lineViews.add(child);
        }
        //处理最后一行
        mLineHeights.add(lineHeight);
        mAllViews.add(lineViews);

        //设置子View的位置
        int totalLines = mAllViews.size();//行数
        int left = 0;
        int top = 0;//记录最原始位置
        for (int i = 0; i < totalLines; i++) {
            lineViews = mAllViews.get(i);//当前行的所有的View
            lineHeight = mLineHeights.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                if (child.getVisibility() == View.GONE) {//获取每个View的显示状态
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int lc = left + lp.leftMargin;//左
                int tc = top + lp.topMargin;//上
                int rc = lc + child.getMeasuredWidth();//右
                int bc = tc + child.getMeasuredHeight();//下
                child.layout(lc, tc, rc, bc);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;//左边坐标每次添加子View之后都需要改变
            }

            left = 0;//换行之后重置left
            top += lineHeight;//top累加行高
        }

    }

    /**
     * 当前ViewGroup对应的LayoutParams
     *
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
