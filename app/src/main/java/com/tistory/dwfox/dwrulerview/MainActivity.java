package com.tistory.dwfox.dwrulerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.tistory.dwfox.dwrulerviewlibrary.utils.DWUtils;
import com.tistory.dwfox.dwrulerviewlibrary.view.DWRulerSeekbar;
import com.tistory.dwfox.dwrulerviewlibrary.view.ObservableHorizontalScrollView;
import com.tistory.dwfox.dwrulerviewlibrary.view.ScrollingValuePicker;

public class MainActivity extends AppCompatActivity {

    private ScrollingValuePicker myScrollingValuePicker;
    private DWRulerSeekbar dwRulerSeekbar;

    private static final float MIN_VALUE = 5;
    private static final float MAX_VALUE = 33;
    private static final float LINE_RULER_MULTIPLE_SIZE = 3.5f;
    private TextView rulerText;
    private TextView seekbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rulerText = (TextView) findViewById(R.id.rulerview_value);
        seekbarText = (TextView) findViewById(R.id.seekbar_value);

        initRulerView();
        initDWSeekbar();
    }

    /**
     * initRulerView
     */
    private void initRulerView() {
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

        myScrollingValuePicker.setOnScrollChangedListener(new ObservableHorizontalScrollView.OnScrollChangedListener() {

            @Override
            public void onScrollChanged(ObservableHorizontalScrollView view, int l, int t) {
            }

            @Override
            public void onScrollStopped(int l, int t) {
                rulerText.setText("DWRulerView Value : " +
                        DWUtils.getValueAndScrollItemToCenter(myScrollingValuePicker.getScrollView()
                                , l
                                , t
                                , MAX_VALUE
                                , MIN_VALUE
                                , myScrollingValuePicker.getViewMultipleSize()));
            }
        });
    }

    /**
     * initDWSeekbar
     */
    private void initDWSeekbar() {
        dwRulerSeekbar = (DWRulerSeekbar) findViewById(R.id.dwRulerSeekbar);
        dwRulerSeekbar
                .setMinMaxValue((int) MIN_VALUE, (int) MAX_VALUE)
                .setDWRulerSeekbarListener(new DWRulerSeekbar.OnDWSeekBarListener() {
                    @Override
                    public void onStopSeekbarValue(int value) {
                        seekbarText.setText("DWSeekBar Value : " + value);
                    }
                });
    }
}
