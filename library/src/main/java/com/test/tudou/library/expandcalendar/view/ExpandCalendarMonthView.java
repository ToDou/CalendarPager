package com.test.tudou.library.expandcalendar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.test.tudou.library.R;
import com.test.tudou.library.model.CalendarDay;
import com.test.tudou.library.util.DayUtils;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by tudou on 15-5-18.
 */
public class ExpandCalendarMonthView extends View {
  private final static int DAY_IN_WEEK = 7;
  private final static float DAY_IN_MONTH_PADDING_VERTICAL = 6.0f;
  private final static int DEFAULT_HEIGHT = 32;
  protected static final int DEFAULT_NUM_ROWS = 7;

  private ArrayList<CalendarDay> mDays;
  private CalendarDay mFirstDay;
  private CalendarDay mSelectDay;
  private int mMonthPosition;

  private Paint mPaintNormal;
  private Paint mPaintSelect;
  private int mCircleColor;
  private int mTextNormalColor;
  private int mTextSelectColor;
  protected int mRowHeight = DEFAULT_HEIGHT;
  private int mNumRows = DEFAULT_NUM_ROWS;
  private int rowNum;

  private OnDayClickListener mOnDayClickListener;


  public ExpandCalendarMonthView(Context context) {
    this(context, null);
  }

