package com.tudou.calendarpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.test.tudou.library.model.CalendarDay;
import com.test.tudou.library.ui.adapter.WeekPagerAdapter;
import com.test.tudou.library.ui.adapter.WeekViewAdapter;
import com.test.tudou.library.ui.view.WeekDayViewPager;
import com.test.tudou.library.ui.view.WeekView;
import com.test.tudou.library.util.DayUtils;
import com.tudou.calendarpager.ui.adapter.SimplePagerAdapter;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener {

  private final static String TAG = "MainActivity";
  public static int LIST_TOP_OFFSET = -1;

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
    mViewPagerContent.setOnPageChangeListener(this);


    LinearLayoutManager manager = new LinearLayoutManager(this);
    manager.setOrientation(LinearLayoutManager.HORIZONTAL);
    mRecyclerView.setLayoutManager(manager);
    mWeekViewAdapter = new WeekViewAdapter(this, mViewPagerContent);
    mRecyclerView.setAdapter(mWeekViewAdapter);

    //, new CalendarDay(2015, 5, 1), new CalendarDay(2015, 5, 19),
    mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        adjustPosition(recyclerView, newState);
      }

      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

      }
    });

    ArrayList<CalendarDay> calendarDays = new ArrayList<>();
    calendarDays.add(new CalendarDay(2015, 5, 1));
    calendarDays.add(new CalendarDay(2015, 5, 4));
    calendarDays.add(new CalendarDay(2015, 5, 6));
    calendarDays.add(new CalendarDay(2015, 5, 20));
    mWeekViewAdapter.setData(calendarDays.get(0), calendarDays.get(calendarDays.size() - 1), calendarDays);
    mPagerAdapter.setData(calendarDays.get(0), calendarDays.get(calendarDays.size() - 1));
  }

  private void adjustPosition(RecyclerView recyclerView, int newState) {
    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
      int i = 0;
      View child = recyclerView.getChildAt(i);
      while (child != null && child.getRight() <= 0) {
        child = recyclerView.getChildAt(++i);
      }
      if (child == null) {
        // The view is no longer visible, just return
        return;
      }
      final int left = child.getLeft();
      final int right = child.getRight();
      final int midpoint = recyclerView.getWidth() / 2;
      if (left < LIST_TOP_OFFSET) {
        if (right > midpoint) {
          recyclerView.smoothScrollBy(left, 0);
        } else {
          recyclerView.smoothScrollBy(right, 0);
        }
      }
    }
  }

  @Override public void onPageScrolled(int position, float positionOffset,
      int positionOffsetPixels) {
    int i = 0;
    View child = mRecyclerView.getChildAt(i);

    if (child == null) return;
    while (child != null && child.getRight() <= 0) {
      child = mRecyclerView.getChildAt(++i);
    }
    ((WeekView) child).onViewPageScroll(position, positionOffset, positionOffsetPixels);
    mTextView.setText(DayUtils.formatEnglishTime(mPagerAdapter.getDatas().get(position).getTime()));
  }

  @Override public void onPageSelected(int position) {
    mRecyclerView.smoothScrollToPosition(position / 7);
    for (int j = 0; j < mRecyclerView.getChildCount(); j++) {
      View week = mRecyclerView.getChildAt(j);
      if (week instanceof WeekView) {
        week.invalidate();
      }
    }

  }

  @Override public void onPageScrollStateChanged(int state) {

  }
}
