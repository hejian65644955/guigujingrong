package com.hejian.com.guigujingrong.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 何健 on 2017/3/14.
 */

public abstract class BaseInvestAllAdapter1<T> extends BaseAdapter {

    public List<T> list =new ArrayList<>();

    public BaseInvestAllAdapter1(List<T> list) {
        if(list!=null && list.size()>0){
            this.list.clear();
            this.list.addAll(list);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = getChlidView(position, convertView, parent);
        return view;
    }

    public abstract View getChlidView(int position, View convertView, ViewGroup parent);
}
