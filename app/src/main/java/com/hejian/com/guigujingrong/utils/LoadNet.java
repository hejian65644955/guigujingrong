package com.hejian.com.guigujingrong.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by 何健 on 2017/3/12.
 */

public class LoadNet {
    public static void getDataPost(String url,final LoadNetHttp loadNetHttp){
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(url,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                if (loadNetHttp != null) {
                    loadNetHttp.success(content);
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                if (loadNetHttp != null) {
                    loadNetHttp.failure(content);
                }
            }
        });
    }
}
