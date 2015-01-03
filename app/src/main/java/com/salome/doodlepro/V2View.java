package com.salome.doodlepro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saianudeepm on 11/6/14.
 */
public class V2View extends View {

    Paint paint;
    Path path = new Path();;
    int paintColor = Color.BLUE;


    // constructor.. lets see what attributes we might use later..
    //but for now dont think we are using much

    public V2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();

    }

    public void setupPaint(){
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(paintColor);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){

            case MotionEvent.ACTION_UP:
                return false;

            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                postInvalidate();
                return true;

            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(),event.getY());
                postInvalidate();
                return true;
        }

        return false;
    }

    //measurement
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }
}
