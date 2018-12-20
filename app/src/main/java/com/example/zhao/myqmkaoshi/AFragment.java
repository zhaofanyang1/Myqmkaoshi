package com.example.zhao.myqmkaoshi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends Fragment implements View.OnClickListener {


    private WebView web;
    private Button share;

    private List<String> list = new ArrayList<>();
    private String s;

    public AFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_a, container, false);
        initView(inflate);

        //初始化EventBus,注册
        EventBus.getDefault().register(this);
        return inflate;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveMessage(String json) {
        this.s = json;
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

     /*   //  FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        //FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        View inflate = View.inflate(getContext(), R.layout.activity_main, null);
        XRecyclerView xy = inflate.findViewById(R.id.xy);
        FrameLayout fm = inflate.findViewById(R.id.fm);
        xy.setVisibility(View.VISIBLE);
        fm.setVisibility(View.GONE);
        //fragmentTransaction.add(R.id.fm,)*/
        getActivity().finish();
    }

    private void initView(View inflate) {
        web = (WebView) inflate.findViewById(R.id.web);
        share = (Button) inflate.findViewById(R.id.share);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share:
                UMWeb umWeb = new UMWeb(s);
                //UMImage image = new UMImage(getContext(), R.mipmap.ic_launcher);//网络图片
                new ShareAction(getActivity()).withMedia(umWeb).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);
    }
}
