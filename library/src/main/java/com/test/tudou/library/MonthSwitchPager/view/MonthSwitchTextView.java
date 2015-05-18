package com.test.tudou.library.MonthSwitchPager.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.test.tudou.library.R;
import java.util.ArrayList;

/**
 * Created by tudou on 15-5-18.
 */
public class MonthSwitchTextView extends RelativeLayout {

  @InjectView(android.R.id.icon1) ForegroundImageView mIconLeft;
  @InjectView(android.R.id.icon2) ForegroundImageView mIconRight;
  @InjectView(android.R.id.text1) TextView mTextTitle;

  private int mCurrentPostion;

  private ViewPager mViewPager;
  private ArrayList<String> mStrings;

  public MonthSwitchTextView(Context context) {
    this(context, null);
  }

  public MonthSwitchTextView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public MonthSwitchTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initialize(context, attrs, defStyleAttr);
  }

  private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
    LayoutInflater.from(context).inflate(R.layout.view_month_switch_text, this);
    ButterKnife.inject(this);
  }

  public void setData(ArrayList<String> maps) {
    //mMaps = maps;
    update();
  }

  private void updateView() {
    if (mCurrentPostion == 0) {
      mIconLeft.setVisibility(View.GONE);
    } else {
      mIconLeft.setVisibility(View.VISIBLE);
    }
    if (mCurrentPostion == mStrings.size() - 1) {
      mIconRight.setVisibility(View.GONE);
    } else {
      mIconRight.setVisibility(View.VISIBLE);
    }
    mTextTitle.setText(mStrings.get(mCurrentPostion));
  }

  private void update() {
    updateView();
  }

  @OnClick({
      android.R.id.icon1, android.R.id.icon2
  }) @SuppressWarnings("unused")
  public void onClick(View view) {
    switch (view.getId()) {
      case android.R.id.icon1:
        mCurrentPostion--;
        update();
        mViewPager.setCurrentItem(mCurrentPostion);
        break;
      case android.R.id.icon2:
        mCurrentPostion++;
        update();
        mViewPager.setCurrentItem(mCurrentPostion);
        break;
    }
  }

  public void setViewPager(ViewPager viewPager) {
    mViewPager = viewPager;
    setUpPagerListener();
  }

  private void setUpPagerListener() {
    mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override public void onPageSelected(int position) {
        mCurrentPostion = position;
        update();
      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });
  }
}
