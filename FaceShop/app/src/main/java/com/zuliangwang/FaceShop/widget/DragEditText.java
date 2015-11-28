package com.zuliangwang.FaceShop.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zuliangwang on 15/11/27.
 */
public class DragEditText extends TextView implements View.OnTouchListener {

    private ImageView bg;


    private float bgXMaxPostion;
    private float bgYMaxPosition;

    private float lastX;
    private float lastY;


    public DragEditText(Context context) {
        super(context);
        setOnTouchListener(this);
        Log.d("TAG","setOn");
    }

    public DragEditText(Context context, AttributeSet attrs) {
        super(context,attrs);
        setOnTouchListener(this);
    }

//
//    public DragEditText(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DragEditText, defStyleAttr, 0);
//        int n = typedArray.getIndexCount();
//        for (int i=0; i<n ; i++){
//            int attr = typedArray.getIndex(i);
//            switch (attr){
//
//            }
//        }
//    }

    //在执行操作之前必须先setBg.
    public void setBg(ImageView bg){
        this.bg = bg;
        bgXMaxPostion =  bg.getWidth();
        bgYMaxPosition =  bg.getHeight();
        setOnTouchListener(this);
        Log.d("TAG","setBg");
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float mDx;
        float mDy;
        float left =0;
        float top = 0;
        float right =0;
        float bottom =0;
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                Log.d("TAG","actionUp");
                break;
            case MotionEvent.ACTION_MOVE:
                //last是上一次按下手指时的位置
                mDx = event.getX() - lastX;
                mDy = event.getY() - lastY;

                left = v.getLeft() + mDx;
                top = v.getTop() + mDy;
                right = v.getRight() + mDx;
                bottom = v.getBottom() + mDy;

                if (left < 0){
                    left = 0;
                    right = getWidth();
                }
                if (right>bgXMaxPostion){
                    right = bgXMaxPostion;
                    left = bgXMaxPostion - getWidth();
                }
                if (top < 0){
                    top = 0;
                    bottom = getHeight();
                }
                if (bottom > bgYMaxPosition){
                    bottom = bgYMaxPosition;
                    top = bottom - getHeight();
                }
                Log.d("TAG","actionMove");

                int iLeft = (int) left;
                int iRight = (int) right;
                int iTop = (int) top;
                int iBottom = (int) bottom;
//                Log.d()
                v.layout(iLeft,iTop,iRight,iBottom);
                Log.d("TAG","ileft = " + iLeft +"   itop = " + iTop +"    iright = "+iRight);
                invalidate();
                break;
            case MotionEvent.ACTION_DOWN:
//                lastX = event.getX();
//                lastY = event.getY();
                mDx = 0;
                mDy =0;
                lastY = event.getY();
                lastX = event.getX();
                Log.d("TAG","actionDown"+" getX"+event.getX()+"   getY"+event.getY());
                break;
        }
        return true;
    }


}
