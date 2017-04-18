package com.simplexlabs.colorpicker.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Bill on 2017-04-08.
 */

public class OverlayView extends View {
    public OverlayView(Context context) {
        super(context);
    }

    public OverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public OverlayView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1.0f);

        float left = getLeft() + (getRight() - getLeft()) / 2 - 5;
        float top = getTop() + (getBottom() - getTop()) / 2 - 5; // basically (X1, Y1)

        float right = left + 10; // width (distance from X1 to X2)
        float bottom = top + 10;

        canvas.drawRect(left, top, right, bottom, paint);
    }
}
