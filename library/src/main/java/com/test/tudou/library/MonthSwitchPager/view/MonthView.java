package com.test.tudou.library.MonthSwitchPager.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.test.tudou.library.R;
import com.test.tudou.library.model.CalendarDay;
import com.test.tudou.library.util.DayUtils;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by tudou on 15-5-18.
 */
public class MonthView extends View {
  private final static String TAG = "MonthView";
  private final static int DAY_IN_WEEK = 7;
  private final static float DAY_IN_MONTH_PADDING_VERTICAL = 6.0f;
  private final static int DEFAULT_HEIGHT = 32;
  protected static final int DEFAULT_NUM_ROWS = 6;

  private ArrayList<CalendarDay> mDays;
  private CalendarDay mFirstDay;
  private int mMonthPosition;

  private Paint mPaintNormal;
  private int mTextNormalColor;
  protected int mRowHeight = DEFAULT_HEIGHT;
  private int mNumRows = DEFAULT_NUM_ROWS;


  public MonthView(Context context) {
    this(context, null);
  }

  public MonthView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public MonthView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initData();
    initPaint();
  }

  private void initPaint() {
    mTextNormalColor = getResources().getColor(R.color.text_color_normal);
    mPaintNormal = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaintNormal.setColor(mTextNormalColor);
    mPaintNormal.setTextSize(getResources().getDimension(R.dimen.si_default_text_size));
  }

  private void initData() {
    mDays = new ArrayList<>();
    mRowHeight = getResources().getDimensionPixelSize(R.dimen.default_month_row_height);
  }

  public void setFirstDay(CalendarDay calendarDay) {
    mFirstDay = calendarDay;
  }

  public void setMonthPosition(int position) {
    mMonthPosition = position;
    createDays();
    invalidate();
  }

  private void createDays() {
    mDays.clear();
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(mFirstDay.getTime());
    calendar.add(Calendar.MONTH, mMonthPosition);
    int position = calendar.get(Calendar.DAY_OF_MONTH);
    calendar.roll(Calendar.DAY_OF_MONTH, -(position - 1));
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    Log.e(TAG, month + " yue " + year);
    int daysNum = DayUtils.getDaysInMonth(month, year);
    for (int i = 0; i < daysNum; i++) {
      mDays.add(new CalendarDay(calendar));
      calendar.roll(Calendar.DAY_OF_MONTH, 1);
    }
  }

  @Override protected void onDraw(Canvas canvas) {
    if (mDays.size() < 28) {
      super.onDraw(canvas);
      return;
    }

    int rowNum = 0;
    for (int i = 0; i < mDays.size(); i++) {
      CalendarDay calendarDay = mDays.get(i);
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(calendarDay.getTime());
      int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
      String content = String.valueOf(calendarDay.day);
      Paint.FontMetrics fontMetrics = mPaintNormal.getFontMetrics();
      float fontHeight = fontMetrics.bottom - fontMetrics.top;
      float textWidth = mPaintNormal.measureText(content);
      float parentWidth = getWidth() - 2 * getResources().getDimension(R.dimen.activity_horizontal_margin);
      float y = mRowHeight  * rowNum + mRowHeight - (mRowHeight - fontHeight) / 2 - fontMetrics.bottom;
      float x = getResources().getDimension(R.dimen.activity_horizontal_margin)
          + parentWidth / DAY_IN_WEEK * (weekDay - 1)
          + parentWidth / DAY_IN_WEEK / 2 - textWidth / 2;

      Log.e(TAG, "i :  " + i + "   weekday: " + weekDay + "      rownum: " + rowNum + "   y: " + y);
      canvas.drawText(content, x, y, mPaintNormal);

      if (weekDay == 7) rowNum++;
    }
  }

  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), mRowHeight * mNumRows);
  }

  public void reuse() {
    mNumRows = DEFAULT_NUM_ROWS;
    requestLayout();
  }

  public static int px2dip(Context context, float pxValue){
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int)(pxValue / scale + 0.5f);
  }
}
