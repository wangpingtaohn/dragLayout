package com.wpt.drag;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

/**
 * author : wpt
 * date   : 2019-12-19 13:46
 * desc   : 可以拖动的LinearLayout
 */
public class DragLinearLayout extends LinearLayout {

    private TranslateAnimation showViewAnimation;

    private TranslateAnimation hideViewAnimation;

    private MoveListener moveListener;

    private int lastY;

    private boolean isValidMove;

    private boolean isMoveDown;

    private boolean isMoveUp;

    /**
     * 是否显示滑出底部的限制，如限制，则，只能滑出该布局的高度，
     * 无限制，则随意滑到顶部
     */
    private boolean isLimitBottom;

    public DragLinearLayout(Context context) {
        super(context);
        init();
    }

    public DragLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DragLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setMoveListener(MoveListener l) {
        this.moveListener = l;
    }

    private void init(){
        setOrientation(LinearLayout.VERTICAL);
        //设置动画，从自身位置的最下端向上滑动了自身的高度，持续时间为300ms
        showViewAnimation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 1f, TranslateAnimation.RELATIVE_TO_SELF, 0f);

        hideViewAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0.0f, TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.0f, TranslateAnimation.RELATIVE_TO_SELF, 1.0f);

        showViewAnimation.setDuration(300);
        hideViewAnimation.setDuration(300);
    }

    public void show() {
        if (this.getVisibility() == View.GONE) {
            this.setVisibility(View.VISIBLE);
            this.startAnimation(showViewAnimation);
        }
    }

    public void hide() {
        if (this.getVisibility() == View.VISIBLE) {
            this.setVisibility(View.GONE);
            this.startAnimation(hideViewAnimation);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int action = event.getAction();
        //获取手机触摸的坐标
        int y = (int) event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN://按下
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE://
                if (Math.abs(y - lastY) < ViewConfiguration.get(getContext()).getScaledTouchSlop()){
                    return false;
                }
                if (y > lastY){//向下
                    isMoveDown = true;
                    isMoveUp = false;
                }
                if (y < lastY){//向上
                    isMoveDown = false;
                    isMoveUp = true;
                    if (isLimitBottom){
                        if (getBottom() < (getScreenH(getContext()) - getNavigationBarHeight(getContext()) - 10)){
                            return false;
                        }
                    } else {
                        if (getTop() < getStatusBarHeight(getContext())){
                            return false;
                        }
                    }
                }
                int offsetY = y - lastY;
                layout(getLeft(),getTop()+offsetY,
                        getRight(),getBottom()+offsetY);
                isValidMove = true;
                break;
            case MotionEvent.ACTION_UP://当手指抬起
                if (isValidMove && moveListener != null && (isMoveDown || isMoveUp)){
                    if (isMoveDown){
                        moveListener.onDownDone();
                    } else {
                        moveListener.onUpDone();
                    }
                    isValidMove = false;
                    isMoveDown = false;
                    isMoveUp = false;
                    return true;
                } else {
                    return false;
                }
        }
        return super.onInterceptTouchEvent(event);
    }


    public interface MoveListener {
        //向下拖拽完成回调
        void onDownDone();
        //向上拖拽完成回调
        void onUpDone();
    }

    private int getScreenH(Context mContext) {
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 获取导航栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    private int getNavigationBarHeight(Context context) {
        if (context == null){
            throw new NullPointerException("context is null");
        }
        boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0 && !hasMenuKey) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    private int getStatusBarHeight(Context context) {
        if (context == null){
            throw new NullPointerException("context is null");
        }
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

}
