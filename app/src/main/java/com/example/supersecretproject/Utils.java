package com.example.supersecretproject;

import android.content.Context;

public class Utils {

//tracks the scratched activity in Profile_activity
    public static float dipToPx(Context context, float dipValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return dipValue * density;
    }
}
