package com.ticker;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Author @ Mohit Soni on 16-05-2018 03:36 PM.
 */

public class Ticker extends HorizontalScrollView {
    private final String TAG = this.getClass().getSimpleName();

    ArrayList<String> arrayList = new ArrayList<>();
    HashMap<String, View> viewArrayList = new HashMap<>();
    Context context;

    Timer timer;
    Button start, stop;

    TickeAdapter adapter;
    int array_size = 0, maxScroll = 0;
    LinearLayout linearLayout;
    TextView text;

    public Ticker(Context context) {
        super(context);
        this.context = context;
    }

    public Ticker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public Ticker(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        linearLayout = (LinearLayout) findViewById(R.id.ll2);
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                text = new TextView(context);
                text.setText(getNumber() + "");
                text.setLayoutParams(new LinearLayout.LayoutParams(200, LinearLayout.LayoutParams.WRAP_CONTENT));
                text.setGravity(Gravity.CENTER);
                text.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        log(text.getText().toString());
                        log(text.getTag() + "");
                    }
                });
                linearLayout.addView(text);
                text.setTag(i);
                viewArrayList.put(i + "", text);
            }
        }
    }

    /**
     * add new item to list
     */
    public void add() {
        String x = getNumber() + "";
        text = new TextView(context);
        text.setText(x);
        text.setLayoutParams(new LinearLayout.LayoutParams(200, LinearLayout.LayoutParams.WRAP_CONTENT));
        text.setGravity(Gravity.CENTER);
        text.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                log(text.getText().toString());
                log(text.getTag() + "");
            }
        });
        linearLayout.addView(text);
        text.setTag(x);
        viewArrayList.put(x + "", text);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        log("l:" + l + ", t:" + t + ", oldl:" + oldl + ", oldt:" + oldt);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        log("scrollX:" + scrollX + ", scrollY:" + scrollY);
    }

    public void start() {
        maxScroll = getMaxScrollAmount();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrollBy(10, 0);
            }
        }, 0, 50);
    }

    /**
     * add value to recycle value
     *
     * @param position
     */
    public void update(int position) {
        if (position == arrayList.size() - 1) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    arrayList.add(arrayList.get(array_size));
                    array_size++;
                    if (array_size == 9) {
                        array_size = 0;
                    }
                    adapter.notifyDataSetChanged();
                }
            }, 50);
        }
    }

    public int getNumber() {
        return 1000 + (new Random().nextInt(10000 - 1000) + 1);
    }

    public void log(String msg) {
        Log.i(TAG, msg);
    }
}
