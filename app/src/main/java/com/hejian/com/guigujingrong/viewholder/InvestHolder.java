package com.hejian.com.guigujingrong.viewholder;

import android.hejian.com.guigujingrong.R;
import android.view.View;
import android.widget.TextView;

import com.hejian.com.guigujingrong.bean.InvestAllBean;
import com.hejian.com.guigujingrong.utils.UiUtils;
import com.hejian.com.guigujingrong.view.MyProgressBar;

import butterknife.Bind;

/**
 * Created by 何健 on 2017/3/14.
 */

public class InvestHolder extends BaseViewHolder<InvestAllBean.DataBean> {
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

    @Override
    protected View initView() {
        return UiUtils.getView(R.layout.adapter_invest_all);
    }

    @Override
    protected void setChildData() {
        InvestAllBean.DataBean dataBean = getT();
        pName.setText(dataBean.getName());
    }

}
