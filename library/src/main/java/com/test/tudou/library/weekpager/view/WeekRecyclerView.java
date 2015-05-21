package com.test.tudou.library.weekpager.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tudou on 15-5-3.
 */
public class WeekRecyclerView extends RecyclerView {

  public static int LIST_LEFT_OFFSET = -1;

  public WeekRecyclerView(Context context) {
    this(context, null);
  }

  public WeekRecyclerView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public WeekRecyclerView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);

    initData();
  }

  private void initData() {
    LinearLayoutManager manager = new LinearLayoutManager(getContext());
    manager.setOrientation(LinearLayoutManager.HORIZONTAL);
    setLayoutManager(manager);

    setOnScrollListener(new OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        adjustPosition(recyclerView, newState);
      }

      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
      }
    });
  }

  private void adjustPosition(RecyclerView recyclerView, int newState) {
    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
      int i = 0;
      View child = recyclerView.getChildAt(i);
      while (child != null && child.getRight() <= 0) {
        child = recyclerView.getChildAt(++i);
      }
      if (child == null) {
        // The view is no longer visible, just return
        return;
      }
      final int left = child.getLeft();
      final int right = child.getRight();
      final int midpoint = recyclerView.getWidth() / 2;
      if (left < LIST_LEFT_OFFSET) {
        if (right > midpoint) {
          recyclerView.smoothScrollBy(left, 0);
        } else {
          recyclerView.smoothScrollBy(right, 0);
        }
      }
    }
  }

}
