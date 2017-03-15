package com.hejian.com.guigujingrong.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 何健 on 2017/3/14.
 */

public abstract class BaseInvestAllAdapter2<T> extends BaseAdapter {
    public List<T> list = new ArrayList<>();

    public BaseInvestAllAdapter2(List<T> list){
        if(list!=null &&list.size()>0){
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

        ViewHolder viewHolder =null;
        if(convertView==null){
            convertView = initView();
            viewHolder = new ViewHolder(convertView);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        T t = list.get(position);
        setData(t,convertView);
        return convertView;
    }

    protected abstract void setData(T t, View convertView);

    protected abstract View initView();

     class ViewHolder{
         ViewHolder(View view){
             view.setTag(this);
         }
     }
}
