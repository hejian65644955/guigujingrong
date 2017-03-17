package com.hejian.com.guigujingrong.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by 何健 on 2017/3/12.
 */

public class AppManger {
    /**
     * 统一应用程序中所有的Activity的栈管理（单例）
     * 涉及到activity的添加、删除指定、删除当前、删除所有、返回栈大小的方法
     */

    private AppManger(){}

    private static AppManger appManger = new AppManger();

    public static AppManger getInstance(){
        return appManger;
    }

    private Stack<Activity> stack = new Stack<>();

    public void AddActivity(Activity activity){
        if(activity!=null){
            stack.add(activity);
        }
    }

    public void removeAll(){
        for(int i = stack.size()-1; i >=0 ; i--) {
            Activity currentActivity = stack.get(i);
            currentActivity.finish();
            stack.remove(currentActivity);
        }
    }

    public void removeCurrentActivity(){
        Activity activity = stack.lastElement();
        //Activity activity = stack.get(stack.size()-1);
        activity.finish();
        stack.remove(activity);
    }

    public int getStackSize(){
        return stack.size();
    }

    //从stack中删除activity
    public void remove(Activity activity){
        if(activity!=null){
            for(int i = stack.size()-1; i >=0 ; i--) {
                Activity currentActivity = stack.get(i);
                if(currentActivity == activity){
                    stack.remove(currentActivity);
                }
            }
        }
    }
}
