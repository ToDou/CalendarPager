package com.tudou.calendarpager.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.test.tudou.library.MonthSwitchPager.view.MonthSwitchView;
import com.test.tudou.library.MonthSwitchPager.view.MonthView;
import com.test.tudou.library.model.CalendarDay;
import com.tudou.calendarpager.R;

/**
 * Created by tudou on 15-5-19.
 */
public class MonthSwitchActivity extends ActionBarActivity implements MonthView.OnDayClickListener {

  @InjectView(R.id.view_month) MonthSwitchView mMonthPagerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_month_switch);
    ButterKnife.inject(this);

    updateData();
  }

  private void updateData() {
    mMonthPagerView.setData(new CalendarDay(2015, 5, 4), new CalendarDay(2020, 12, 2));
    mMonthPagerView.setOnDayClickListener(this);
    mMonthPagerView.setSelectDay(new CalendarDay(2016, 10, 1));
  }

  @Override public void onDayClick(CalendarDay calendarDay) {
    Toast.makeText(this, calendarDay.getDayString(), Toast.LENGTH_SHORT).show();
  }
}