  public ExpandCalendarMonthView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ExpandCalendarMonthView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initData();
    initPaint();
  }

  private void initPaint() {
    mTextNormalColor = getResources().getColor(R.color.text_color_normal);
    mTextSelectColor = getResources().getColor(android.R.color.white);
    mCircleColor = getResources().getColor(R.color.color_18ffff);

    mPaintNormal = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaintNormal.setColor(mTextNormalColor);
    mPaintNormal.setTextSize(getResources().getDimension(R.dimen.si_default_text_size));

    mPaintSelect = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaintSelect.setColor(mCircleColor);
    mPaintSelect.setStyle(Paint.Style.FILL);

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

  public void setSelectDay(CalendarDay calendarDay) {
    mSelectDay = calendarDay;
  }

  private void createDays() {
    mDays.clear();
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(mFirstDay.getTime());
    int position = calendar.get(Calendar.DAY_OF_MONTH);
    calendar.roll(Calendar.DAY_OF_MONTH, -(position - 1));
    calendar.add(Calendar.MONTH, mMonthPosition);
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int daysNum = DayUtils.getDaysInMonth(month, year);
    for (int i = 0; i < daysNum; i++) {
      mDays.add(new CalendarDay(calendar));
      calendar.roll(Calendar.DAY_OF_MONTH, 1);
    }
    calculateRowNum();
  }

  @Override protected void onDraw(Canvas canvas) {
    if (mDays.size() < 28) {
      super.onDraw(canvas);
      return;
    }
    rowNum = 0;
    drawYearMonthLable(canvas);
    drawWeekLable(canvas);
    drawMonthNum(canvas);
  }

  private void drawYearMonthLable(Canvas canvas) {
    CalendarDay calendarDay = mDays.get(0);
    int flags =
        DateUtils.FORMAT_NO_MONTH_DAY + DateUtils.FORMAT_SHOW_DATE + DateUtils.FORMAT_SHOW_YEAR;
    String content = DateUtils.formatDateTime(getContext(), calendarDay.getTime(), flags);
    Paint.FontMetrics fontMetrics = mPaintNormal.getFontMetrics();
    float fontHeight = fontMetrics.bottom - fontMetrics.top;
    float textWidth = mPaintNormal.measureText(content);
    float parentWidth =
        getWidth() - 2 * getResources().getDimension(R.dimen.activity_horizontal_margin);
    float y = mRowHeight * rowNum + mRowHeight - (mRowHeight - fontHeight) / 2 - fontMetrics.bottom;
    float x = getResources().getDimension(R.dimen.activity_horizontal_margin) + parentWidth / 2 - textWidth / 2;
    mPaintNormal.setColor(mTextNormalColor);
    canvas.drawText(content, x, y, mPaintNormal);
    rowNum++;
  }

  private void drawWeekLable(Canvas canvas) {
    String[] weeks = DateFormatSymbols.getInstance().getShortWeekdays();
    for (int i = 0; i < weeks.length; i++) {

      String content = String.valueOf(weeks[i]);
      Paint.FontMetrics fontMetrics = mPaintNormal.getFontMetrics();
      float fontHeight = fontMetrics.bottom - fontMetrics.top;
      float textWidth = mPaintNormal.measureText(content);
      float parentWidth = getWidth() - 2 * getResources().getDimension(R.dimen.activity_horizontal_margin);
      float y = mRowHeight  * rowNum + mRowHeight - (mRowHeight - fontHeight) / 2 - fontMetrics.bottom;
      float x = getResources().getDimension(R.dimen.activity_horizontal_margin)
          + parentWidth / DAY_IN_WEEK * (i - 1)
          + parentWidth / DAY_IN_WEEK / 2 - textWidth / 2;
      mPaintNormal.setColor(mTextNormalColor);
      canvas.drawText(content, x, y, mPaintNormal);
    }
    rowNum++;
  }

  private void drawMonthNum(Canvas canvas) {
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

      //Log.e(TAG, "i :  " + i + "   weekday: " + weekDay + "      rownum: " + rowNum + "   y: " + y);
      if (mSelectDay.getDayString().equals(calendarDay.getDayString())) {
        canvas.drawCircle(getResources().getDimension(R.dimen.activity_horizontal_margin)
                + parentWidth / DAY_IN_WEEK * (weekDay - 1)
                + parentWidth / DAY_IN_WEEK / 2, mRowHeight  * rowNum + mRowHeight / 2, mRowHeight * 2 / 4, mPaintSelect
        );
        mPaintNormal.setColor(mTextSelectColor);
        canvas.drawText(content, x, y, mPaintNormal);
      } else {
        mPaintNormal.setColor(mTextNormalColor);
        canvas.drawText(content, x, y, mPaintNormal);
      }

      if (weekDay == 7) rowNum++;
    }
  }

  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
        mRowHeight * mNumRows + mRowHeight / 2);
  }

  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_UP) {
      CalendarDay calendarDay = getDayFromLocation(event.getX(), event.getY());
      if (calendarDay != null) {
        mOnDayClickListener.onDayClick(calendarDay);
      }
    }
    return true;
  }

  private void calculateRowNum() {
    mNumRows = 0;
    int row = 2;
    for (int i = 0; i < mDays.size(); i++) {
      CalendarDay calendarDay = mDays.get(i);
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(calendarDay.getTime());
      int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
      if (weekDay == 7) row++;
      if (i == mDays.size() - 1) {
        mNumRows = row + 1;
      }
    }
  }

  public CalendarDay getDayFromLocation(float x, float y) {
    int padding = getContext().getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
    if (x < padding) {
      return null;
    }

    if (x > getWidth() - padding) {
      return null;
    }

    if (y < mRowHeight * 2 || y > (rowNum + 1) * mRowHeight) {
      return null;
    }

    int yDay = (int) (y - mRowHeight * 2) / mRowHeight;

    int xday = (int) ((x - padding) / ((getWidth() - padding * 2) / DAY_IN_WEEK));

    int position = yDay * DAY_IN_WEEK + xday;

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(mFirstDay.getTime());
    int monthPosition = calendar.get(Calendar.DAY_OF_MONTH);
    calendar.roll(Calendar.DAY_OF_MONTH, -(monthPosition - 1));
    calendar.add(Calendar.MONTH, mMonthPosition);

    position = position - calendar.get(Calendar.DAY_OF_WEEK) + 1;
    if (position < 0 || position > mDays.size() - 1) return null;
    return mDays.get(position);
  }

  public void setOnDayClickListener(OnDayClickListener onDayClickListener) {
    mOnDayClickListener = onDayClickListener;
  }

  public interface OnDayClickListener {
    void onDayClick(CalendarDay calendarDay);
  }

}
