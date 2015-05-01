package com.tudou.calendarpager;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.tudou.calendarpager.model.CalendarDay;
import com.tudou.calendarpager.ui.adapter.ContentPagerAdapter;
import com.tudou.calendarpager.ui.adapter.HeaderPagerAdapter;
import com.tudou.calendarpager.ui.adapter.WeekViewAdapter;
import com.tudou.calendarpager.ui.view.HeaderViewPager;

public class MainActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener {

  private final static String TAG = "MainActivity";

  @InjectView(R.id.view_pager) ViewPager mViewPagerContent;
  //@InjectView(R.id.view_pager_header) HeaderViewPager mHeaderViewPager;
  @InjectView(R.id.header_recycler_view) RecyclerView mRecyclerView;

  private ContentPagerAdapter mPagerAdapter;
  private WeekViewAdapter mWeekViewAdapter;
  //private HeaderPagerAdapter mHeaderAdapter;
  private static final int OFFSCREEN_PAGE_LIMIT = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);

    setUpPager();
  }

  private void setUpPager() {
    mPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager());
    mViewPagerContent.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
    final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
        getResources().getDisplayMetrics());
    mViewPagerContent.setPageMargin(pageMargin);
    mViewPagerContent.setAdapter(mPagerAdapter);
    /*mDayViewAdapter = new DayViewAdapter(this);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
    mRecyclerView.setLayoutManager(linearLayoutManager);
    mRecyclerView.setAdapter(mDayViewAdapter);*/

    mViewPagerContent.setOnPageChangeListener(this);

    /*mHeaderAdapter = new HeaderPagerAdapter(this);
    mHeaderViewPager.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
    mHeaderViewPager.setPageMargin(pageMargin);
    mHeaderViewPager.setAdapter(mHeaderAdapter);
    mHeaderAdapter.setViewPager(mViewPagerContent);*/

    LinearLayoutManager manager = new LinearLayoutManager(this);
    manager.setOrientation(LinearLayoutManager.HORIZONTAL);
    mRecyclerView.setLayoutManager(manager);
    mWeekViewAdapter = new WeekViewAdapter(this, new CalendarDay(2015, 5, 1), new CalendarDay(2015, 5, 19));
    mRecyclerView.setAdapter(mWeekViewAdapter);
  }

  @Override public void onPageScrolled(int position, float positionOffset,
      int positionOffsetPixels) {
    mRecyclerView.smoothScrollToPosition(position / 7);
   /* mHeaderViewPager.onContentPageScrolled(position, positionOffset, positionOffsetPixels);
    mHeaderAdapter.setSelectDay(position);*/
   /* Log.e(TAG, "position: "
        + position
        + "      positionOffset: "
        + positionOffset
        + "     positionOffsetPicxels: "
        + positionOffsetPixels);*/

    if ((position + 1) % 7 == 0 && (position + 1) / 7 > 0) {
//      mRecyclerView.smoothScrollBy(position / 6 * positionOffsetPixels + positionOffsetPixels, 0);
      //mRecyclerView.smoothScrollToPosition(position / 6 );
    }
  }

  @Override public void onPageSelected(int position) {
    //mHeaderViewPager.onContentPageSelected(position);
  }

  @Override public void onPageScrollStateChanged(int state) {
    //mHeaderViewPager.onContentPageScrollStateChanged(state);
  }

}
