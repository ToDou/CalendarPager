package com.tudou.calendarpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.TypedValue;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.tudou.calendarpager.ui.adapter.ContentPagerAdapter;
import com.tudou.calendarpager.ui.adapter.HeaderPagerAdapter;
import com.tudou.calendarpager.ui.view.HeaderViewPager;

public class MainActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener {

  @InjectView(R.id.view_pager) ViewPager mViewPagerContent;
  @InjectView(R.id.view_pager_sliding_tab) HeaderViewPager mHeaderViewPager;

  private ContentPagerAdapter mPagerAdapter;
  private HeaderPagerAdapter mHeaderPagerAdapter;
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

    mHeaderPagerAdapter = new HeaderPagerAdapter(this);
    mHeaderViewPager.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
    mHeaderViewPager.setAdapter(mHeaderPagerAdapter);


    mViewPagerContent.setOnPageChangeListener(this);
  }

  @Override public void onPageScrolled(int position, float positionOffset,
      int positionOffsetPixels) {
    mHeaderViewPager.onContentPageScrolled(position, positionOffset, positionOffsetPixels);
  }

  @Override public void onPageSelected(int position) {
    mHeaderViewPager.onContentPageSelected(position);
  }

  @Override public void onPageScrollStateChanged(int state) {
    mHeaderViewPager.onContentPageScrollStateChanged(state);
  }
}
