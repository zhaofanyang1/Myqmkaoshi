package com.example.zhao.myqmkaoshi.https;

import com.example.zhao.myqmkaoshi.Demo;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface MyServer {
    //http://c.m.163.com/nc/article/list/T1371543208049/0-20.html
    String url = "http://c.m.163.com/nc/article/list/T1371543208049/";

    @GET("{page}-20.html")
    Observable<Demo> getdemo(@Path("page") String page, @Header("User-Agent") String head);
}
