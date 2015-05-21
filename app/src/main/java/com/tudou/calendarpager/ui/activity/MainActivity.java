package com.tudou.calendarpager.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tudou.calendarpager.R;

public class MainActivity extends ActionBarActivity{

  private final static String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);

  }

  @OnClick({
      R.id.btn_month_switch, R.id.btn_week_pager, R.id.btn_expand_calendar
  }) @SuppressWarnings("unused") public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_week_pager:
        startActivity(new Intent(this, WeekPagerActivity.class));
        break;
      case R.id.btn_month_switch:
        startActivity(new Intent(this, MonthSwitchActivity.class));
        break;
      case R.id.btn_expand_calendar:
        startActivity(new Intent(this, ExpandCalendarActivity.class));
        break;
    }
  }
}
