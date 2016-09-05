package com.tistory.dwfox.dwrulerviewlibrary.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by DW on 2016-09-01.
 */
public class ObservableHorizontalScrollView extends HorizontalScrollView {

    public interface OnScrollChangedListener {
        void onScrollChanged(ObservableHorizontalScrollView view, int l, int t);
    }

    private OnScrollChangedListener mOnScrollChangedListener;

    public ObservableHorizontalScrollView(Context context) {
        super(context);
    }

    public ObservableHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ObservableHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void setOnScrollChangedListener(OnScrollChangedListener l) {
        mOnScrollChangedListener = l;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(this, l, t);
        }
    }
}



