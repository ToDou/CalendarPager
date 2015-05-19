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

/**
 * Created by tudou on 15-5-18.
 */
public class MonthSwitchPagerView extends LinearLayout {

  @InjectView(android.R.id.text2) MonthSwitchTextView mSwitchText;
  @InjectView(android.R.id.content) MonthRecyclerView mRecyclerView;

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

    mMonthAdapter = new MonthViewAdapter(context);
    mMonthAdapter.setData(new CalendarDay(2015, 5, 6), new CalendarDay(2017, 5, 5), null);
    mSwitchText.setDay(new CalendarDay(2015, 5, 6), new CalendarDay(2017, 5, 5));
    mSwitchText.setMonthRecyclerView(mRecyclerView);
    mRecyclerView.setMonthSwitchTextView(mSwitchText);
    mRecyclerView.setAdapter(mMonthAdapter);
  }
}
