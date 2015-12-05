package com.zuliangwang.FaceShop.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by zuliangwang on 15/11/29.
 */
public class DragImageView extends ImageView {

    private static final int HORIZONTAL = 1;
    private static final int VERTICAL = 2;

    private static final int LEFT = 10;
    private static final int RIGHT = 11;
    private static final int TOP = 12;
    private static final int BOTTOM = 13;

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


    public DragImageView(Context context) {
        super(context);
    }

    public DragImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //        得到的是一个viewparent 它不一定是view 但是我使用时让它强制雷习惯转换
        ViewGroup viewGroup= (ViewGroup)getParent();
        parentWidth = viewGroup.getWidth();
        parentHeight = viewGroup.getHeight();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();

                Log.d("TAG", "startX = " + startX + "   startY = " + startY);
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
                    int realLeft = calculatePosition(left,right,dx,HORIZONTAL,LEFT);
                    int realRight = calculatePosition(left,right,dx,HORIZONTAL,RIGHT);
                    int realTop = calculatePosition(top,bottom,dy,VERTICAL,TOP);
                    int realBottom = calculatePosition(top,bottom,dy,VERTICAL,bottom);


                    layout(realLeft,realTop,realRight,realBottom);
                }



                break;
            case MotionEvent.ACTION_UP:
                Log.d("sb35",getLeft()+"  "+getRight() +"   "+"    "+getTop()+"    "+getBottom());
                break;
            default:
                break;
        }
//        return super.onTouchEvent(event);
        return true;
    }



    private int calculatePosition(int begin,int end,int delta,int direction,int varType){

        if (direction == HORIZONTAL){
            if (begin + delta < 0){
                begin = 0;
                end = getWidth();
            }
            else if (end + delta > parentWidth){
                end = parentWidth;
                begin = parentWidth - getWidth();
            }
            else {
                begin = begin + delta;
                end = end + delta;
            }

            if (varType == LEFT)
                return begin;
            else
                return end;
        }

        if (direction == VERTICAL){
            if (begin + delta <0){
                begin = 0;
                end = getHeight();
            }
            else if (end + delta > parentHeight){
                end = parentHeight;
                begin =  parentHeight - getHeight();
            }
            else {
                begin = begin + delta;
                end = end + delta;
            }

            if (varType == TOP)
                return begin;
            else
                return end;
        }
        return 0;
    }
}
