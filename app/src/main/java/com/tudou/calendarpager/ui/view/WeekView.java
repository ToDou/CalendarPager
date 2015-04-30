package com.tudou.calendarpager.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.tudou.calendarpager.R;

/**
 * Created by tudou on 15-4-30.
 */
public class WeekView extends View {

  private Paint mPaintNormal;

  public WeekView(Context context) {
    this(context, null);
  }

  public WeekView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public WeekView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initPaint();
  }

  private void initPaint() {
    mPaintNormal = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaintNormal.setColor(getResources().getColor(android.R.color.darker_gray));
    mPaintNormal.setTextSize(26f);
  }

  protected void onDraw(Canvas canvas) {

    for (int i = 1; i < 8; i++) {
      String content = i + "";
      Paint.FontMetrics fontMetrics = mPaintNormal.getFontMetrics();
      //计算文字高度
      float fontHeight = fontMetrics.bottom - fontMetrics.top;
      //计算文字baseline
      float textWidth = mPaintNormal.measureText(content);
      float parentWidth = getWidth() - 2 * getResources().getDimension(R.dimen.activity_horizontal_margin);
      float textBaseY = getHeight() - (getHeight() - fontHeight) / 2 - fontMetrics.bottom;
      canvas.drawText(content, getResources().getDimension(R.dimen.activity_horizontal_margin) +  parentWidth / 7 * (i - 1) + parentWidth / 7 / 2 - textWidth / 2, textBaseY, mPaintNormal);
    }
  }
}
