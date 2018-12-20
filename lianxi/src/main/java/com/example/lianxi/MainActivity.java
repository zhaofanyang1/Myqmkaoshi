package com.example.lianxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show();
    }

    //555555创建一个金牌用户和银牌用户
    private void show() {
        //使用工厂类创建一个金牌用户
        ICustomer goldCustomer = CustomerFactory.createGoldCustomer();
        // 使用工厂类创建一个银牌用户
        ICustomer silverCustomer = CustomerFactory.createSilverCustomer();
        Log.e("FactoryPattern", goldCustomer.describe());
        Log.e("FactoryPattern", silverCustomer.describe());
    }

}

//2222金牌用户实现类
class GoldCustomer implements ICustomer {
    @Override
    public String describe() {
        return "金牌用户";
    }
}

//3333银牌用户实现类
class SilverCustomer implements ICustomer {
    @Override
    public String describe() {
        return "银牌用户";
    }
}

//4444创建用户的工厂类
class CustomerFactory {
    //创建一个金牌用户
    public static ICustomer createGoldCustomer() {
        return new GoldCustomer();
    }

    // 创建一个银牌用户
    public static ICustomer createSilverCustomer() {
        return new SilverCustomer();
    }
}

//11111定义一个用户接口
interface ICustomer {
    String describe();
}