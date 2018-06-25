package com.zxn.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

//https://blog.csdn.net/u010575173/article/details/52701936
public class ClockView extends View {

    private String TAG = "ClockView";

    private int mWidth;
    private int mHeight;

    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //在不处理onMeasu的情况,wrapcent 和 matchparent 是一样的效果,
        //全部是撑满父View
        if (heightMode == MeasureSpec.EXACTLY) {
            //精确值模式
            //父View给自定义View确定了一个范围,在这个范围内,自定义view的大小是给出的具体的值,父view限制自定义View的大小
            Log.i(TAG, "onMeasure: 高度测量模式EXACTLY--->".concat(String.valueOf(heightMeasureSpec)));
            //match_parent
            //onMeasure: 高度测量模式EXACTLY--->1073742982
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //最大值模式
            //父View没有对自定义控件做任何限制,想多大就多大,可以超过父View的大小
            Log.i(TAG, "onMeasure: 高度测量模式AT_MOST--->".concat(String.valueOf(heightMeasureSpec)));
            //wrap_content
            //onMeasure: 高度测量模式AT_MOST--->-2147482490
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            //未指定模式
            //父View没有对自定义View的大小做任何限制,自定义View想多大就多大,但是不能超过父View的大小,父View一定程度上限制了自定义View的大小
            Log.i(TAG, "onMeasure: 高度测量模式UNSPECIFIED--->".concat(String.valueOf(heightMeasureSpec)));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


//        刻度值
//        指针

        //将画布的起点x坐标向移动50
        //canvas.translate(50,0);
//        mWidth = getMeasuredWidth()-100;
//        mHeight = getMeasuredHeight()-100;

        //        绘制仪表盘–外面的那个大圆形
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        int size = 20;
        int left = size, top = size, right = size, bottom = size;
        setPadding(left, top, right, bottom);
        int radius = (mWidth - size * 2) / 2;
        //首先绘制一个大圆盘
        Paint paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(5);
        canvas.drawCircle(mWidth / 2, mHeight / 2, radius, paintCircle);


        // 刻度线
        //第二部绘制刻度线和刻度值,
        // 我们可以将一个圆分成120根刻度线,
        // 每两根刻度线之间的角度为3度,
        // 我们只要每绘制好一根线后,我们就画布旋转3度

        //创建画笔
        Paint paintDegree = new Paint();
        //设置画笔粗细3
        paintDegree.setStrokeWidth(3);

        //定义刻度针的尺寸
        float degreeScaleHeight = 20 * 1;
        float startX = mWidth / 2, startY = mHeight / 2 - radius, stopX = mWidth / 2, stopY = mHeight / 2 - radius + degreeScaleHeight;
        //定义大点刻度针的尺寸
        float degreeBigHeight = 20 * 4;
        //定义整点刻度针的尺寸
        float degreeIntHeight = 20 * 3;
        //定义半点刻度针的尺寸
        float degreeHalfHeight = 20 * 2;
        for (int i = 0; i < 120; i++) {
            //大点:12点 3点 6点 9点
            if (i == 0 || i == 30 || i == 60 || i == 90) {
                //设置画笔粗细3*4
                paintDegree.setStrokeWidth(12);
                //设置画笔字体为60
                paintDegree.setTextSize(60);
                //设置起点和终点的坐标,和画笔来画直线
                //起点为控件的中心点,
                //float startX, float startY, float stopX, float stopY,Paint paint
                float stopYBig = mHeight / 2 - radius + degreeBigHeight;
                canvas.drawLine(startX,
                        startY,
                        stopX,
                        stopYBig,
                        paintDegree);
                String degree = String.valueOf(i / 10);
                if (i == 0) {
                    degree = "12";
                }
                //String text, float x, float y, @NonNull Paint paint
                //定义文字和刻度的间隙
                float textGap = 150;
                canvas.drawText(
                        degree,
                        startX - paintDegree.measureText(degree) / 2,
                        mHeight / 2 - radius + textGap,
                        paintDegree);

            } else if (i % 10 == 0) {//整点
                paintDegree.setStrokeWidth(3 * 3);
                paintDegree.setTextSize(60);
                String degree = String.valueOf(i / 10);
                //定义文字和刻度的间隙
                float textGap = 140;
                canvas.drawText(
                        degree,
                        startX - paintDegree.measureText(degree) / 2,
                        mHeight / 2 - radius + textGap,
                        paintDegree);
                float stopYInt = mHeight / 2 - radius + degreeIntHeight;
                canvas.drawLine(
                        startX,
                        startY,
                        stopX,
                        stopYInt,
                        paintDegree);
            } else if (i % 5 == 0) {//半点
                paintDegree.setStrokeWidth(3 * 2);
//                paintDegree.setTextSize(20);
//                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 40, paintDegree);
                float stopYHalf = mHeight / 2 - radius + degreeHalfHeight;
                canvas.drawLine(startX, startY, stopX, stopYHalf, paintDegree);
            } else {//刻度点
                paintDegree.setStrokeWidth(3);
//                paintDegree.setTextSize(20);

                canvas.drawLine(
                        startX,
                        startY,
                        stopX,
                        stopY,
                        paintDegree
                );
            }
            //每次绘制完成后将画布旋转3度
            canvas.rotate(3, mWidth / 2, mHeight / 2);
        }

        //保存表盘和刻度的画布
        canvas.save();

        //绘制指针
        //绘制指针的圆心
        Paint paintPoint = new Paint();
        //将画布的起点坐标移动到圆心位置
        canvas.translate(mWidth/2,mHeight/2);
        canvas.drawCircle(0,0,15,paintPoint);

        Paint paintHouse = new Paint();
        paintHouse.setStrokeWidth(15);
        canvas.drawLine(0,0,0,-100,paintHouse);

        Paint paintMinute = new Paint();
        paintMinute.setStrokeWidth(10);
        canvas.drawLine(0,0,0,180,paintMinute);
//
        Paint paintSecond = new Paint();
        paintSecond.setStrokeWidth(8);
        canvas.drawLine(0,0,100,250,paintSecond);
        //合并图层
        canvas.restore();
    }
}
