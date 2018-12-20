package com.example.zhao.myqmkaoshi.modle;

import android.util.Log;

import com.example.zhao.myqmkaoshi.Demo;
import com.example.zhao.myqmkaoshi.https.HttpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Mainmodle implements Mymodle {
    @Override
    public void showDemo(final HttpFinish httpFinish, int page) {
        Observable<Demo> getdemo = HttpUtils.getHttpUtils().getMyserver().getdemo(page + "", "zfy");
        getdemo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Demo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Demo value) {
                        List<Demo.T1371543208049Bean> t1371543208049 = value.getT1371543208049();
                        httpFinish.showList(t1371543208049);
                        Log.e("Mainmodle", "value-----" + t1371543208049.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Mainmodle", "onError" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
