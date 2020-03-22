package com.training.coronavirus;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Pair;

public class ScreenSize {


    public static Pair<Integer,Integer> getScreenSize(Context context){

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        return new Pair<>(height,width);

    }
}
