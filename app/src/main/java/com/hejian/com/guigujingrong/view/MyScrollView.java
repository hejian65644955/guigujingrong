package com.hejian.com.guigujingrong.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by 何健 on 2017/3/13.
 */

public class MyScrollView extends ScrollView {
    private int lastY;
    private View childView;
    private Rect rect = new Rect();
    private boolean isAnimationEnd = true;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if(getChildCount()==0|| !isAnimationEnd){
            return super.onTouchEvent(ev);
        }

        /*
        * getY(); 相对于父布局的Y值
        * getrawY(); 相对于屏幕的Y值
        * */

        int eventY = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                if(isNeedMove()){
                    int dy = eventY-lastY;//偏移量
                    //当我们没有记录的时候需要记录原来的位置
                    if(rect.isEmpty()){
                        rect.set(childView.getLeft(),childView.getTop()
                                ,childView.getRight(),childView.getBottom());
                    }
                    childView.layout(childView.getLeft(),childView.getTop()+dy/2,
                            childView.getRight(),childView.getBottom()+dy/2);

                }
                    lastY =eventY;
                break;
            case MotionEvent.ACTION_UP:
                //当原来的位置有记录的时候并且动画是结束的时候再执行
                if(!rect.isEmpty()&&isAnimationEnd){
                    //获取原来的高度和现在拉动位置的差
                    int translateY = childView.getBottom() - rect.bottom;
                    //平移动画所移动的距离
                    TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -translateY);
                    animation.setDuration(200);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            isAnimationEnd =false;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            childView.clearAnimation();
                            childView.layout(rect.left,rect.top,rect.right,rect.bottom);
                            //把原来的位置移除
                            rect.setEmpty();
                            isAnimationEnd =true;

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    childView.startAnimation(animation);
                }
            break;
        }

        return super.onTouchEvent(ev);
    }

    private boolean isNeedMove() {
        //scrollview的高度
        int scrolleViewHeight = this.getMeasuredHeight();
        //子视图的高度
        int childViewHeight = childView.getMeasuredHeight();
        int dy = childViewHeight-scrolleViewHeight;
        int scrollY =getScrollY();
        if(scrollY<=0 ||scrollY>=dy){
            return true;
        }
        return false;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildCount()>0){
            childView =getChildAt(0);
        }
    }

    private int downy,lastx,downx;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isOnIntercept = false;
        int eventx = (int) ev.getX();
        int eventy = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastY = downy = eventy;
                lastx = downx = eventx;
                break;
            case MotionEvent.ACTION_MOVE:
                int absx = Math.abs(eventx - downx);
                int absy = Math.abs(eventy - downy);
                if (absy > absx && absy >= 20){
                    isOnIntercept = true;
                }
                lastY = eventy;
                lastx = eventx;
                break;
        }
        return isOnIntercept;
    }
}
