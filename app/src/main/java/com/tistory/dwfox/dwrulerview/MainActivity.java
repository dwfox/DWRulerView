package com.tistory.dwfox.dwrulerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.tistory.dwfox.dwrulerviewlibrary.utils.DWUtils;
import com.tistory.dwfox.dwrulerviewlibrary.view.ObservableHorizontalScrollView;
import com.tistory.dwfox.dwrulerviewlibrary.view.ScrollingValuePicker;

public class MainActivity extends AppCompatActivity {

    private ScrollingValuePicker myScrollingValuePicker;

    private static final float MIN_VALUE = 0;
    private static final float MAX_VALUE = 100;
    private static final float LINE_RULER_MULTIPLE_SIZE = 4.5f;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);

        myScrollingValuePicker = (ScrollingValuePicker) findViewById(R.id.myScrollingValuePicker);
        myScrollingValuePicker.setViewMultipleSize(LINE_RULER_MULTIPLE_SIZE);
        myScrollingValuePicker.setMaxValue(MIN_VALUE, MAX_VALUE);
        myScrollingValuePicker.setValueTypeMultiple(5);
        myScrollingValuePicker.getScrollView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    myScrollingValuePicker.getScrollView().startScrollerTask();
                }
                return false;
            }
        });

        myScrollingValuePicker
                .setOnScrollChangedListener(new ObservableHorizontalScrollView.OnScrollChangedListener() {

                    @Override
                    public void onScrollChanged(ObservableHorizontalScrollView view, int l, int t) {
//                        text.setText("Value MOVEING : " +
//                                DWUtils.getValueAndScrollItemToCenter(myScrollingValuePicker.getScrollView(), l, t, MAX_VALUE, MIN_VALUE, myScrollingValuePicker.getViewMultipleSize()));
                    }

                    @Override
                    public void onScrollStopped(int l, int t) {
                        text.setText("Value STOP : " +
                                DWUtils.getValueAndScrollItemToCenter(myScrollingValuePicker.getScrollView(), l, t, MAX_VALUE, MIN_VALUE, myScrollingValuePicker.getViewMultipleSize()));
                    }
                });
//        myScrollingValuePicker.setOnScrollChangedListener(new ObservableHorizontalScrollView.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged(ObservableHorizontalScrollView view, int l, int t) {
//
//                float oneValue = (float) view.getWidth() * 2.5f / (MAX_VALUE - MIN_VALUE);
//                int value = (int) (l / oneValue) + (int) MIN_VALUE;
//
//                Log.d("TEST", "View : view.getWidth() : " + view.getWidth()
//                        + "\nView : l : " + l
//                        + "\nView : t : " + t
//                        + "\noneValue : oneValue : " + oneValue
//                        + "\nvalue : value : " + value);
//
//                if (value > (MAX_VALUE)) value = (int) MAX_VALUE;
//                else if (value < MIN_VALUE) value = (int) MIN_VALUE;
//                tv_text.setText("" + value);
//            }
//        });

    }
}
