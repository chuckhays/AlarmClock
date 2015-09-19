package com.madebychuck.alarmclock;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class AlarmShapeView extends View {
    private int circleCol, labelCol;
    private Paint circlePaint;
    private String circleText;
    private String mExampleString = "Hi there"; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 100; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    public AlarmShapeView(Context context) {
        super(context);
        init(null, 0);
    }

    public AlarmShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public AlarmShapeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        circlePaint = new Paint();
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.AlarmShapeView, defStyle, 0);
        circleText = "N";
        circleCol = Color.RED;
        labelCol = Color.BLACK;

        //mExampleString = "Hi there";//a.getString(
                //R.styleable.AlarmShapeView_exampleString);
        //mExampleColor = a.getColor(
        //        R.styleable.AlarmShapeView_exampleColor,
         //       mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
       // mExampleDimension = a.getDimension(
         //       R.styleable.AlarmShapeView_exampleDimension,
         //       mExampleDimension);

        if (a.hasValue(R.styleable.AlarmShapeView_exampleDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.AlarmShapeView_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();

        // Set up a default TextPaint object
       // mTextPaint = new TextPaint();
       // mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
       // mTextPaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
       // mTextPaint.setTextSize(mExampleDimension);
       // mTextPaint.setColor(mExampleColor);
       // mTextWidth = mTextPaint.measureText(mExampleString);

      //  Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
      //  mTextHeight = fontMetrics.bottom;
    }

    public int getCircleColor(){
        return circleCol;
    }

    public int getLabelColor(){
        return labelCol;
    }

    /**
     * Set the label color
     * @param newColor new color as an int
     */
    public void setLabelColor(int newColor){
        //update the instance variable
        labelCol=newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }

    /**
     * Set the circle color
     * @param newColor new color as an int
     */
    public void setCircleColor(int newColor){
        //update the instance variable
        circleCol=newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public String getLabelText(){
        return circleText;
    }

    /**
     * Set the label text
     * @param newLabel text as a string
     */
    public void setLabelText(String newLabel){
        //update the instance variable
        circleText=newLabel;
        //redraw the view
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //get half of the width and height as we are working with a circle
        int viewWidthHalf = this.getMeasuredWidth()/2;
        int viewHeightHalf = this.getMeasuredHeight()/2;
        //get the radius as half of the width or height, whichever is smaller
        //subtract ten so that it has some space around it
        int radius = 0;
        if(viewWidthHalf>viewHeightHalf)
            radius=viewHeightHalf-10;
        else
            radius=viewWidthHalf-10;

        //set drawing properties
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);
        //set the paint color using the circle color specified
        circlePaint.setColor(circleCol);
        //draw the circle using the properties defined
        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);

        //set the text color using the color specified
        circlePaint.setColor(labelCol);
        //set text properties
        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(180);
        //draw the text using the string attribute and chosen properties
        canvas.drawText(circleText, viewWidthHalf, viewHeightHalf + 70, circlePaint);
    }
  }
