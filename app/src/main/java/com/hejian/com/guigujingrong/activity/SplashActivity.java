package com.hejian.com.guigujingrong.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hejian.com.guigujingrong.R;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hejian.com.guigujingrong.utils.AppManger;

import butterknife.Bind;

public class SplashActivity extends BaseActivity {

    @Bind(R.id.splash_tv_version)
    TextView splashTvVersion;
    @Bind(R.id.activity_splash)
    RelativeLayout activitySplash;


    @Override
    protected void initLisetener() {

    }

    @Override
    public void initData() {
        AppManger.getInstance().AddActivity(this);
        //设置版本号
        setVersion();

        //设置动画
        setAnimation();
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    private void setAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isLogin()) {
                    //登录过进入
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else {
                    //没有登录过进入
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();

                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        activitySplash.startAnimation(animation);
    }

    private boolean isLogin() {
        if(TextUtils.isEmpty(getUser().getData().getName())){
            return false;
        }

        return true;
    }

    private void setVersion() {
        splashTvVersion.setText(getVersion());
    }

    private String getVersion() {
        try {
            //拿到包管理器
            PackageManager packageManager = getPackageManager();
            //拿到包信息
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            //每次发布新一版本要加一
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;
            return versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        return "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManger.getInstance().remove(this);
    }
}
