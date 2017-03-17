package com.hejian.com.guigujingrong.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.hejian.com.guigujingrong.R;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hejian.com.guigujingrong.activity.LineChartActivity;
import com.hejian.com.guigujingrong.activity.MainActivity;
import com.hejian.com.guigujingrong.bean.UserInfo;
import com.hejian.com.guigujingrong.utils.AppNetConfig;
import com.hejian.com.guigujingrong.utils.BitmapUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.Bind;

/**
 * Created by 何健 on 2017/3/10.
 */

public class PropertyFragment extends BaseFragment {

    @Bind(R.id.tv_settings)
    TextView tvSettings;
    @Bind(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @Bind(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @Bind(R.id.tv_me_name)
    TextView tvMeName;
    @Bind(R.id.rl_me)
    RelativeLayout rlMe;
    @Bind(R.id.recharge)
    ImageView recharge;
    @Bind(R.id.withdraw)
    ImageView withdraw;
    @Bind(R.id.ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichan)
    TextView llZichan;

    @Override
    protected void initData(String json) {
        initListener();
        MainActivity activity = (MainActivity) getActivity();
        UserInfo userInfo = activity.getUser();
        //设置头像名
        tvMeName.setText(userInfo.getData().getName());
        //设置头像
//        Picasso.with(getActivity())
//                .load(AppNetConfig.BASE_URL+"/images/tx.png")
//                .transform(new CropCircleTransformation())
//                .transform(new ColorFilterTransformation(Color.parseColor("#66ffccff")))
//                //第二个参数值越大越模糊
//                .transform(new BlurTransformation(getActivity(),10))
//                .into(ivMeIcon);

        Picasso.with(getActivity())
                .load(AppNetConfig.BASE_URL+"/images/tx.png")
                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap source) {
                        Bitmap bitmap = BitmapUtils.circleBitmap(source);
                        source.recycle();//释放原来的图片
                        return bitmap;
                    }
                    @Override
                    public String key() {

                        return "";
                    }
                }).into(ivMeIcon);

    }

    @Override
    public int getLayoutid() {
        return R.layout.fragment_property;
    }

    @Override
    public String getChildUrl() {
        return null;
    }

    @Override
    protected void initListener() {
        llZichan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        llTouziZhiguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        llTouzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),LineChartActivity.class));
            }
        });
    }
}
