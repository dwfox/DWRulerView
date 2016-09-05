package com.tistory.dwfox.dwrulerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.tistory.dwfox.dwrulerviewlibrary.view.ObservableHorizontalScrollView;
import com.tistory.dwfox.dwrulerviewlibrary.view.ScrollingValuePicker;

public class MainActivity extends AppCompatActivity {

    private ScrollingValuePicker myScrollingValuePicker;

    private float MIN_VALUE = 10;
    private float MAX_VALUE = 80;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv_text = (TextView) findViewById(R.id.text);

        myScrollingValuePicker = (ScrollingValuePicker) findViewById(R.id.myScrollingValuePicker);
        myScrollingValuePicker.setMaxValue(MIN_VALUE, MAX_VALUE);
        myScrollingValuePicker.setOnScrollChangedListener(new ObservableHorizontalScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ObservableHorizontalScrollView view, int l, int t) {

                float oneValue = (float) view.getWidth() * 2.5f / (MAX_VALUE - MIN_VALUE);
                int value = (int) (l / oneValue) + (int) MIN_VALUE;

                Log.d("TEST", "View : view.getWidth() : " + view.getWidth()
                        + "\nView : l : " + l
                        + "\nView : t : " + t
                        + "\noneValue : oneValue : " + oneValue
                        + "\nvalue : value : " + value);

                if (value > (MAX_VALUE)) value = (int) MAX_VALUE;
                else if (value < MIN_VALUE) value = (int) MIN_VALUE;
                tv_text.setText("" + value);
            }
        });

    }
}
