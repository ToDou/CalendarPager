package com.test.tudou.library.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import com.test.tudou.library.model.CalendarDay;

/**
 * Created by tudou on 15-5-1.
 */
public class WeekDayViewPager extends ViewPager implements WeekView.OnDayClickListener {

  private RecyclerView mRecyclerView;

  public WeekDayViewPager(Context context) {
    this(context, null);
  }

  public WeekDayViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public void onDayClick(WeekView simpleMonthView, CalendarDay calendarDay, int position) {
    setCurrentItem(position);
  }

  public void setWeekRecyclerView(RecyclerView recyclerView) {
    mRecyclerView = recyclerView;
    updateListener();
  }

  private void updateListener() {
    setOnPageChangeListener(new OnPageChangeListener() {
      @Override public void onPageScrolled(int position, float positionOffset,
          int positionOffsetPixels) {
        int i = 0;
        View child = mRecyclerView.getChildAt(i);

        if (child == null) return;
        while (child != null && child.getRight() <= 0) {
          child = mRecyclerView.getChildAt(++i);
        }
        ((WeekView) child).onViewPageScroll(position, positionOffset, positionOffsetPixels);
        //mTextView.setText(DayUtils.formatEnglishTime(mPagerAdapter.getDatas().get(position).getTime()));
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
    });
  }


}
