package com.tudou.calendarpager.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by tudou on 15-4-30.
 */
public class SimpleFragment extends Fragment {

  private String mStrings;

  public static SimpleFragment newInstance(String s) {
    SimpleFragment simpleFragment = new SimpleFragment();
   simpleFragment.mStrings = s;
    return simpleFragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    TextView textView = new TextView(getActivity());
    textView.setText(mStrings);
    return textView;
  }
}
