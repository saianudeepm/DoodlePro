package com.salome.doodlepro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saianudeepm on 11/5/14.
 */
public class V1View extends View {

    Paint paint;
    int paintColor = Color.BLACK;
    List<Point> pointsList;

    // constructor.. lets see what attributes we might use later..
    //but for now dont think we are using much
    public V1View(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupPaint();
        pointsList = new ArrayList<Point>();
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
        for(Point p: pointsList){
            canvas.drawCircle(p.x,p.y,1,paint);
        }
    }

   /* private class Point{
        private Point(float x, float y) {
            X = x;
            Y = y;
        }

        float X;
        float Y;


    }*/


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        pointsList.add(new Point(Math.round(event.getX()),Math.round(event.getY())));
        invalidate();
        return true;
    }
}
