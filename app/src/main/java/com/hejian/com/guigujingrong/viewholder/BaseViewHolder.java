package com.hejian.com.guigujingrong.viewholder;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by 何健 on 2017/3/14.
 */

public abstract class BaseViewHolder<T> {

    private T t;
    private final View rootView;

    public BaseViewHolder(){
        rootView = initView();
        ButterKnife.bind(this,rootView);
        rootView.setTag(this);
    }

    protected abstract View initView();

    public  void setData(T t) {
        this.t =t;
        setChildData();

    }

    protected abstract void setChildData();

    public T getT(){
        return t;
    }

    public View getView() {
        return rootView;
    }
}
