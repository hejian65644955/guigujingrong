package com.hejian.com.guigujingrong.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hejian.com.guigujingrong.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hejian.com.guigujingrong.utils.AppManger;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.splash_tv_version)
    TextView splashTvVersion;
    @Bind(R.id.activity_splash)
    RelativeLayout activitySplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

//        Log.i("aaa", "onCreate: "+1/0);

        AppManger.getInstance().AddActivity(this);

        initData();
    }

    private void initData() {
        //设置版本号
        setVersion();

        //设置动画
        setAnimation();
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
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        activitySplash.startAnimation(animation);
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


}
