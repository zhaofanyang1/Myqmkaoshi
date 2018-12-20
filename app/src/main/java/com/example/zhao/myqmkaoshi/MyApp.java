package com.example.zhao.myqmkaoshi;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.setLogEnabled(true);
        //初始化
        UMConfigure.init(this, "5c1715caf1f5560add00004d", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
}
