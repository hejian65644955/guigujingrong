package com.hejian.com.guigujingrong.activity;

import android.hejian.com.guigujingrong.R;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hejian.com.guigujingrong.utils.AppNetConfig;
import com.hejian.com.guigujingrong.utils.LoadNet;
import com.hejian.com.guigujingrong.utils.LoadNetHttp;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

public class RegesterActivity extends BaseActivity {

    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.et_register_number)
    EditText etRegisterNumber;
    @Bind(R.id.et_register_name)
    EditText etRegisterName;
    @Bind(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @Bind(R.id.et_register_pwdagain)
    EditText etRegisterPwdagain;
    @Bind(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void initLisetener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etRegisterName.getText().toString();
                String number = etRegisterNumber.getText().toString().trim();
                String pwd = etRegisterPwd.getText().toString().trim();
                String pwdAgain = etRegisterPwdagain.getText().toString().trim();

                //判断两个密码是否一致  判断密码的长度  判断是否注册过

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number) ||
                        TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwdAgain)){
                    showToast("啥都不能为空！！！");
                    return;
                }

                Map<String,String> map = new HashMap<String, String>();
                map.put("name",name);
                map.put("password",pwd);
                map.put("phone",number);
                LoadNet.getDataPost(AppNetConfig.REGISTER, map, new LoadNetHttp() {
                    @Override
                    public void success(String context) {
                        JSONObject jsonObject = JSON.parseObject(context);
                        Boolean isExist = jsonObject.getBoolean("isExist");
                        if(isExist){
                            showToast("账号已经注册过了");
                        }else {
                            showToast("注册成功");
                            finish();
                        }

                    }

                    @Override
                    public void failure(String error) {

                    }
                });
            }
        });

    }

    /*public void isEmpty(String name,String alert){
        if (TextUtils.isEmpty(name)){
            showToast(alert);
            return;
        }
    }*/

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        baseSetting.setVisibility(View.INVISIBLE);
        baseTitle.setText("注册");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_regester;
    }

}
