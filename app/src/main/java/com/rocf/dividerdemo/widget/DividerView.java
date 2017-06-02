package com.rocf.dividerdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.rocf.dividerdemo.R;
import com.rocf.dividerdemo.utils.DividerDrawableFilter;
import com.rocf.dividerdemo.utils.DividerUtils;


/**
 * Create by rocf.wong@gmail.com
 */
public class DividerView extends View {
    private int mDividerLeft = 0;
    private int mDividerRight = 0;
    private int mDividerPaddingColor = Color.TRANSPARENT;
    private int mDividerColor = Color.TRANSPARENT;
    private Paint mDividerPaddingPaint = new Paint();
    //default is null
    private Paint mDividerPaint = null;

    public DividerView(Context context) {
        this(context, null);
    }

    public DividerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DividerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DividerView, defStyleAttr, 0);
        mDividerLeft = a.getDimensionPixelSize(R.styleable.DividerView_dividerLeft,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));
        mDividerRight = a.getDimensionPixelSize(R.styleable.DividerView_dividerRight,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));
        mDividerPaddingColor = a.getColor(R.styleable.DividerView_dividerPaddingColor, Color.TRANSPARENT);
        mDividerColor = a.getColor(R.styleable.DividerView_android_background, Color.TRANSPARENT);
        a.recycle();
        mDividerPaddingPaint.setColor(mDividerPaddingColor);
        setDividerDrawableFilter(new DividerDrawableFilter() {
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDividerLeft > 0) {
            canvas.drawRect(0, 0, mDividerLeft, getMeasuredHeight(), mDividerPaddingPaint);
        }
        if (mDividerRight > 0) {
            canvas.drawRect(getMeasuredWidth() - mDividerRight, 0, getMeasuredWidth(), getMeasuredHeight(), mDividerPaddingPaint);
        }
        if (mDividerPaint != null) {
            canvas.drawRect(mDividerLeft, 0, getMeasuredWidth() - mDividerRight, getMeasuredHeight(), mDividerPaint);
        }
    }

    public void setDividerDrawableFilter(DividerDrawableFilter filter) {
        if (filter == null)
            return;
        if (mDividerPaint == null)
            mDividerPaint = new Paint();
        this.mDividerPaddingColor = filter.dividerPaddingColor();
        this.mDividerLeft = DividerUtils.dip2px(getContext(), filter.leftDividerMargin());
        this.mDividerRight = DividerUtils.dip2px(getContext(), filter.rightDividerMargin());
        this.mDividerColor = filter.dividerColor();
        mDividerPaint.setColor(this.mDividerColor);
        mDividerPaddingPaint.setColor(mDividerPaddingColor);
        ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = filter.dividerHeight();
            this.setLayoutParams(layoutParams);
        }
        invalidate();
    }

}