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
import java.util.ArrayList;
import java.util.Calendar;

public class ContentPagerAdapter extends FragmentPagerAdapter {

  private CalendarDay mStartDay;
  private CalendarDay mEndDay;

  private ArrayList<CalendarDay> mDays;
  private int mCount;

  public ContentPagerAdapter(FragmentManager fm, CalendarDay startDay, CalendarDay endDay) {
    super(fm);

    mStartDay = startDay;
    mEndDay = endDay;
    mCount = DayUtils.calculateWeekCount(mStartDay, mEndDay) * DayUtils.DAY_IN_WEEK;
    mDays = new ArrayList<>(mCount);
    createWeekCalendardays();
  }

  private void createWeekCalendardays() {
    mDays.clear();
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(DayUtils.calculateFirstShowDay(mStartDay).getTime());
    for (int i = 0; i < mCount; i++) {
      mDays.add(new CalendarDay(calendar));
      calendar.roll(Calendar.DAY_OF_YEAR, 1);
    }
  }

  @Override public Fragment getItem(int position) {
    return SimpleFragment.newInstance(mDays.get(position));
  }

  @Override public int getCount() {
    return mCount;
  }

  @Override public CharSequence getPageTitle(int position) {
    switch (position) {


      default:
        return "";
    }
  }
}
