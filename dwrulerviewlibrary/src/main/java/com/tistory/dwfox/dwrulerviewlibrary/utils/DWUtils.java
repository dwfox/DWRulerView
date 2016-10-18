package com.tistory.dwfox.dwrulerviewlibrary.utils;

import android.content.Context;

import com.tistory.dwfox.dwrulerviewlibrary.view.ObservableHorizontalScrollView;

/**
 * Created by Administrator on 2015-10-20.
 */
public class DWUtils {

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static float getRulerViewValue(ObservableHorizontalScrollView view, int l, int t, float MAX_VALUE, float MIN_VALUE) {

        float oneValue = (float) view.getWidth() * 3f / (MAX_VALUE - MIN_VALUE);
        int value = (int) (l / oneValue) + (int) MIN_VALUE;

        if (value > (MAX_VALUE)) value = (int) MAX_VALUE;
        else if (value < MIN_VALUE) value = (int) MIN_VALUE;

        return value;
    }


    public static int getValueAndScrollItemToCenter(ObservableHorizontalScrollView view, int l, int t, float MAX_VALUE, float MIN_VALUE, float multipleSize) {
        return getValueAndScrollItemToCenter(view, l, t, MAX_VALUE, MIN_VALUE, multipleSize, 1);
    }

    public static int getValueAndScrollItemToCenter(ObservableHorizontalScrollView view, int l, int t, float MAX_VALUE, float MIN_VALUE, float multipleSize, int valueMultiple) {
        float oneValue = (float) view.getWidth() * multipleSize / (MAX_VALUE - MIN_VALUE);
        int value = (int) (l / oneValue) + (int) MIN_VALUE;
        int offset = (int) (l % oneValue);


        if (offset > oneValue / 2) {
            value += 1;
            view.smoothScrollBy((int) oneValue - offset, 0);

        } else {
            view.smoothScrollBy(-offset, 0);
        }

        if (value > MAX_VALUE) {
            value = (int) MAX_VALUE;
        }

        return value * valueMultiple;
    }

    public static void scrollToValue(ObservableHorizontalScrollView view, float value, float MAX_VALUE, float MIN_VALUE, float multipleSize) {
        float oneValue = (float) view.getWidth() * multipleSize / (MAX_VALUE - MIN_VALUE);
        float valueWidth = oneValue * (value - MIN_VALUE);

        view.scrollBy((int) valueWidth, 0);
    }

}
