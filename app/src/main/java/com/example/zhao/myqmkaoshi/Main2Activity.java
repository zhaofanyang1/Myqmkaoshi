package com.example.zhao.myqmkaoshi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private WebView web;
    private Button share;
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        //初始化EventBus,注册
        EventBus.getDefault().register(this);
    }

    private void initView() {
        web = (WebView) findViewById(R.id.web);
        share = (Button) findViewById(R.id.share);

        share.setOnClickListener(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveMessage(String json) {
        list.add(json);
        Log.e("222222222", "json****************" + json.toString());
        //Webview 跳转百度页面
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        web.loadUrl(json);
        share.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share:

                UMImage image = new UMImage(this, R.mipmap.ic_launcher);//网络图片
                new ShareAction(this).withText("hello").withMedia(image).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {
                                Log.e("liangxq", "onResult");
                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                Log.e("liangxq", throwable.getMessage());
                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        }).open();

                break;
        }
    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        }
}
