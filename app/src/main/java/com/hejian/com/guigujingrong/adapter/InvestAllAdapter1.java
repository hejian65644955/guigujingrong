package com.hejian.com.guigujingrong.adapter;

import android.hejian.com.guigujingrong.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hejian.com.guigujingrong.bean.InvestAllBean;
import com.hejian.com.guigujingrong.view.MyProgressBar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 何健 on 2017/3/14.
 */

public class InvestAllAdapter1 extends BaseInvestAllAdapter1<InvestAllBean.DataBean> {


    public InvestAllAdapter1(List<InvestAllBean.DataBean> list) {
        super(list);
    }

    @Override
    public View getChlidView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_invest_all, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        InvestAllBean.DataBean dataBean = list.get(position);

        viewHolder.pName.setText(dataBean.getName());

        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.p_name)
        TextView pName;
        @Bind(R.id.p_money)
        TextView pMoney;
        @Bind(R.id.p_yearlv)
        TextView pYearlv;
        @Bind(R.id.p_suodingdays)
        TextView pSuodingdays;
        @Bind(R.id.p_minzouzi)
        TextView pMinzouzi;
        @Bind(R.id.p_minnum)
        TextView pMinnum;
        @Bind(R.id.p_progresss)
        MyProgressBar pProgresss;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}