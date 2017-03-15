package com.hejian.com.guigujingrong.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hejian.com.guigujingrong.R;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 何健 on 2017/3/13.
 */

public class MyProgressBar extends View {

    private int sweepArc;
    private int sweepColor;
    private int measuredHeight;
    private int measuredWidth;
    private Paint paint;
    private int roundColor= Color.RED;
    private int roundWidth =10;

    public MyProgressBar(Context context) {
        super(context);
        init();
    }

    /**
     * 1.什么是自定义属性？
     * 定义可以在布局文件的标签中使用的属性。
     * 2.为什么要自定义视图属性?
     * 这样就可以通过布局的方式给视图对象指定特定的属性值, 而不用动态的设置
     * 3.理解属性值的类型(format)
     * 1. reference 引用类型值 :@id/...
     * 2. color 颜色类型值  #ff00ff
     * 3. boolean 布尔类型值 true false
     * 4. dimension 尺寸类型值 dp / px /sp
     * 5. integer 整数类型值  weight  progress max
     * 6. float 小数类型值  0.5f
     * 7. string 字符串类型值  ""
     * 8. <enum> 枚举类型值 ：水平/垂直
     */
        public MyProgressBar(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
            //自定义属性
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.progress);
            //第一个参数获取attrs里面的配置属性名，第二个参数设置默认值
            roundColor =typedArray.getColor(R.styleable.progress_roundColor,Color.GRAY);
            sweepColor =typedArray.getColor(R.styleable.progress_sweepColor,Color.RED);
            sweepArc =typedArray.getInteger(R.styleable.progress_sweepArc,0);
            typedArray.recycle();


    }

    /**
    * 进度条
    * 第一步 画圆
    * 第二步 画弧
    * 第三步 画文字
    *
    * */
    private void init() {
        //画笔
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cy = measuredHeight / 2;
        int cx = measuredWidth / 2;
        int radius = cx -roundWidth/2;
        //设置圆环的颜色
        paint.setColor(roundColor);
        //设置圆环的宽度
        paint.setStrokeWidth(roundWidth);
        canvas.drawCircle(cx,cy,radius,paint);
        //画弧
        /*
        * RectF里面存放的是float的类型
        * Rect里面存放的是int值
        *
        * */
        RectF rectF = new RectF(roundWidth / 2, roundWidth / 2,
                measuredWidth - roundWidth / 2, measuredHeight - roundWidth / 2);
        paint.setColor(sweepColor);
        canvas.drawArc(rectF,0,sweepArc * 360 / 100,false,paint);
        //画文字
        //第一个参数是文本 第二个到第三个参数是文字的截取的长度，第四个参数是存放测量结果的容器
        String text = sweepArc+"%";
        Rect rect = new Rect();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(0);
        paint.setTextSize(30);
        paint.getTextBounds(text,0,text.length(),rect);

        float tx =measuredWidth/2-rect.width()/2;

        float ty =measuredHeight/2+rect.height()/2;
        canvas.drawText(text,tx,ty,paint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredHeight = getMeasuredHeight();//控件的高
        measuredWidth = getMeasuredWidth();//控件宽

    }

    public void setProgress(int progress) {
        sweepArc =progress;
        /*
        * invalidate 是在主线程强制刷新
        * postinvalidate 是在分线程强制刷新
        * */
        postInvalidate();
    }
}
