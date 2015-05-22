package com.test.tudou.library.expandcalendar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.test.tudou.library.R;
import java.text.DateFormatSymbols;

/**
 * Created by tudou on 15-5-22.
 */
public class WeekLabelView extends View {

  private final static int DAY_IN_WEEK = 7;

  private Paint mPaint;
  private int mTextColor;
  private float mTextSize;
  private int mRowHeight;

  public WeekLabelView(Context context) {
    this(context, null);
  }

  public WeekLabelView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public WeekLabelView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    initPaint();
    initData();
  }

  private void initData() {
    mRowHeight = getResources().getDimensionPixelSize(R.dimen.default_month_row_height);
  }

  private void initPaint() {
    mTextColor = getResources().getColor(R.color.text_color_normal);
    mTextSize = getResources().getDimension(R.dimen.si_default_text_size);

    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setColor(mTextColor);
    mPaint.setTextSize(mTextSize);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
        mRowHeight);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawWeekLable(canvas);
  }

  private void drawWeekLable(Canvas canvas) {
    String[] weeks = DateFormatSymbols.getInstance().getShortWeekdays();
    for (int i = 0; i < weeks.length; i++) {
      String content = String.valueOf(weeks[i]);
      Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
      float fontHeight = fontMetrics.bottom - fontMetrics.top;
      float textWidth = mPaint.measureText(content);
      float parentWidth = getWidth() - 2 * getResources().getDimension(R.dimen.activity_horizontal_margin);
      float y = mRowHeight - (mRowHeight - fontHeight) / 2 - fontMetrics.bottom;
      float x = getResources().getDimension(R.dimen.activity_horizontal_margin)
          + parentWidth / DAY_IN_WEEK * (i - 1)
          + parentWidth / DAY_IN_WEEK / 2 - textWidth / 2;
      canvas.drawText(content, x, y, mPaint);
    }
  }

}
