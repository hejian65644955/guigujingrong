package com.hejian.com.guigujingrong.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hejian.com.guigujingrong.bean.DataBean;
import com.hejian.com.guigujingrong.bean.UserInfo;

import butterknife.ButterKnife;

/**
 * Created by 何健 on 2017/3/15.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initTitle();
        initData();
        initLisetener();
    }

    protected abstract void initLisetener();

    protected abstract void initData();

    protected abstract void initTitle();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected void showToast(String context){
        Toast.makeText(BaseActivity.this, context, Toast.LENGTH_SHORT).show();

    }

    //保存用户信息
    public void saveUser(UserInfo userInfo){
        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("imageurl",userInfo.getData().getImageurl());
        edit.putString("iscredit",userInfo.getData().getIscredit());
        edit.putString("name",userInfo.getData().getName());
        edit.putString("number",userInfo.getData().getPhone());
        edit.commit();
    }
    //获取用户信息
    public UserInfo getUser(){
        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        String imageurl = sp.getString("imageurl", "");
        String iscredit = sp.getString("iscredit", "");
        String name = sp.getString("name", "");
        String number = sp.getString("number", "");
        UserInfo userInfo = new UserInfo();
        DataBean dataBean = new DataBean();
        dataBean.setImageurl(imageurl);
        dataBean.setIscredit(iscredit);
        dataBean.setName(name);
        dataBean.setPhone(number);
        userInfo.setData(dataBean);
        return userInfo;
    }
}
