/**
 * Created by YuGang Yang on April 01, 2015.
 * Copyright 2007-2015 Laputapp.com. All rights reserved.
 */
package com.tudou.calendarpager.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.tudou.calendarpager.model.CalendarDay;
import com.tudou.calendarpager.ui.fragment.SimpleFragment;
import com.tudou.calendarpager.util.DayUtils;

public class ContentPagerAdapter extends FragmentPagerAdapter {

  private CalendarDay mStartDay;
  private CalendarDay mEndDay;

  public ContentPagerAdapter(FragmentManager fm, CalendarDay startDay, CalendarDay endDay) {
    super(fm);

    mStartDay = startDay;
    mEndDay = endDay;
  }

  @Override public Fragment getItem(int position) {
    return SimpleFragment.newInstance("This is the page: " + (position + 1));
  }

  @Override public int getCount() {
    return DayUtils.calculateWeekCount(mStartDay, mEndDay) * DayUtils.DAY_IN_WEEK;
  }

  @Override public CharSequence getPageTitle(int position) {
    switch (position) {


      default:
        return "";
    }
  }
}
