package com.zuliangwang.FaceShop.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.InjectView;

/**
 * Created by zuliangwang on 15/11/29.
 */
public class TemplatePositionFrame extends FrameLayout implements View.OnTouchListener{


    ImageView template;

    public TemplatePositionFrame(Context context) {
        super(context);
    }

    public TemplatePositionFrame(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TemplatePositionFrame(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        canvas.drawLine(0,0,getWidth(),0,paint);
        canvas.drawLine(0,0,0,getHeight(),paint);
        canvas.drawLine(0,getHeight(),getWidth(),getHeight(),paint);
        canvas.drawLine(getWidth(),0,getWidth(),getHeight(),paint);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
