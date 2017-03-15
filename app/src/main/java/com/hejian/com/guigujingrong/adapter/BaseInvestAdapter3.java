package com.hejian.com.guigujingrong.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hejian.com.guigujingrong.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 何健 on 2017/3/14.
 */

public abstract class BaseInvestAdapter3<T> extends BaseAdapter {
    private List<T> list = new ArrayList<>();

    public BaseInvestAdapter3(List<T> list) {
        if (list != null && list.size() > 0) {
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
        BaseViewHolder baseViewHolder = null;
        if (convertView == null) {
            baseViewHolder = getHolder();
        } else {
            baseViewHolder = (BaseViewHolder) convertView.getTag();
        }
        T t = list.get(position);
        baseViewHolder.setData(t);
        return baseViewHolder.getView();
    }

    protected abstract BaseViewHolder getHolder();
}
