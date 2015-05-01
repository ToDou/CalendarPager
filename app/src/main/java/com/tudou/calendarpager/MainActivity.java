package com.tudou.calendarpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.tudou.calendarpager.model.CalendarDay;
import com.tudou.calendarpager.ui.adapter.ContentPagerAdapter;
import com.tudou.calendarpager.ui.adapter.WeekViewAdapter;
import com.tudou.calendarpager.ui.view.WeekView;

public class MainActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener {

  private final static String TAG = "MainActivity";
  public static int LIST_TOP_OFFSET = -1;

  @InjectView(R.id.view_pager) ViewPager mViewPagerContent;
  @InjectView(R.id.header_recycler_view) RecyclerView mRecyclerView;

  private ContentPagerAdapter mPagerAdapter;
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
    mPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager(), new CalendarDay(2015, 5, 1), new CalendarDay(2015, 5, 19));
    mViewPagerContent.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
    final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
        getResources().getDisplayMetrics());
    mViewPagerContent.setPageMargin(pageMargin);
    mViewPagerContent.setAdapter(mPagerAdapter);
    mViewPagerContent.setOnPageChangeListener(this);

    LinearLayoutManager manager = new LinearLayoutManager(this);
    manager.setOrientation(LinearLayoutManager.HORIZONTAL);
    mRecyclerView.setLayoutManager(manager);
    mWeekViewAdapter = new WeekViewAdapter(this, new CalendarDay(2015, 5, 1), new CalendarDay(2015, 5, 19));
    mRecyclerView.setAdapter(mWeekViewAdapter);
    mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        adjustPosition(recyclerView, newState);

      }

      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

      }
    });
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
    Log.e(TAG, "position: "
        + position
        + "      positionOffset: "
        + positionOffset
        + "     positionOffsetPicxels: "
        + positionOffsetPixels);
    int i = 0;
    View child = mRecyclerView.getChildAt(i);
    while (child != null && child.getRight() <= 0) {
      child = mRecyclerView.getChildAt(++i);
    }
    ((WeekView) child).onViewPageScroll(position, positionOffset, positionOffsetPixels);
  }

  @Override public void onPageSelected(int position) {
    mRecyclerView.smoothScrollToPosition(position / 7);
  }

  @Override public void onPageScrollStateChanged(int state) {

  }
}
