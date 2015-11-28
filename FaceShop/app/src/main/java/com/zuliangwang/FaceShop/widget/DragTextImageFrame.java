package com.zuliangwang.FaceShop.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by zuliangwang on 15/11/28.
 */
public class DragTextImageFrame extends FrameLayout {

    public DragTextImageFrame(Context context) {
        super(context);
    }

    public DragTextImageFrame(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragTextImageFrame(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DragTextImageFrame(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



}
