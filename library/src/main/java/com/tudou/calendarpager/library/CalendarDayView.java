package com.tudou.calendarpager.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tudou on 15-3-31.
 */
public class CalendarDayView extends View {

    private Paint mWeekDayPaint;
    private Paint mMonthDayPaint;
    private Path mMonthDayCircle;

    private int mMonthDayCircleColor = getResources().getColor(android.R.color.holo_green_dark);
    private int mWeekDayColor = getResources().getColor(android.R.color.darker_gray);
    private int mMonthDaySelectColor = getResources().getColor(android.R.color.white);
    private int mMonthDayNormalColor = getResources().getColor(android.R.color.black);
    private int mMonthDayUnReachableColor = getResources().getColor(android.R.color.darker_gray);
    private int mMonthDayColor;

    private float mWeekDayTextSize = 14f;
    private float mMonthDayTextSize = 14f;

    private float currentOffset = 1.0f;

    private boolean unReachable = false;

    private String week;
    private String day;

    public CalendarDayView(Context context) {
        this(context, null);
    }

    public CalendarDayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarDayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPaint();
    }

    private void initPaint() {
        mMonthDayColor = mMonthDayNormalColor;
        mMonthDayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWeekDayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMonthDayPaint.setColor(mMonthDayColor);
        mMonthDayPaint.setTextSize(mMonthDayTextSize);
        mWeekDayPaint.setColor(mWeekDayColor);
        mWeekDayPaint.setTextSize(mWeekDayTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        setMeasuredDimension(screenWidth, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint.FontMetrics fontMetrics = mWeekDayPaint.getFontMetrics();
        //计算文字高度
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
        //计算文字baseline
        float textWidth = mWeekDayPaint.measureText(week);
        float textBaseY = getHeight() - (getHeight() - fontHeight)/2 - fontMetrics.bottom;
        canvas.drawText(week, getWidth() / 2 - textWidth / 2, textBaseY, mWeekDayPaint);

        calculatePaintColor();

        fontMetrics = mWeekDayPaint.getFontMetrics();
        //计算文字高度
        fontHeight = fontMetrics.bottom - fontMetrics.top;
        //计算文字baseline
        textWidth = mWeekDayPaint.measureText(week);
        textBaseY = getHeight() - (getHeight() - fontHeight)/2 - fontMetrics.bottom;
        canvas.drawText(week, getWidth() / 2 - textWidth / 2, textBaseY, mWeekDayPaint);

    }

    private void calculatePaintColor() {
        if (!unReachable) {
            mMonthDayColor = calculateGradientColor(mMonthDaySelectColor, mMonthDayNormalColor, currentOffset);
        } else {
            mMonthDayColor = mMonthDayUnReachableColor;
        }
        mMonthDayPaint.setColor(mMonthDayColor);

    }

    private int calculateGradientColor(int startColor, int endColor, float offset) {
        int r0 = (startColor >> 16) & 0xff;
        int r1 = (endColor >> 16) & 0xff;
        int g0 = (startColor >> 8) & 0xff;
        int g1 = (endColor >> 8) & 0xff;
        int b0 = startColor & 0xff;
        int b1 = endColor & 0xff;

        int r2 = (int)(r0 * (1 - offset) + r1 * offset);
        int g2 = (int)(g0 * (1 - offset) + g1 * offset);
        int b2 = (int)(b0 * (1 - offset) + b1 * offset);

        return Color.argb(255, r2, g2, b2);
    }

    public void setOffset(float offset) {
        this.currentOffset = offset;
        invalidate();
    }



}
