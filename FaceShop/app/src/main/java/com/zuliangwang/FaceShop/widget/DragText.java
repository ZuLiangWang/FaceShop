package com.zuliangwang.FaceShop.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zuliangwang on 15/11/28.
 */
public class DragText extends TextView {

    private static final int HORIZONTAL = 1;
    private static final int VERTICAL = 2;

    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private int left;
    private int right;
    private int bottom;
    private int top;
    private int dx;
    private int dy;

    private int parentHeight;
    private int parentWidth;

    public DragText(Context context) {
        this(context, null);
    }

    public DragText(Context context, AttributeSet attrs) {
        this(context, attrs,com.android.internal.R.attr.textViewStyle);
    }

    public DragText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //得到的是一个viewparent 它不一定是view 但是我使用时让它强制雷习惯转换
        ViewGroup viewGroup= (ViewGroup)getParent();
        parentWidth = viewGroup.getWidth();
        parentHeight = viewGroup.getHeight();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();

                Log.d("TAG","startX = "+startX +"   startY = " +startY);
                break;
            case MotionEvent.ACTION_MOVE:
                endX = event.getX();
                endY = event.getY();

                left = getLeft();
                right = getRight();
                bottom = getBottom();
                top = getTop();

                dx = (int) (endX - startX);
                dy = (int) (endY - startY);

                Log.d("TAG","endX="+endX+"endY"+endY+"left="+left+"right="+right+"bottom="+bottom+"top="+top);
                if (dx != 0 || dy !=0){
                    if (left<0){
                        left = 0;

                    }
                    layout(left + dx,top + dy,right + dx,bottom + dy);
                }



                break;
            case MotionEvent.ACTION_UP:
                Log.d("TAG",getLeft()+"  "+getRight() +"   "+getWidth());
                break;
            default:
                break;
        }
//        return super.onTouchEvent(event);
        return true;
    }



    private int calculatePosition(int begin,int end,int delta,int direction){
        if (direction == HORIZONTAL){
            if (begin < 0){
                
            }
        }
    }
}
