/**
 * Created by YuGang Yang on April 01, 2015.
 * Copyright 2007-2015 Laputapp.com. All rights reserved.
 */
package com.tudou.calendarpager.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.test.tudou.library.WeekPager.adapter.WeekPagerAdapter;
import com.tudou.calendarpager.ui.fragment.SimpleFragment;

public class SimplePagerAdapter extends WeekPagerAdapter {

  public SimplePagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override protected Fragment createFragmentPager(int position) {
    return SimpleFragment.newInstance(mDays.get(position));
  }
}
