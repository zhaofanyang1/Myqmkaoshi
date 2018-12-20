package com.example.zhao.myqmkaoshi.modle;

import com.example.zhao.myqmkaoshi.Demo;

import java.util.List;

public interface Mymodle {
    interface HttpFinish {
        void showList(List<Demo.T1371543208049Bean> list);

        void showError(String error);
    }

    void showDemo(HttpFinish httpFinish, int page);
}
