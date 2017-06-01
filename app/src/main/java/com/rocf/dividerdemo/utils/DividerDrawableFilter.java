package com.rocf.dividerdemo.utils;

/**
 * Interface for divider style.
 * Create by rocf.wong@jsonwong.com
 */
public abstract class DividerDrawableFilter {

    /**
     * @return the divider color,default value is 0xffc6c7cb.
     */
    public int dividerColor() {
        return 0xffc6c7cb;
    }

    /**
     * @return the padding space color,default value is 0xffffffff.
     * Advise return the item color
     */
    public int dividerPaddingColor() {
        return 0xffffffff;
    }

    /**
     * @return the left space width dp,default value is 16.
     */
    public int leftDividerMargin() {
        return 16;
    }

    /**
     * @return the right space width dp,default value is 16.
     */
    public int rightDividerMargin() {
        return 16;
    }

    /**
     * @return the divider height px,default value is 1.
     */
    public int dividerHeight() {
        return 1;
    }

}