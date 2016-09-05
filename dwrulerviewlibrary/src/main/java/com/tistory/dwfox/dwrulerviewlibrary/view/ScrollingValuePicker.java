package com.tistory.dwfox.dwrulerviewlibrary.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


/**
 * Created by DW on 2016-09-01.
 */
public class ScrollingValuePicker extends FrameLayout {

    private View mLeftSpacer;
    private View mRightSpacer;
    private LineRulerView lineRulerView;
    private ObservableHorizontalScrollView mScrollView;

    public ScrollingValuePicker(Context context) {
        super(context);
        init(context);
    }

    public ScrollingValuePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollingValuePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ScrollingValuePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void setOnScrollChangedListener(final ObservableHorizontalScrollView.OnScrollChangedListener onScrollChangedListener) {
        mScrollView.setOnScrollChangedListener(onScrollChangedListener);
    }

    public void setMaxValue(float minValue, float maxValue) {
        lineRulerView.setMaxValue(maxValue);
        lineRulerView.setMinValue(minValue);
    }


    private void init(Context context) {
        mScrollView = new ObservableHorizontalScrollView(context);
        mScrollView.setHorizontalScrollBarEnabled(false);
        addView(mScrollView);

        final LinearLayout container = new LinearLayout(context);
        mScrollView.addView(container);

        mLeftSpacer = new View(context);
        mRightSpacer = new View(context);

        lineRulerView = new LineRulerView(context);
        container.addView(lineRulerView);
        container.addView(mLeftSpacer, 0);
        container.addView(mRightSpacer);

    }

    @Override
    protected void onDraw(Canvas canvas) {


        Log.d("TTTT", "FrameLayout onDraw");

        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        paint.setStrokeWidth(5f);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawLine(0, 0, 100, 100, paint);
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (changed) {

            final int width = getWidth();

            final ViewGroup.LayoutParams leftParams = mLeftSpacer.getLayoutParams();
            leftParams.width = width / 2;
            mLeftSpacer.setLayoutParams(leftParams);
//            mLeftSpacer.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));

            final ViewGroup.LayoutParams rulerViewParams = lineRulerView.getLayoutParams();
            rulerViewParams.width = (int) (width * 2.5f);  // set RulerView Width
            lineRulerView.setLayoutParams(rulerViewParams);
//            lineRulerView.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
            lineRulerView.invalidate();


            final ViewGroup.LayoutParams rightParams = mRightSpacer.getLayoutParams();
            rightParams.width = width / 2;
//            mRightSpacer.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
            mRightSpacer.setLayoutParams(rightParams);

            invalidate();

        }
    }
}
