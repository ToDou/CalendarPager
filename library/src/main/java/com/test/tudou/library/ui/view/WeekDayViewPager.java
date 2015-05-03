package com.test.tudou.library.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import com.test.tudou.library.model.CalendarDay;

/**
 * Created by tudou on 15-5-1.
 */
public class WeekDayViewPager extends ViewPager implements WeekView.OnDayClickListener {

  public WeekDayViewPager(Context context) {
    this(context, null);
  }

  public WeekDayViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public void onDayClick(WeekView simpleMonthView, CalendarDay calendarDay, int position) {
    setCurrentItem(position);
  }
}
