package com.rocf.dividerdemo.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rocf.dividerdemo.R;

/**
 * Create by rocf.wong@gmail.com
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    private Drawable mDivider;
    private Resources mResources;
    private DividerFilter mDividerFilter;
    private RecyclerView.Adapter mAdapter = null;

    public DividerItemDecoration(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mDivider = context.getDrawable(R.drawable.list_divider);
        } else {
            mDivider = context.getResources().getDrawable(R.drawable.list_divider);
        }
        mResources = context.getResources();
        initDividerFilter();
    }

    public DividerItemDecoration(Context context, Drawable dividerDrawable) {
        mDivider = dividerDrawable;
        mResources = context.getResources();
        initDividerFilter();
    }

    private void initDividerFilter() {
        mDividerFilter = new DividerFilter() {
            @Override
            public boolean bottomDividerEnabled() {
                return false;
            }

            @Override
            public boolean dividerEnabled(int position) {
//                if (mAdapter instanceof PreferenceGroupAdapter) {
//                    final Preference currentPreference = ((PreferenceGroupAdapter) mAdapter).getItem(position);
//                    final Preference nextPreference = ((PreferenceGroupAdapter) mAdapter).getItem(position + 1);
//                    if (currentPreference instanceof PreferenceCategory ||
//                            (nextPreference != null && nextPreference instanceof PreferenceCategory)) {
//                        return false;
//                    }
//                }
                return true;
            }

            @Override
            public int leftDividerMargin(int position) {
//                if (mAdapter instanceof PreferenceGroupAdapter) {
//                    if (((PreferenceGroupAdapter) mAdapter).getItem(position) != null &&
//                            ((PreferenceGroupAdapter) mAdapter).getItem(position).getIcon() != null) {
//                        Drawable icon = ((PreferenceGroupAdapter) mAdapter).getItem(position).getIcon();
//                        return icon.getBounds().width() + mResources.getDimensionPixelSize(R.dimen.divider_padding_horizontal);
//                    }
//                }
                return mResources.getDimensionPixelSize(R.dimen.divider_padding_horizontal);
            }

            @Override
            public int rightDividerMargin(int position) {
                return 0;
            }

            @Override
            public int dividerColor() {
                return -1;
            }
        };

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
    }


    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();

        mAdapter = parent.getAdapter();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            if (layoutParams == null || !mDividerFilter.dividerEnabled(layoutParams.getViewAdapterPosition()))
                continue;
            if (i == childCount - 1 && !mDividerFilter.bottomDividerEnabled())
                continue;

            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mDividerFilter.dividerHeight();

            if (mDivider != null) {
                if (mDividerFilter.dividerColor() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mDivider.setTint(mDividerFilter.dividerColor());
                    } else {
                        mDivider.setColorFilter(mDividerFilter.dividerColor(), PorterDuff.Mode.SRC_ATOP);
                    }
                }
                mDivider.setBounds(left + mDividerFilter.leftDividerMargin(layoutParams.getViewAdapterPosition()),
                        top, right + mDividerFilter.rightDividerMargin(layoutParams.getViewAdapterPosition()), bottom);
                mDivider.draw(c);
            }
        }
    }

    public void setDividerFilter(DividerFilter mDividerFilter) {
        this.mDividerFilter = mDividerFilter;
    }


}