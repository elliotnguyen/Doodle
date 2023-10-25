package com.example.doodle.fragment;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doodle.R;
import com.example.doodle.customview.MyDoodleView;

public class DoodleViewFragment extends Fragment {
    public DoodleViewFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doodle, container, false);

        MyDoodleView myDoodleView = view.findViewById(R.id.doodleview);
        //myDoodleView.displayShapes();
        myDoodleView.spinnerAround();
        myDoodleView.moveText();
        return view;
    }
}
