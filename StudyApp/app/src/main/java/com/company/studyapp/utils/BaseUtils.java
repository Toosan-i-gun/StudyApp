package com.company.studyapp.utils;

import android.content.Context;
import android.util.TypedValue;

public class BaseUtils {
    public static int sp2px(Context context, int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,context.getResources().getDisplayMetrics());
    }
}
