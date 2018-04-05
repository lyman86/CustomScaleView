package ly.customscaleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import ly.customscaleview.utils.ScaleViewDensityUtils;

/**
 * 滚动刻度尺
 * Created by luoyan on 2017/10/16.
 */

public class ScrollScaleView extends View {

    private Paint paintLine;

    private Paint paintScale;

    private Paint paintPoint;

//    private int screenWidth;

    private int deltaX = 0;

    private int downX = 0;
    private int moveX = 0;
    private int curDel = 0;

    /**
     * 这个刻尺子的总长度
     */
    private int scaleViewLenth = 2000;
    /**
     * 刻度尺在屏幕上显示的宽度
     */
    private int scaleViewWidth = 800;
    /**
     * 每个刻度的宽度
     */
    private float scaleViewScaleWidth = 4;
    /**
     * 指针的宽度
     */
    private float scaleViewPointerWidth = 8;
    /**
     * 刻度尺的刻度数
     */
    private int scaleViewScaleNum = 10;
    /**
     * 刻度尺直线的高度
     */
    private float scaleViewLineHeight = 4;
    /**
     * 刻度尺整个高度
     */
    private float scaleViewHeight = 26;
    /**
     * 刻度尺刻度的高度
     */
    private float scaleViewScaleHeight = 10;
    /**
     * 刻度尺刻度的颜色
     */
    private int scaleViewScaleColor = Color.RED;
    /**
     * 刻度尺指针的颜色
     */
    private int scaleViewPointerColor = Color.RED;
    /**
     * 刻度尺直线的颜色
     */
    private int scaleViewLineColor = Color.RED;

    private OnDataChangedListener listener;

    public ScrollScaleView(Context context) {
        this(context, null);
    }

    public ScrollScaleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollScaleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ScrollScaleView, defStyleAttr, 0);
        initAttrs(ta);
        ta.recycle();
    }

    private void initAttrs(TypedArray ta) {
//        scaleViewWidth = ta.getDimension(R.styleable.ScrollScaleView_scaleViewWidth, ScaleViewDensityUtils.dp2px(getContext(), 300));
        scaleViewLenth = ta.getInt(R.styleable.ScrollScaleView_scaleViewLenth, 1000);
        scaleViewPointerWidth = ta.getDimension(R.styleable.ScrollScaleView_scaleViewPointerWidth, ScaleViewDensityUtils.dp2px(getContext(), 4));
        scaleViewScaleWidth = ta.getDimension(R.styleable.ScrollScaleView_scaleViewScaleWidth, ScaleViewDensityUtils.dp2px(getContext(), 2));
        scaleViewScaleColor = ta.getColor(R.styleable.ScrollScaleView_scaleViewScaleColor, Color.RED);
        scaleViewPointerColor = ta.getColor(R.styleable.ScrollScaleView_scaleViewPointerColor, Color.RED);
        scaleViewLineColor = ta.getColor(R.styleable.ScrollScaleView_scaleViewLineColor, Color.RED);
        scaleViewScaleNum = ta.getInt(R.styleable.ScrollScaleView_scaleViewScaleNum, 10);
        scaleViewHeight = ta.getDimension(R.styleable.ScrollScaleView_scaleViewHeight, ScaleViewDensityUtils.dp2px(getContext(), 26));
        scaleViewLineHeight = ta.getDimension(R.styleable.ScrollScaleView_scaleViewLineHeight, ScaleViewDensityUtils.dp2px(getContext(), 2));
        scaleViewScaleHeight = ta.getDimension(R.styleable.ScrollScaleView_scaleViewScaleHeight, ScaleViewDensityUtils.dp2px(getContext(), 5));
        initData();
        initPaint();
    }

    public void setListener(OnDataChangedListener listener) {
        this.listener = listener;
    }

    public interface OnDataChangedListener {
        void onDataChanged(int data);
    }

    private void initData() {
//        WindowManager wm = (WindowManager) getContext()
//                .getSystemService(Context.WINDOW_SERVICE);
//        screenWidth = wm.getDefaultDisplay().getWidth();
    }

    private void initPaint() {
        paintLine = new Paint();
        paintLine.setStyle(Paint.Style.FILL);
        paintLine.setAntiAlias(true);
        paintLine.setColor(scaleViewLineColor);
        paintLine.setStrokeWidth(scaleViewLineHeight);

        paintScale = new Paint();
        paintScale.setStyle(Paint.Style.FILL);
        paintScale.setAntiAlias(true);
        paintScale.setColor(scaleViewScaleColor);
        paintScale.setStrokeWidth(scaleViewScaleWidth);

        paintPoint = new Paint();
        paintPoint.setAntiAlias(true);
        paintPoint.setStrokeWidth(scaleViewPointerWidth);
        paintPoint.setColor(scaleViewPointerColor);
        paintPoint.setStrokeCap(Paint.Cap.ROUND);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        scaleViewWidth = getMeasuredWidth();
        setMeasuredDimension(scaleViewWidth, (int) scaleViewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (deltaX < -(scaleViewLenth - scaleViewWidth / 2)) {
            deltaX = (int) -(scaleViewLenth - scaleViewWidth / 2);
        }

        if (deltaX > scaleViewWidth / 2) {
            deltaX = (int) (scaleViewWidth / 2);
        }

        if (deltaX < 0) {
            canvas.drawLine(0, scaleViewHeight / 2 - scaleViewLineHeight / 2, scaleViewLenth + deltaX, scaleViewHeight / 2 - scaleViewLineHeight / 2, paintLine);
        } else {
            canvas.drawLine(0 + deltaX, scaleViewHeight / 2 - scaleViewLineHeight / 2, scaleViewLenth, scaleViewHeight / 2 - scaleViewLineHeight / 2, paintLine);
        }
        canvas.drawLine(scaleViewWidth / 2, 0, scaleViewWidth / 2, scaleViewHeight, paintPoint);

        float delta = scaleViewLenth / scaleViewScaleNum;
        for (int i = 0; i <= scaleViewScaleNum; i++) {
            float len = delta * i + deltaX;
            canvas.drawLine(len, scaleViewHeight / 2 - scaleViewScaleWidth - scaleViewScaleHeight, len, scaleViewHeight / 2 + scaleViewScaleWidth+scaleViewScaleHeight, paintScale);
        }

        if (listener != null) {
            int data = deltaX;
            if (data < 0) {
                data = (int) -(data - scaleViewWidth / 2);
            } else {
                data = (int) (scaleViewWidth / 2 - data);
            }
            listener.onDataChanged(data);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                //从右向左滑动，数字变小，从左向右滑动数字变大
                moveX = (int) event.getX();
                deltaX = moveX - downX + curDel;
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                curDel = deltaX;
                break;
        }
        return true;
    }

    /**
     * 设置刻度尺的总长度
     *
     * @param len
     */
    public void setScaleViewLenth(int len) {
        this.scaleViewLenth = len;
        postInvalidate();
    }

    /**
     * 设置刻度尺的分割数
     *
     * @param partNum
     */
    public void setPartNum(int partNum) {
        this.scaleViewScaleNum = partNum;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
        curDel = deltaX;
        postInvalidate();
    }

    public float getScaleViewWidth() {
        return scaleViewWidth;
    }

    public int getScaleViewLenth() {
        return scaleViewLenth;
    }
}
