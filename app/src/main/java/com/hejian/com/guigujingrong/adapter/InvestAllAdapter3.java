package com.hejian.com.guigujingrong.adapter;

import com.hejian.com.guigujingrong.viewholder.BaseViewHolder;
import com.hejian.com.guigujingrong.viewholder.InvestHolder;

import java.util.List;

/**
 * Created by 何健 on 2017/3/14.
 */

public class InvestAllAdapter3 extends BaseInvestAdapter3 {
    public InvestAllAdapter3(List list) {
        super(list);
    }

    @Override
    protected BaseViewHolder getHolder() {
        return new InvestHolder();
    }

}
