package com.hejian.com.guigujingrong.fragment;

import android.graphics.Color;
import android.hejian.com.guigujingrong.R;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hejian.com.guigujingrong.adapter.InvesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 何健 on 2017/3/10.
 */

public class InvestFragment extends BaseFragment {
    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.invest_vp)
    ViewPager investVp;
    @Bind(R.id.tv_invest_all)
    TextView tvInvestAll;
    @Bind(R.id.tv_invest_recommend)
    TextView tvInvestRecommend;
    @Bind(R.id.tv_invest_hot)
    TextView tvInvestHot;

    @Override
    protected void initData(String json) {
        //设置标题
        initTitle();
        initFragment();
        //初始化viewpager
        initViewPage();

        initListener();

        initTab();
    }

    private void initTab() {
        selectText(0);
    }

    private void selectText(int id) {
        //把原来所有的背景颜色还原
        hiddenTextViewState();
        switch (id){
            case 0:
                tvInvestAll.setBackgroundColor(Color.BLUE);
                break;
            case 1:
                tvInvestRecommend.setBackgroundColor(Color.BLUE);
                break;
            case 2:
                tvInvestHot.setBackgroundColor(Color.BLUE);
                break;
        }
    }

    private void hiddenTextViewState() {
        tvInvestRecommend.setBackgroundColor(Color.WHITE);
        tvInvestHot.setBackgroundColor(Color.WHITE);
        tvInvestAll.setBackgroundColor(Color.WHITE);

    }

    public void initListener() {
        investVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectText(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tvInvestAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                investVp.setCurrentItem(0);
            }
        });

        tvInvestHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                investVp.setCurrentItem(2);
            }
        });
        tvInvestRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                investVp.setCurrentItem(1);
            }
        });

    }

    private void initViewPage() {
        investVp.setAdapter(new InvesAdapter(getChildFragmentManager(), fragments));

    }

    private List<BaseFragment> fragments = new ArrayList<>();

    private void initFragment() {
        fragments.add(new InvestAllFragment());
        fragments.add(new InvestRecommendFragment());
        fragments.add(new InvestHotFragment());

    }

    private void initTitle() {
        baseSetting.setVisibility(View.GONE);
        baseTitle.setText("投资");
        baseBack.setVisibility(View.GONE);

    }

    @Override
    public int getLayoutid() {
        return R.layout.fragment_invest;
    }

    @Override
    public String getChildUrl() {
        return null;
    }
}
