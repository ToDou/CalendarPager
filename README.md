# CalendarPager
This is one horizontal calendar with viewPager.

Screeshot
====
![](/screenshot.gif)


Just Do
====
```java
    mPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager(), new CalendarDay(2015, 5, 1), new CalendarDay(2015, 5, 19));
    mViewPagerContent.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
    mViewPagerContent.setAdapter(mPagerAdapter);
    mViewPagerContent.setOnPageChangeListener(this);


    LinearLayoutManager manager = new LinearLayoutManager(this);
    manager.setOrientation(LinearLayoutManager.HORIZONTAL);
    mRecyclerView.setLayoutManager(manager);
    mWeekViewAdapter = new WeekViewAdapter(this, new CalendarDay(2015, 5, 1), new CalendarDay(2015, 5, 19), mViewPagerContent);
    mRecyclerView.setAdapter(mWeekViewAdapter);
    mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        adjustPosition(recyclerView, newState);
      }

      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

      }
    });
    
```
I have not finish.I will create one library here. If you have saw. I will thanks for your suggestion.

Thanks
====
[flavienlaurent/datetimepicker](https://github.com/flavienlaurent/datetimepicker)
[chenupt/SpringIndicator](https://github.com/chenupt/SpringIndicator)

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
