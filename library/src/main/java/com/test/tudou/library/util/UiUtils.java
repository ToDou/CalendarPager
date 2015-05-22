package com.test.tudou.library.util;

import android.graphics.Paint;

/**
 * Created by tudou on 15-5-22.
 */
public class UiUtils {

  public static float calculateExpandMinHeight(float textSize, int rowHeight) {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setTextSize(textSize);
    Paint.FontMetrics fontMetrics = paint.getFontMetrics();
    float fontHeight = fontMetrics.bottom - fontMetrics.top;
    return rowHeight * 2 - fontHeight;
  }
}
