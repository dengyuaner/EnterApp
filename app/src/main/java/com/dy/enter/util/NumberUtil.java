package com.dy.enter.util;

import android.text.TextUtils;

/**
 *  类名 NumberUtil
 *  作者 dy
 *  功能 数字操作类
 *  创建日期 2017/1/13 10:26
 *  修改日期 2017/1/13 10:26
 */


public class NumberUtil {

    public static int convertToint(String intStr, int defValue) {
        try {
            return Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return defValue;
    }

    public static long convertTolong(String longStr, long defValue) {
        try {
            return Long.parseLong(longStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return defValue;
    }

    public static float convertTofloat(String fStr, float defValue) {
        if (TextUtils.isEmpty(fStr)) {
            return defValue;
        } else {
            try {
                return Float.parseFloat(fStr);
            } catch (NumberFormatException e) {
                //e.printStackTrace();
            }
        }

        return defValue;
    }

    public static double convertTodouble(String dStr, double defValue) {
        try {
            return Double.parseDouble(dStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return defValue;
    }


    public static Integer convertToInteger(String intStr) {
        try {
            return Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return null;
    }

    public static Long convertToLong(String longStr) {
        try {
            return Long.parseLong(longStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return null;
    }

    public static Float convertToFloat(String fStr) {
        if (TextUtils.isEmpty(fStr)) {
            return 0f;
        } else {
            try {
                return Float.parseFloat(fStr);
            } catch (NumberFormatException e) {
                //e.printStackTrace();
            }
            return 0f;
        }

    }

    public static Double convertToDouble(String dStr) {
        try {
            return Double.parseDouble(dStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return null;
    }

}
