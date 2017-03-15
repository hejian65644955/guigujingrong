package com.hejian.com.guigujingrong.adapter;

import android.hejian.com.guigujingrong.R;
import android.view.View;
import android.widget.TextView;

import com.hejian.com.guigujingrong.bean.InvestAllBean;
import com.hejian.com.guigujingrong.utils.UiUtils;

import java.util.List;

/**
 * Created by 何健 on 2017/3/14.
 */

public class InvestAllAdapter2 extends BaseInvestAllAdapter2<InvestAllBean.DataBean> {
    public InvestAllAdapter2(List list) {
        super(list);
    }


    @Override
    protected void setData(InvestAllBean.DataBean dataBean, View convertView) {
        TextView textView = (TextView) convertView.findViewById(R.id.p_name);
        textView.setText(dataBean.getName());
    }

    @Override
    protected View initView() {
        return UiUtils.getView(R.layout.adapter_invest_all);
    }
}
