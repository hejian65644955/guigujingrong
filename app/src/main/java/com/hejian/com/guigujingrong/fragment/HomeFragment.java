package com.hejian.com.guigujingrong.fragment;


import android.content.Context;
import android.hejian.com.guigujingrong.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.hejian.com.guigujingrong.bean.HomeBean;
import com.hejian.com.guigujingrong.utils.AppNetConfig;
import com.hejian.com.guigujingrong.utils.LoadNet;
import com.hejian.com.guigujingrong.utils.LoadNetHttp;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by 何健 on 2017/3/10.
 */

public class HomeFragment extends BaseFragment {

    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.tv_home_product)
    TextView tvHomeProduct;
    @Bind(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;
    @Bind(R.id.banner)
    Banner banner;

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();

        initListener();
    }

    private void initListener() {
        baseTitle.setText("首页");
        baseBack.setVisibility(View.INVISIBLE);
        baseSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    public void initData() {
         /*
        * 二次封装
        * 为什么要二次封装
        *
        * 第一  调用的方便
        * 第二  修改和维护方便
        * */

        LoadNet.getDataPost(AppNetConfig.INDEX, new LoadNetHttp() {
            @Override
            public void success(String context) {
                Log.i("http", "success: ");
                HomeBean homeBean = JSON.parseObject(context, HomeBean.class);
                tvHomeProduct.setText(homeBean.getProInfo().getName());
                initBanner(homeBean);


            }

            @Override
            public void failure(String error) {
                Log.i("http", "failure: " + error);

            }
        });
    }

    private void initBanner(HomeBean homeBean) {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        List<String> urls = new ArrayList<>();
        for(int i = 0; i <homeBean.getImageArr().size() ; i++) {
            urls.add(AppNetConfig.BASE_URL+homeBean.getImageArr().get(i).getIMAURL());

        }
        //设置图片集合
        banner.setImages(urls);
        banner.start();

    }

    class GlideImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Picasso.with(context)
                    .load((String)path)
                    .into(imageView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
