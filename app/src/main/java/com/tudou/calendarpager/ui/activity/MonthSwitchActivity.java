package com.tudou.calendarpager.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import butterknife.ButterKnife;
import com.tudou.calendarpager.R;

/**
 * Created by tudou on 15-5-19.
 */
public class MonthSwitchActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_month_switch);
    ButterKnife.inject(this);

  }


}
