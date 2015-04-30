package com.tudou.calendarpager.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tudou.calendarpager.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by tudou on 15-4-30.
 */
public class DayViewAdapter extends RecyclerView.Adapter<DayViewAdapter.DayViewHolder> {


  private LayoutInflater mInflater;
  private ArrayList<Integer> mDatas;
  private Context mContext;

  public DayViewAdapter(Context context) {
    mInflater = LayoutInflater.from(context);
    mContext = context;
    mDatas = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      mDatas.add(i);
    }
  }

  @Override
  public int getItemCount() {
    return mDatas.size();
  }

  @Override
  public DayViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View view = new TextView(mContext);
    int width = mContext.getResources().getDisplayMetrics().widthPixels;
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,
        ViewGroup.LayoutParams.MATCH_PARENT);
    view.setLayoutParams(params);
    DayViewHolder viewHolder = new DayViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(final DayViewHolder viewHolder, final int i) {
    viewHolder.bind(i);
  }

  public static class DayViewHolder extends RecyclerView.ViewHolder {
    private TextView mText;

    public DayViewHolder(View view) {
      super(view);
      mText = (TextView)view;
    }

    public void bind(int position) {
      mText.setText(" This position is:" + position);
    }

  }
}
