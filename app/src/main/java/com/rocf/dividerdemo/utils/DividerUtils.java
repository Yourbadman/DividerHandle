package com.rocf.dividerdemo.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.view.Gravity;

/**
 * Create by rocf.wong@jsonwong.com
 */
public class DividerUtils {


    /**
     * Package divider drawable throw {@link DividerDrawableFilter} provider.
     *
     * @param filter divider style.
     * @return the divider drawable.
     */
    public static Drawable packageDividerDrawable(Context context, DividerDrawableFilter filter) {

        if (filter == null)
            return null;

        Drawable divider = getDividerShaperDrawable(filter.dividerColor());

        Drawable[] dividers = new Drawable[]{divider};
        LayerDrawable layerDrawable = new LayerDrawable(dividers);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //divider
            layerDrawable.setLayerInsetLeft(layerDrawable.getNumberOfLayers() - 1, dip2px(context, filter.leftDividerMargin()));
            layerDrawable.setLayerInsetRight(layerDrawable.getNumberOfLayers() - 1, dip2px(context, filter.rightDividerMargin()));
            if (filter.dividerHeight() > 0) {
                layerDrawable.setLayerHeight(layerDrawable.getNumberOfLayers() - 1, filter.dividerHeight());
            }
            //left padding
            if (filter.leftDividerMargin() > 0) {
                layerDrawable.addLayer(getDividerShaperDrawable(filter.dividerPaddingColor()));
                layerDrawable.setLayerWidth(layerDrawable.getNumberOfLayers() - 1, dip2px(context, filter.leftDividerMargin()));
                layerDrawable.setLayerGravity(layerDrawable.getNumberOfLayers() - 1, Gravity.START);
            }
            //right padding
            if (filter.rightDividerMargin() > 0) {
                layerDrawable.addLayer(getDividerShaperDrawable(filter.dividerPaddingColor()));
                layerDrawable.setLayerWidth(layerDrawable.getNumberOfLayers() - 1, dip2px(context, filter.rightDividerMargin()));
                layerDrawable.setLayerGravity(layerDrawable.getNumberOfLayers() - 1, Gravity.END);
            }
        } else {
            //TODO...compat

        }

        return layerDrawable;
    }


    private static Drawable getDividerShaperDrawable(int color) {

        ShapeDrawable drawable = new ShapeDrawable();
        drawable.setShape(new RectShape());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable.setTintMode(PorterDuff.Mode.SRC_ATOP);
            drawable.setTint(color);
        } else {
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        }
        drawable.getPaint().setColor(color);

        return drawable;
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }



}