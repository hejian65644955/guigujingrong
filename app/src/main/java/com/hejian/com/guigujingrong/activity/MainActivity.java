package com.hejian.com.guigujingrong.activity;


import android.hejian.com.guigujingrong.R;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hejian.com.guigujingrong.fragment.HomeFragment;
import com.hejian.com.guigujingrong.fragment.InvestFragment;
import com.hejian.com.guigujingrong.fragment.MoreFragment;
import com.hejian.com.guigujingrong.fragment.PropertyFragment;
import com.hejian.com.guigujingrong.utils.AppManger;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

import static android.hejian.com.guigujingrong.R.id.fl_main_content;


public class MainActivity extends BaseActivity {

    @Bind(R.id.fl_main_content)
    FrameLayout flMainContent;
    @Bind(R.id.main_rg)
    RadioGroup mainRg;
    private InvestFragment investFragment;
    private HomeFragment homeFragment;
    private MoreFragment moreFragment;
    private PropertyFragment propertyFragment;
    private boolean isDouble = false;



    public void initData() {
        AppManger.getInstance().AddActivity(MainActivity.this);
        switchFragment(R.id.rb_main);
    }

    @Override
    protected void initTitle() {


    }

    @Override
    protected int getLayoutId() {
        // 去掉窗口标题
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏顶部的状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_main;
    }

    public void initLisetener() {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
    }

    private void switchFragment(int checkedId) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hiddenFragment(ft);
        switch (checkedId) {
            case R.id.rb_invest:
                if (investFragment == null) {
                    investFragment = new InvestFragment();
                    ft.add(fl_main_content, investFragment);
                }
                ft.show(investFragment);
                break;
            case R.id.rb_main:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(fl_main_content, homeFragment);
                }
                ft.show(homeFragment);
                break;
            case R.id.rb_more:
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                    ft.add(fl_main_content, moreFragment);
                }
                ft.show(moreFragment);
                break;
            case R.id.rb_propert:
                if (propertyFragment == null) {
                    propertyFragment = new PropertyFragment();
                    ft.add(fl_main_content, propertyFragment);
                }
                ft.show(propertyFragment);
                break;
        }
        ft.commit();
    }

    private void hiddenFragment(FragmentTransaction ft) {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (investFragment != null) {
            ft.hide(investFragment);
        }
        if (moreFragment != null) {
            ft.hide(moreFragment);
        }
        if (propertyFragment != null) {
            ft.hide(propertyFragment);
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isDouble) {
                finish();
            }
            isDouble = true;
            Toast.makeText(MainActivity.this, "再次点击退出", Toast.LENGTH_SHORT).show();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isDouble = false;
                }
            }, 2000);
            return true;

        }
        return super.onKeyUp(keyCode, event);
    }
}
