package com.hejian.com.guigujingrong.utils;

import android.content.Context;
import android.view.View;

/**
 * Created by 何健 on 2017/3/12.
 */

public class UiUtils {

    public static Context getContext(){
        return MyApplication.getContext();
    }

    public static View getView(int layoutid){
        return View.inflate(getContext(),layoutid,null);
    }

    public static int getColor(int color){
        return getContext().getResources().getColor(color);
    }

    public static String[] getStringArray(int stringid){
        return getContext().getResources().getStringArray(stringid);
    }


    //与屏幕分辨率相关的
    public static int dp2px(int dp){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);

    }

    public static int px2dp(int px){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    public static void runOnUiThread(Runnable runnable) {
        //比较pid来判断是不是主线程
        if(MyApplication.getThreadid()== android.os.Process.myPid()){
            runnable.run();
        }else {
            MyApplication.getHandler().post(runnable);
        }
    }
}
