package com.hejian.com.guigujingrong.activity;

import android.content.Intent;
import android.hejian.com.guigujingrong.R;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hejian.com.guigujingrong.bean.UserInfo;
import com.hejian.com.guigujingrong.utils.AppNetConfig;
import com.hejian.com.guigujingrong.utils.LoadNet;
import com.hejian.com.guigujingrong.utils.LoadNetHttp;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

public class LoginActivity extends BaseActivity {


    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.tv_login_number)
    TextView tvLoginNumber;
    @Bind(R.id.login_et_number)
    EditText loginEtNumber;
    @Bind(R.id.rl_login)
    RelativeLayout rlLogin;
    @Bind(R.id.tv_login_pwd)
    TextView tvLoginPwd;
    @Bind(R.id.login_et_pwd)
    EditText loginEtPwd;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.login_regitster_tv)
    TextView loginRegitsterTv;

    @Override
    protected void initLisetener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        loginRegitsterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegesterActivity.class));
            }
        });

    }

    private void Login() {
        //校验
        String phone = loginEtNumber.getText().toString().trim();
        String pw = loginEtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            showToast("账号不能为空");
            return;
        }
        if (TextUtils.isEmpty(pw)){
            showToast("密码不能为空");
            return;
        }

        Map<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("password",pw);

        LoadNet.getDataPost(AppNetConfig.LOGIN, map, new LoadNetHttp() {
            @Override
            public void success(String context) {
                Log.e("TAG", "成功"+context);
                JSONObject jsonObject = JSON.parseObject(context);
                Boolean success = jsonObject.getBoolean("success");
                if(success){
                    UserInfo userInfo = JSON.parseObject(context, UserInfo.class);
                    //保存数据到sp
                    saveUser(userInfo);
                    //跳转
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }else {
                    showToast("账号不存在或者密码不正确");
                }
            }

            @Override
            public void failure(String error) {
                Log.e("TAG", "失败"+error);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        baseBack.setVisibility(View.INVISIBLE);
        baseSetting.setVisibility(View.INVISIBLE);
        baseTitle.setText("登录");


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

}
