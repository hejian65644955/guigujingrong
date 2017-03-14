package com.hejian.com.guigujingrong.view;

import android.content.Context;
import android.hejian.com.guigujingrong.R;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.hejian.com.guigujingrong.utils.UiUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by 何健 on 2017/3/13.
 */

public abstract class LoadingPager  extends FrameLayout{

    private  Context mContext;
    private View loadingView;
    private View errorView;
    private View emptyView;
    private View sucessView;
    private LayoutParams params;
    private ResultState resultState;

    public LoadingPager(Context context) {
        super(context);
        this.mContext =context;
        init();
    }


    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        this.mContext =context;
    }

    private int STATE_LOADING = 1; //加载中
    private int STATE_ERROR = 2; //加载失败
    private int STATE_SUCCESS = 3; //加载成功
    private int STATE_EMPTY = 4; //空

    private int current_state = STATE_LOADING;


    private void init() {
        //设置全屏属性
        params= new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        if(loadingView ==null){
             loadingView = View.inflate(mContext, R.layout.page_loading, null);
            this.addView(loadingView,params);
        }
        if(errorView ==null){
            errorView = View.inflate(mContext, R.layout.page_error, null);
            this.addView(errorView,params);
        }
        if(emptyView ==null){
            emptyView = View.inflate(mContext, R.layout.page_empty, null);
            this.addView(emptyView,params);
        }
        //展示布局
        showSaveView();
    }

    private void showSaveView() {
        //展示view保证在主线程
        UiUtils.runOnUiThread(new Runnable(){
            @Override
            public void run() {
                showView();
            }
        });
    }

    private void showView() {

        //是否展示错误页面
        errorView.setVisibility(
                current_state == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        //是否展示加载界面
        loadingView.setVisibility(
                current_state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        //是否展示空页面
        emptyView.setVisibility(
                current_state == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);

        if(sucessView ==null){
            sucessView =View.inflate(mContext,getViewId(),null);
            this.addView(sucessView,params);
        }
        //是否展示成功页面
        sucessView.setVisibility(
                current_state == STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);

    }

     /*
    * 根据不同的网络状态加载相应的页面
    *
    * */

    public void loadData(){
        //加载网络
        AsyncHttpClient httpClient = new AsyncHttpClient();
        /*
        *
        *
        * */
        String url = getUrl();

        if(TextUtils.isEmpty(url)){
            resultState =ResultState.SUCCESS;
            loadImage();
            return;
        }

        httpClient.post(url,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                if (TextUtils.isEmpty(content)){
                    //current_state = STATE_EMPTY;
                    resultState = ResultState.EMPTY;
                    resultState.setJson("");
                }else{
                    resultState = ResultState.SUCCESS;
                    resultState.setJson(content);
                }
                loadImage();
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                resultState = ResultState.ERROR;
                resultState.setJson(content);
                loadImage();
            }

        });
    }

    /*
  *
  * 根据枚举不同的值 来设置不同的状态
  * */
    private void loadImage() {
        switch (resultState){
            case ERROR:
                current_state = STATE_ERROR; //根据枚举值来赋值相应的状态
                break;
            case EMPTY:
                current_state = STATE_EMPTY; //根据枚举值来赋值相应的状态
                break;
            case SUCCESS:
                current_state = STATE_SUCCESS; //根据枚举值来赋值相应的状态
                break;
        }

        showSaveView();
        if (current_state == STATE_SUCCESS){
            //把数据传过去
            onSuccess(resultState,sucessView);
        }
    }

    protected abstract void onSuccess(ResultState resultState, View sucessView);

    public enum ResultState{
        //相当于是三个ResultState对象
        ERROR(2),SUCCESS(3),EMPTY(4);
        private int state;
        ResultState(int state){
            this.state = state;
        }

        private String json;
        public void setJson(String json){
            this.json = json;
        }

        public String getJson(){
            return json;
        }

    }

    protected abstract String getUrl();


    public abstract int getViewId();

}
