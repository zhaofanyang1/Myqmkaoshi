package com.example.zhao.myqmkaoshi.presenter;

import com.example.zhao.myqmkaoshi.Demo;
import com.example.zhao.myqmkaoshi.modle.Mymodle;
import com.example.zhao.myqmkaoshi.view.Myview;

import java.util.List;

public class Mainpresenter implements Mypresenter, Mymodle.HttpFinish {
    Myview myview;
    Mymodle mymodle;

    public Mainpresenter(Myview myview, Mymodle mymodle) {
        this.myview = myview;
        this.mymodle = mymodle;
    }

    @Override
    public void getData(int page) {
        if (mymodle != null) {
            mymodle.showDemo(this, page);
        }
    }

    @Override
    public void showList(List<Demo.T1371543208049Bean> list) {
        if (myview != null) {
            myview.showList(list);
        }
    }

    @Override
    public void showError(String error) {
        if (myview != null) {
            myview.showError(error);
        }
    }
}
