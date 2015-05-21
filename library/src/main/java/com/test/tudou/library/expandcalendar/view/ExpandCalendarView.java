package com.test.tudou.library.expandcalendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.test.tudou.library.R;
import com.test.tudou.library.expandcalendar.adapter.MonthViewAdapter;
import com.test.tudou.library.model.CalendarDay;
import com.test.tudou.library.util.DayUtils;

/**
 * Created by tudou on 15-5-18.
 */
public class ExpandCalendarView extends LinearLayout implements ExpandCalendarMonthView.OnDayClickListener {

  @InjectView(android.R.id.content) ExpandMonthRecyclerView mRecyclerView;

  private ExpandCalendarMonthView.OnDayClickListener mOnDayClickListener;

  private MonthViewAdapter mMonthAdapter;

  public ExpandCalendarView(Context context) {
    this(context, null);
  }

  public ExpandCalendarView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ExpandCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    initialize(context, attrs, defStyleAttr);
  }

  private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
    LayoutInflater.from(context).inflate(R.layout.view_expand_calendar_view, this);
    ButterKnife.inject(this);

    mMonthAdapter = new MonthViewAdapter(context, this);
    mRecyclerView.setAdapter(mMonthAdapter);
  }

  public void setData(CalendarDay startDay, CalendarDay endDay) {
    mMonthAdapter.setData(startDay, endDay, null);
  }

  public void setSelectDay(CalendarDay calendarDay) {
    mRecyclerView.scrollToPosition(DayUtils.calculateMonthPosition(mMonthAdapter.getStartDay(), calendarDay));
    mMonthAdapter.setSelectDay(calendarDay);
  }

  public void setOnDayClickListener(ExpandCalendarMonthView.OnDayClickListener onDayClickListener) {
    mOnDayClickListener = onDayClickListener;
  }

  @Override public void onDayClick(CalendarDay calendarDay) {
    mOnDayClickListener.onDayClick(calendarDay);
  }
}
