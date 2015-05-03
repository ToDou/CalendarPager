package com.tudou.calendarpager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.test.tudou.library.model.CalendarDay;
import com.test.tudou.library.ui.adapter.WeekPagerAdapter;
import com.test.tudou.library.ui.adapter.WeekViewAdapter;
import com.test.tudou.library.ui.view.WeekDayViewPager;
import com.tudou.calendarpager.ui.adapter.SimplePagerAdapter;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity{

  private final static String TAG = "MainActivity";

  @InjectView(R.id.view_pager) WeekDayViewPager mViewPagerContent;
  @InjectView(R.id.header_recycler_view) RecyclerView mRecyclerView;
  @InjectView(R.id.text_day_label) TextView mTextView;

  private WeekPagerAdapter mPagerAdapter;
  private WeekViewAdapter mWeekViewAdapter;
  private static final int OFFSCREEN_PAGE_LIMIT = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);

    setUpPager();
  }

  private void setUpPager() {
    mPagerAdapter = new SimplePagerAdapter(getSupportFragmentManager());
    mViewPagerContent.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
    mViewPagerContent.setAdapter(mPagerAdapter);
    mViewPagerContent.setWeekRecyclerView(mRecyclerView);
    mWeekViewAdapter = new WeekViewAdapter(this, mViewPagerContent);
    mRecyclerView.setAdapter(mWeekViewAdapter);



    ArrayList<CalendarDay> reachAbleDays = new ArrayList<>();
    reachAbleDays.add(new CalendarDay(2015, 5, 1));
    reachAbleDays.add(new CalendarDay(2015, 5, 4));
    reachAbleDays.add(new CalendarDay(2015, 5, 6));
    reachAbleDays.add(new CalendarDay(2015, 5, 20));
    mWeekViewAdapter.setData(reachAbleDays.get(0), reachAbleDays.get(reachAbleDays.size() - 1), reachAbleDays);
    mPagerAdapter.setData(reachAbleDays.get(0), reachAbleDays.get(reachAbleDays.size() - 1));
  }
}
