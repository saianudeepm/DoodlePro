package com.salome.doodlepro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by saianudeepm on 11/4/14.
 */
public class DoodleView extends View{
    Paint paint;
    Path path;
    int paintColor=Color.BLACK; // initial paint color
    Canvas doodleCanvas;
    Bitmap canvasBitmap;

    // if your not applying any theme then this is called (while inflating)
    // with out style attribute this is what the inflater is calling
    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupPaint();
    }

    public void clearPage(){
        postInvalidate();
        doodleCanvas.drawColor(Color.WHITE);
        path.reset();

    }
    private void setupPaint() {
        path = new Path();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(paintColor);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // looks at the state (int , float, text)
        //uses the state to create a visual representation
        canvas.drawBitmap(canvasBitmap, 0, 0, paint);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // do different things based on the movement
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                postInvalidate();
                return true;
            case MotionEvent.ACTION_UP:
                //as soon as the user removes his finger, save the state into doodleCanvas
                // and then reset the path
                doodleCanvas.drawPath(path, paint);
                path.reset();
                return false;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(),event.getY());
                postInvalidate();
                return true;
        }
        return false;
    }

    //set a new color
    public void setColor(ColorDrawable color){
        postInvalidate();
        paintColor = color.getColor();
        paint.setColor(paintColor);

    }


    //set brush size
    public void setBrushSize(int size){
        postInvalidate();
        paint.setStrokeWidth(size);

    }

    //we need to tell the system when the state has changed and tell it to redraw when this happens
    //the way we do is to say invalidate the view. postInvalidate() is used to do this
    // adding the invalidate command to the view message queue .

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(canvasBitmap==null){
            canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            doodleCanvas = new Canvas(canvasBitmap);
        }
        System.out.println("--------------resized called!!!!!!!");
    }



/*
    *//**
     *  need to be implemented
     *  *//*
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    // read the persisted path ( to restore the path)
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    // persist the path
    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    *//* Unused constructor*//*
    // when you have the styles defined this is what the inflater is calling
    //i.e if a custom style is applied then the inflater calls this constructor
    public DoodleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }*/



}
