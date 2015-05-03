package com.tudou.calendarpager.ui.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.test.tudou.library.model.CalendarDay;
import com.test.tudou.library.ui.adapter.WeekViewAdapter;
import com.test.tudou.library.ui.view.WeekDayViewPager;
import com.test.tudou.library.ui.view.WeekRecyclerView;
import com.test.tudou.library.util.DayUtils;
import com.tudou.calendarpager.R;
import com.tudou.calendarpager.ui.adapter.SimplePagerAdapter;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements WeekDayViewPager.DayScrollListener {

  private final static String TAG = "MainActivity";

  @InjectView(R.id.view_pager) WeekDayViewPager mViewPagerContent;
  @InjectView(R.id.header_recycler_view) WeekRecyclerView mWeekRecyclerView;
  @InjectView(R.id.text_day_label) TextView mTextView;

  private SimplePagerAdapter mPagerAdapter;
  private WeekViewAdapter mWeekViewAdapter;
  private static final int OFFSCREEN_PAGE_LIMIT = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);
    setUpPager();
    setUpData();
  }


  private void setUpPager() {
    mPagerAdapter = new SimplePagerAdapter(getSupportFragmentManager());
    mViewPagerContent.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
    mViewPagerContent.setAdapter(mPagerAdapter);
    mViewPagerContent.setWeekRecyclerView(mWeekRecyclerView);
    mViewPagerContent.setDayScrollListener(this);
    mWeekViewAdapter = new WeekViewAdapter(this, mViewPagerContent);
    mWeekViewAdapter.setTextNormalColor(getResources().getColor(android.R.color.darker_gray));
    mWeekRecyclerView.setAdapter(mWeekViewAdapter);
  }

  private void setUpData() {
    ArrayList<CalendarDay> reachAbleDays = new ArrayList<>();
    reachAbleDays.add(new CalendarDay(2015, 5, 1));
    reachAbleDays.add(new CalendarDay(2015, 5, 4));
    reachAbleDays.add(new CalendarDay(2015, 5, 6));
    reachAbleDays.add(new CalendarDay(2015, 5, 20));
    mWeekViewAdapter.setData(reachAbleDays.get(0), reachAbleDays.get(reachAbleDays.size() - 1), null);
    mPagerAdapter.setData(reachAbleDays.get(0), reachAbleDays.get(reachAbleDays.size() - 1));
  }

  @Override public void onDayPageScrolled(int position, float positionOffset,
      int positionOffsetPixels) {
    mTextView.setText(DayUtils.formatEnglishTime(mPagerAdapter.getDatas().get(position).getTime()));
  }

  @Override public void onDayPageSelected(int position) {
  }

  @Override public void onDayPageScrollStateChanged(int state) {

  }
}
