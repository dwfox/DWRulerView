package com.tistory.dwfox.dwrulerviewlibrary.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.tistory.dwfox.dwrulerviewlibrary.R;

/**
 * Created by DW on 2016-11-14.
 */


public class DWRulerSeekbar extends RelativeLayout implements SeekBar.OnSeekBarChangeListener {

    private int seekbarMinValue = 0;
    private int seekbarMaxValue = 50;

    private SeekBar seekBar;
    private Context context;

    private OnDWSeekBarListener listener;

    public interface OnDWSeekBarListener {
        public void onStopSeekbarValue(int value);
    }

    public DWRulerSeekbar(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public DWRulerSeekbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public DWRulerSeekbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DWRulerSeekbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        init();
    }


    private void init() {

        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (getHeight() > 0) {
                    addCompoenet();
                    getViewTreeObserver().removeOnPreDrawListener(this);
                }
                return false;
            }
        });
    }

    private void addCompoenet() {
        removeAllViews();
        LineRulerView lineRulerView = new LineRulerView(context);
        LayoutParams rulerLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getHeight() / 3 * 2);
        rulerLayoutParams.addRule(ALIGN_PARENT_BOTTOM);
        lineRulerView.setLayoutParams(rulerLayoutParams);
        lineRulerView.setMinMaxValue(seekbarMinValue, seekbarMaxValue + 1);
        lineRulerView.setBackgroundColor(Color.DKGRAY);
        addView(lineRulerView);

        seekBar = new SeekBar(context);
        LayoutParams seekbarLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getHeight() / 3);
        seekbarLayoutParams.addRule(ALIGN_PARENT_TOP);
        seekBar.setLayoutParams(seekbarLayoutParams);
        seekBar.setBackgroundColor(Color.DKGRAY);
        seekBar.setProgressDrawable(null);
        seekBar.setPadding(0, 0, 0, 0);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setMax(seekbarMaxValue - seekbarMinValue + 1);
        setSeekberThumb(seekBar, context.getResources());
        addView(seekBar);
    }

    private void setSeekberThumb(final SeekBar seekBar, final Resources res) {
        seekBar.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (seekBar.getHeight() > 0) {
                    Drawable thumb = res.getDrawable(R.drawable.indicator);
                    int h = seekBar.getMeasuredHeight();
                    int w = h;
                    Bitmap bmpOrg = ((BitmapDrawable) thumb).getBitmap();
                    Bitmap bmpScaled = Bitmap.createScaledBitmap(bmpOrg, w, h, true);
                    Drawable newThumb = new BitmapDrawable(res, bmpScaled);
                    newThumb.setBounds(0, 0, newThumb.getIntrinsicWidth(), newThumb.getIntrinsicHeight());
                    seekBar.setThumb(newThumb);
                    seekBar.getViewTreeObserver().removeOnPreDrawListener(this);
                }
                return true;
            }
        });
    }

    public DWRulerSeekbar setDWRulerSeekbarListener(OnDWSeekBarListener listener) {
        this.listener = listener;
        return this;
    }

    public DWRulerSeekbar setMinMaxValue(int seekbarMinValue, int seekbarMaxValue) {
        this.seekbarMinValue = seekbarMinValue;
        this.seekbarMaxValue = seekbarMaxValue;
        addCompoenet();
        return this;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (listener != null) {
            listener.onStopSeekbarValue(seekbarMinValue + progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
