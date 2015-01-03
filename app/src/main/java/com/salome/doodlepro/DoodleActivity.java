package com.salome.doodlepro;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;


public class DoodleActivity extends Activity {


    private DoodleView doodleView;
    private ImageButton currPaintColorBtn;
    private LinearLayout colorLayout;
    private LinearLayout toolTipLayout;
    private LinearLayout brushLayout;
    private Button brushSizeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doodle);

        doodleView = (DoodleView) findViewById(R.id.doodleView);

        colorLayout = (LinearLayout) findViewById(R.id.color_layout);
        currPaintColorBtn = (ImageButton) colorLayout.getChildAt(0);
        currPaintColorBtn.setImageDrawable(getResources().getDrawable(R.drawable.btn_selected));
        //initialize with small brush size as default
        brushSizeButton = (Button) findViewById(R.id.brush_small_btn);
        brushSizeButton.setSelected(true);
        brushSizeButton.setBackgroundColor(Color.CYAN);
        toolTipLayout = (LinearLayout) findViewById(R.id.tooltip_layout);
        brushLayout = (LinearLayout) findViewById(R.id.brush_layout);
    }

    // get the color selected frm img button and update paint color in doodle view
    //here view is the img button that is clicked
    public void colorPicker(View view) {
        if(view != currPaintColorBtn) {
            ColorDrawable color = (ColorDrawable)view.getBackground();
            doodleView.setColor(color);
            currPaintColorBtn.setImageDrawable(getResources().getDrawable(R.drawable.btn_normal));
            currPaintColorBtn = (ImageButton)view;
            //update the ui by selecting the new img button
            currPaintColorBtn.setImageDrawable(getResources().getDrawable(R.drawable.btn_selected));
        }
    }

    public void toggleToolTip(View view) {

        //if eraser was previously selected, click on brush shuld swtich to brush
        if(doodleView.paintColor==-1){
            doodleView.setColor((ColorDrawable)currPaintColorBtn.getBackground());
            //reset the size of brush to its original
            doodleView.setBrushSize(Integer.parseInt(brushSizeButton.getTag().toString()));

        }
        else if(toolTipLayout.getVisibility() == View.VISIBLE){
            brushLayout.setVisibility(View.VISIBLE);
            toolTipLayout.setVisibility(View.GONE);
        }
        else{
            toolTipLayout.setVisibility(View.VISIBLE);
            brushLayout.setVisibility(View.GONE);
        }
    }

    public void createNewFile(View view) {
        doodleView.clearPage();
    }

    public void selectBrushSize(View view) {
        if(brushSizeButton != (Button)view){
            ((Button)view).setSelected(true);
            brushSizeButton.setSelected(false);
            brushSizeButton.setBackgroundColor(Color.TRANSPARENT);
            brushSizeButton=(Button)view;
            brushSizeButton.setBackgroundColor(Color.CYAN);
            doodleView.setBrushSize(Integer.parseInt(brushSizeButton.getTag().toString()));
        }
    }

    public void selectEraser(View view) {
        doodleView.setBrushSize(10);
        doodleView.setColor(new ColorDrawable(getResources().getColor(R.color.white)));
    }
}

