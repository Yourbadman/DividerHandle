package com.rocf.dividerdemo.utils;
/**
 * Create by rocf.wong@jsonwong.com
 */
public abstract class DividerFilter {

    /**
     * Whether to draw the recycleview bottom line.
     *
     * @return
     */
    public boolean bottomDividerEnabled() {
        return true;
    }

    /**
     * Whether to draw the divider at postion.
     */
    public boolean dividerEnabled(int position) {
        return true;
    }

    /**
     * LeftMargin of the divider at postion.
     */
    public int leftDividerMargin(int position) {
        return 15;
    }

    /**
     * RightMargin of the divider at postion.
     */
    public int rightDividerMargin(int position) {
        return 15;
    }

    /**
     * @return the divider color,default value is 0xffc6c7cb.
     */
    public int dividerColor() {
        return 0xffc6c7cb;
    }

    /**
     * @return the divider height px,default value is 1.
     */
    public int dividerHeight() {
        return 1;
    }
}