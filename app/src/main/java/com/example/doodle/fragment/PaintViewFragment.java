package com.example.doodle.fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.doodle.GlobalSettings;
import com.example.doodle.R;
import com.example.doodle.customview.MyPaintView;
import com.example.doodle.myshape.MyCircle;
import com.example.doodle.myshape.Shape;

import java.util.ArrayList;

public class PaintViewFragment extends Fragment {
    Button lineOption, ellipseOption, textOption;
    public PaintViewFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_paintview, container, false);
        lineOption = (Button) view.findViewById(R.id.line_view);
        lineOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GlobalSettings.getInstance().setShapeID(0);
                GlobalSettings.ShapeID = 0;
            }
        });

        ellipseOption = (Button) view.findViewById(R.id.ellipse_view);
        ellipseOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GlobalSettings.getInstance().setShapeID(1);
                GlobalSettings.ShapeID = 1;
            }
        });

        textOption = (Button) view.findViewById(R.id.text_view);
        textOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalSettings.ShapeID = 2;
            }
        });

        return view;
    }
}
