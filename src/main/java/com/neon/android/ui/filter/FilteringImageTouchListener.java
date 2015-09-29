package com.neon.android.ui.filter;

import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class FilteringImageTouchListener implements View.OnTouchListener {

    private final ImageView image;

    private Rect rect;

    private final int filter;

    private final int clearFilter = Color.argb(0, 0, 0, 0);


    public FilteringImageTouchListener(ImageView image) {
        this(image, Color.argb(50, 0, 0, 0));
    }

    public FilteringImageTouchListener(ImageView image, int filter) {
        this.image = image;
        this.filter = filter;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            image.setColorFilter(filter);
            rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            image.setColorFilter(clearFilter);
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                image.setColorFilter(clearFilter);
            }
        }
        return false;
    }

}
