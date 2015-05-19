package com.tudou.calendarpager.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.test.tudou.library.model.CalendarDay;
import com.test.tudou.library.WeekPager.adapter.WeekViewAdapter;
import com.test.tudou.library.WeekPager.view.WeekDayViewPager;
import com.test.tudou.library.WeekPager.view.WeekRecyclerView;
import com.test.tudou.library.util.DayUtils;
import com.tudou.calendarpager.R;
import com.tudou.calendarpager.ui.adapter.SimplePagerAdapter;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity{

  private final static String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);

  }

  @OnClick({
      R.id.btn_month_switch, R.id.btn_week_pager
  }) @SuppressWarnings("unused") public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_week_pager:
        startActivity(new Intent(this, WeekPagerActivity.class));
        break;
      case R.id.btn_month_switch:
        startActivity(new Intent(this, MonthSwitchActivity.class));
        break;
    }
  }
}
