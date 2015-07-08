package com.tudou.calendarpager.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.test.tudou.library.expandcalendar.view.ExpandCalendarMonthView;
import com.test.tudou.library.expandcalendar.view.ExpandCalendarView;
import com.test.tudou.library.model.CalendarDay;
import com.tudou.calendarpager.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by tudou on 15-5-19.
 */
public class ExpandCalendarActivity extends ActionBarActivity implements ExpandCalendarMonthView.OnDayClickListener {

    @InjectView(R.id.view_calendar)
    ExpandCalendarView mMonthPagerView;
    @InjectView(R.id.text_example)
    TextView textExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_calendar);
        ButterKnife.inject(this);

        updateData();
    }

    private void updateData() {
        mMonthPagerView.setData(new CalendarDay(2015, 5, 4), new CalendarDay(2020, 12, 2));
        mMonthPagerView.setOnDayClickListener(this);
        mMonthPagerView.setSelectDay(new CalendarDay(2016, 10, 16));

    }

    @Override
    public void onDayClick(CalendarDay calendarDay) {
        textExample.setText("Click at " + calendarDay.getDayString());
    }
}
