package com.tudou.calendarpager.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tudou.calendarpager.model.CalendarDay;

/**
 * Created by tudou on 15-4-30.
 */
public class SimpleFragment extends Fragment {

  private CalendarDay mCalendarDay;

  public static SimpleFragment newInstance(CalendarDay calendarDay) {
    SimpleFragment simpleFragment = new SimpleFragment();
    simpleFragment.mCalendarDay = calendarDay;
    return simpleFragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    TextView textView = new TextView(getActivity());
    textView.setText(mCalendarDay.year + " - " + mCalendarDay.getMonth() + " - " + mCalendarDay.day);
    return textView;
  }
}
