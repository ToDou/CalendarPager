package com.test.tudou.library.MonthSwitchPager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.test.tudou.library.MonthSwitchPager.adapter.MonthViewAdapter;
import com.test.tudou.library.R;
import com.test.tudou.library.model.CalendarDay;
import com.test.tudou.library.util.DayUtils;

/**
 * Created by tudou on 15-5-18.
 */
public class MonthSwitchPagerView extends LinearLayout implements MonthView.OnDayClickListener {

  @InjectView(android.R.id.text2) MonthSwitchTextView mSwitchText;
  @InjectView(android.R.id.content) MonthRecyclerView mRecyclerView;

  private MonthView.OnDayClickListener mOnDayClickListener;

  private MonthViewAdapter mMonthAdapter;

  public MonthSwitchPagerView(Context context) {
    this(context, null);
  }

  public MonthSwitchPagerView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public MonthSwitchPagerView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    initialize(context, attrs, defStyleAttr);
  }

  private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
    LayoutInflater.from(context).inflate(R.layout.view_month_switch_container, this);
    ButterKnife.inject(this);

    mMonthAdapter = new MonthViewAdapter(context, this);
    mSwitchText.setMonthRecyclerView(mRecyclerView);
    mRecyclerView.setMonthSwitchTextView(mSwitchText);
    mRecyclerView.setAdapter(mMonthAdapter);
  }

  public void setData(CalendarDay startDay, CalendarDay endDay) {
    mMonthAdapter.setData(startDay, endDay, null);
    mSwitchText.setDay(startDay, endDay);
  }

  public void setSelectDay(CalendarDay calendarDay) {
    mRecyclerView.scrollToPosition(DayUtils.calculateMonthPosition(mMonthAdapter.getStartDay(), calendarDay));
    mMonthAdapter.setSelectDay(calendarDay);
  }

  public void setOnDayClickListener(MonthView.OnDayClickListener onDayClickListener) {
    mOnDayClickListener = onDayClickListener;
  }

  @Override public void onDayClick(CalendarDay calendarDay) {
    mOnDayClickListener.onDayClick(calendarDay);
  }
}
