package com.hejian.com.guigujingrong.fragment;


import android.content.Context;
import android.hejian.com.guigujingrong.R;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.hejian.com.guigujingrong.bean.HomeBean;
import com.hejian.com.guigujingrong.utils.AppNetConfig;
import com.hejian.com.guigujingrong.utils.ThreadPool;
import com.hejian.com.guigujingrong.view.MyProgressBar;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


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
    @Bind(R.id.home_progress)
    MyProgressBar homeProgress;


    @Override
    public String getChildUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData(String json) {
        HomeBean homeBean = JSON.parseObject(json, HomeBean.class);
        //Log.i("http", "success: "+homeBean.getImageArr().size());
        tvHomeYearrate.setText(homeBean.getProInfo().getYearRate() + "%");
        tvHomeProduct.setText(homeBean.getProInfo().getName());
        //注意：展示UI一定要判断是不是主线程
        initProgress(homeBean.getProInfo());
        initBanner(homeBean);
    }

    private void initProgress(final HomeBean.ProInfoBean proInfo) {
        ThreadPool.getInstance().getGlobalThread().execute(new Runnable() {
            @Override
            public void run() {
                int progress = Integer.parseInt(proInfo.getProgress());
                for(int i = 0; i <=progress ; i++) {
                    SystemClock.sleep(20);
                    homeProgress.setProgress(i);

                }
            }
        });

    }

    private void initBanner(HomeBean homeBean) {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < homeBean.getImageArr().size(); i++) {
            urls.add(AppNetConfig.BASE_URL + homeBean.getImageArr().get(i).getIMAURL());

        }
        //设置图片集合
        banner.setImages(urls);
        banner.start();

    }


    class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Picasso.with(context)
                    .load((String) path)
                    .into(imageView);
        }
    }


}
