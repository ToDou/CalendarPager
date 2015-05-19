package com.test.tudou.library.MonthSwitchPager.view;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.test.tudou.library.R;
import com.test.tudou.library.model.CalendarDay;
import com.test.tudou.library.util.DayUtils;
import java.util.Calendar;

/**
 * Created by tudou on 15-5-18.
 */
public class MonthSwitchTextView extends RelativeLayout {

  private final static String TAG = "MonthSwitchTextView";

  @InjectView(android.R.id.icon1) ForegroundImageView mIconLeft;
  @InjectView(android.R.id.icon2) ForegroundImageView mIconRight;
  @InjectView(android.R.id.text1) TextView mTextTitle;

  private int mPosition;
  private CalendarDay mFirstDay;
  private int mCount;
  private MonthRecyclerView mMonthRecyclerView;
  private int mPrePosition;

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

  private void updateView() {
    if (mPosition == 0) {
      mIconLeft.setVisibility(View.GONE);
    } else {
      mIconLeft.setVisibility(View.VISIBLE);
    }
    if (mPosition == mCount - 1) {
      mIconRight.setVisibility(View.GONE);
    } else {
      mIconRight.setVisibility(View.VISIBLE);
    }
    updateText();
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
        mPosition--;
        update();
        mMonthRecyclerView.scrollToPosition(mPosition);
        break;
      case android.R.id.icon2:
        mPosition++;
        update();
        mMonthRecyclerView.scrollToPosition(mPosition);
        break;
    }
  }

  public void setPosition(int position) {
    mPosition = position;
    update();
  }

  public void setDay(CalendarDay startDay, CalendarDay endDay) {
    mFirstDay = startDay;
    mCount = DayUtils.calculateMonthCount(startDay, endDay);
    update();
  }

  public void setMonthRecyclerView(MonthRecyclerView recyclerView) {
    mMonthRecyclerView = recyclerView;
  }

  private void updateText() {
    if (mPrePosition == mPosition && mPrePosition != 0) {
      return;
    }
    mPrePosition = mPosition;

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(mFirstDay.getTime());
    int position = calendar.get(Calendar.DAY_OF_MONTH);
    calendar.add(Calendar.DAY_OF_MONTH, -(position - 1));
    calendar.add(Calendar.MONTH, mPosition);
    int flags =
        DateUtils.FORMAT_NO_MONTH_DAY + DateUtils.FORMAT_SHOW_DATE + DateUtils.FORMAT_SHOW_YEAR;
    Log.e(TAG, DateUtils.formatDateTime(getContext(), calendar.getTimeInMillis(), flags));
    mTextTitle.setText(DateUtils.formatDateTime(getContext(), calendar.getTimeInMillis(), flags));
  }
}
