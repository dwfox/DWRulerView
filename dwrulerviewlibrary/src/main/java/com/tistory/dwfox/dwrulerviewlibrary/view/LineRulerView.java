package com.tistory.dwfox.dwrulerviewlibrary.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.tistory.dwfox.dwrulerviewlibrary.utils.DWUtils;

/**
 * Created by DWFOX on 2016-08-31.
 */

public class LineRulerView extends View {

    private Paint paint;
    private Paint textPaint;
    private DashPathEffect dashPathEffect;
    private Path backGroundPath;

    private float bigUnitLineHeight = 0f;
    private float samllUnitLineHeight = 0f;

    private float MAX_DATA = 100;
    private float MIN_DATA = 0;

    private int viewHeight = 0;
    private int viewWidth = 0;

    private int showRangeValue = 5;

    private int valueMultiple = 1;

    // 1  5개마다 ( Default)
    // 2  특정 배수 마다
    private int displayNumberType = 1;

    public static final int DISPLAY_NUMBER_TYPE_SPACIAL_COUNT = 1;
    public static final int DISPLAY_NUMBER_TYPE_MULTIPLE = 2;
    private int valueTypeMultiple = 5;

    private int longHeightRatio = 10;
    private int shortHeightRatio = 5;
    private int baseHeightRatio = 3;


    public LineRulerView(Context context) {
        super(context);
        init(context);
    }

    public LineRulerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LineRulerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LineRulerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        paint.setStrokeWidth(5f);
        paint.isAntiAlias();
        paint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setColor(0xFFFFFFFF);
        textPaint.isAntiAlias();
        textPaint.setTextSize(DWUtils.sp2px(context, 14));
        textPaint.setTextAlign(Paint.Align.CENTER);

        invalidate();
    }

    public LineRulerView setMaxValue(float maxValue) {
        this.MAX_DATA = maxValue;
        return this;
    }

    public LineRulerView setMinValue(float minValue) {
        this.MIN_DATA = minValue;
        return this;
    }

    public LineRulerView setValueMultiple(int valueMultiple) {
        this.valueMultiple = valueMultiple;
        return this;
    }

    public void setMultipleTypeValue(int valueTypeMultiple) {
        this.displayNumberType = DISPLAY_NUMBER_TYPE_MULTIPLE;
        this.valueTypeMultiple = valueTypeMultiple;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        viewHeight = getMeasuredHeight();
        viewWidth = getMeasuredWidth();

        Log.d("##", "viewHeight : " + viewHeight);

        float viewInterval = (float) viewWidth / (MAX_DATA - MIN_DATA);


        canvas.drawLine(0, 0, 0, viewHeight / longHeightRatio * baseHeightRatio, paint);

        for (int i = 1; i < (MAX_DATA - MIN_DATA); i++) {
            if (displayNumberType == DISPLAY_NUMBER_TYPE_MULTIPLE) {

                if (((int) (i + MIN_DATA) * valueMultiple) % valueTypeMultiple == 0) {
                    canvas.drawLine(viewInterval * i, 0, viewInterval * i, viewHeight / shortHeightRatio * baseHeightRatio, paint);
                    canvas.drawText("" + ((int) (i + MIN_DATA) * valueMultiple), viewInterval * i, viewHeight / shortHeightRatio * baseHeightRatio + DWUtils.sp2px(getContext(), 14), textPaint);
                } else {
                    canvas.drawLine(viewInterval * i, 0, viewInterval * i, viewHeight / longHeightRatio * baseHeightRatio, paint);
                }

            } else {
                if (i % 5 == 0) {
                    canvas.drawLine(viewInterval * i, 0, viewInterval * i, viewHeight / shortHeightRatio * baseHeightRatio, paint);
                    canvas.drawText("" + ((int) (i + MIN_DATA) * valueMultiple), viewInterval * i, viewHeight / shortHeightRatio * baseHeightRatio + DWUtils.sp2px(getContext(), 14), textPaint);
                } else {
                    canvas.drawLine(viewInterval * i, 0, viewInterval * i, viewHeight / longHeightRatio * baseHeightRatio, paint);
                }
            }
        }
        canvas.drawLine(viewWidth, 0, viewWidth, viewHeight / longHeightRatio * baseHeightRatio, paint);

        super.onDraw(canvas);
    }
}

