# CalendarPager
This is one horizontal calendar with viewPager.

The header is create by recyclerview. Every item draw based on week by week.You can slide the week recyclerview to next week or pref. I write it because I have used in my work.You just should input your first and last day. Then the calendar will be create one by one.

Maybe not perfect. I will thanks for your suggestion.

Screeshot
====

* WeekRecyclerView

![](/screenshot/screenshot.gif)

* MonthSwitchView

![](/screenshot/screenshot_switch.gif)

* ExpandCalendarView

![](/screenshot/screenshot_expand.gif)

Installation
====
```groovy
dependencies {
    compile 'com.github.todou:calendarpager:1.0.0'
}
```

Just Do
====

###WeekRecyclerView

First you should add the layout WeekRecyclerView and WeekDayViewPager. @layout/view_week_label and text_day_label can add by yourself.
```xml
  <include layout="@layout/view_week_label"/>
  
    <com.test.tudou.library.WeekPager.view.WeekRecyclerView
        android:id="@+id/header_recycler_view"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:clipToPadding="false"
        android:scrollbars="none"/>

    <TextView
        android:id="@+id/text_day_label"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:padding="@dimen/default_padding"
        tools:text="sjidg"
        android:layout_gravity="center_horizontal"
        />

    <com.test.tudou.library.WeekPager.view.WeekDayViewPager
      android:id="@+id/view_pager"
      android:layout_width="match_parent"
      android:layout_height="0dp"
      android:layout_weight="1"
      android:background="#DDDDDD"/>
    
```
Then to init the adapter and viewpager. Also you can add the setDayScrollListener to change the text of text_day_label textView to show the day.
```java
  private void setUpPager() {
    mPagerAdapter = new SimplePagerAdapter(getSupportFragmentManager());
    mViewPagerContent.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
    mViewPagerContent.setAdapter(mPagerAdapter);
    mViewPagerContent.setWeekRecyclerView(mWeekRecyclerView);
    mViewPagerContent.setDayScrollListener(this);
    mWeekViewAdapter = new WeekViewAdapter(this, mViewPagerContent);
    mWeekViewAdapter.setTextNormalColor(getResources().getColor(android.R.color.darker_gray));
    mWeekRecyclerView.setAdapter(mWeekViewAdapter);
  }
    
```
And you should create one adapter to extends WeekPagerAdapter. Return one Fragment by createFragmentPager(int position). 

Like This:
```java
  public class SimplePagerAdapter extends WeekPagerAdapter {

    public SimplePagerAdapter(FragmentManager fm) {
      super(fm);
    }
    
    @Override protected Fragment createFragmentPager(int position) {
      return SimpleFragment.newInstance(mDays.get(position));
    }
  }
```

Last you can load the data.
```java
  private void setUpData() {
    ArrayList<CalendarDay> reachAbleDays = new ArrayList<>();
    reachAbleDays.add(new CalendarDay(2015, 5, 1));
    reachAbleDays.add(new CalendarDay(2015, 5, 4));
    reachAbleDays.add(new CalendarDay(2015, 5, 6));
    reachAbleDays.add(new CalendarDay(2015, 5, 20));
    mWeekViewAdapter.setData(reachAbleDays.get(0), reachAbleDays.get(reachAbleDays.size() - 1), null);
    mPagerAdapter.setData(reachAbleDays.get(0), reachAbleDays.get(reachAbleDays.size() - 1));
    mViewPagerContent.setCurrentPosition(DayUtils.calculateDayPosition(mWeekViewAdapter.getFirstShowDay(), new CalendarDay(2015, 5, 6)));
  }
    
```
You can use 
```java
    mViewPagerContent.setCurrentPosition(position);
```
to change the current pager

If you want one color to distinguish some days. You can add reachAbleDays. And set the color by setTextUnableColor
```java
    ...
    mWeekViewAdapter.setData(reachAbleDays.get(0), reachAbleDays.get(reachAbleDays.size() - 1), reachAbleDays);
    ...
    
```

###MonthSwitchView

Ok!Add layout first.
```xml
  <com.test.tudou.library.MonthSwitchPager.view.MonthSwitchView
    android:id="@+id/view_month"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
    
```
Then initial the data, Pay attention to the selectday must set after initial the startday and endday.
```java
    mMonthPagerView.setData(new CalendarDay(2015, 5, 4), new CalendarDay(2020, 12, 2));
    mMonthPagerView.setOnDayClickListener(this);
    mMonthPagerView.setSelectDay(new CalendarDay(2016, 10, 1));
    
```
Finally, your activity can Implements MonthView.OnDayClickListener to get the click event.
```java
    @Override public void onDayClick(CalendarDay calendarDay) {
        Toast.makeText(this, calendarDay.getDayString(), Toast.LENGTH_SHORT).show();
    }
    
```

###ExpandCalendarView
You can add it
```xml
   <com.test.tudou.library.expandcalendar.view.ExpandCalendarView
        android:id="@+id/view_calendar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
    
```
Then to add data to the Calendar
```java
   private void updateData() {
        mMonthPagerView.setData(new CalendarDay(2015, 5, 4), new CalendarDay(2020, 12, 2));
        mMonthPagerView.setOnDayClickListener(this);
        mMonthPagerView.setSelectDay(new CalendarDay(2016, 10, 16));

    }

    @Override
    public void onDayClick(CalendarDay calendarDay) {
        textExample.setText("Click at " + calendarDay.getDayString());
    }
    
```
Finally, get the click data as this
```java
public class ExpandCalendarActivity extends ActionBarActivity implements ExpandCalendarMonthView.OnDayClickListener {
    ...
    @Override
    public void onDayClick(CalendarDay calendarDay) {
        textExample.setText("Click at " + calendarDay.getDayString());
    }
    
```

If you have saw. I will thanks for your suggestion.



Thanks
====
* [flavienlaurent/datetimepicker](https://github.com/flavienlaurent/datetimepicker)

* [chenupt/SpringIndicator](https://github.com/chenupt/SpringIndicator)

License
====
<pre>
Copyright 2015 ToDou

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>
