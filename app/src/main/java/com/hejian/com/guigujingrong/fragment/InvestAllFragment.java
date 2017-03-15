package com.hejian.com.guigujingrong.fragment;

import android.hejian.com.guigujingrong.R;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.hejian.com.guigujingrong.adapter.InvestAllAdapter3;
import com.hejian.com.guigujingrong.bean.InvestAllBean;
import com.hejian.com.guigujingrong.utils.AppNetConfig;

import butterknife.Bind;

/**
 * Created by 何健 on 2017/3/14.
 */

public class InvestAllFragment extends BaseFragment {
    @Bind(R.id.invest_all_lv)
    ListView investAllLv;

    @Override
    protected void initData(String json) {
        InvestAllBean investAllBean = JSON.parseObject(json, InvestAllBean.class);
       /* InvestAllAdapter adapter =
                new InvestAllAdapter(investAllBean.getData());*/
          /* InvestAllAdapter1 adapter =
                new InvestAllAdapter1(investAllBean.getData());
        investAllLv.setAdapter(adapter);*/
         /*  InvestAllAdapter2 adapter =
                new InvestAllAdapter2(investAllBean.getData());
        investAllLv.setAdapter(adapter);*/
           InvestAllAdapter3 adapter =
                new InvestAllAdapter3(investAllBean.getData());
        investAllLv.setAdapter(adapter);

    }

    @Override
    public int getLayoutid() {
        return R.layout.fragment_invest_all;
    }

    @Override
    public String getChildUrl() {

        return AppNetConfig.PRODUCT;
    }

    @Override
    protected void initListener() {

    }
}
