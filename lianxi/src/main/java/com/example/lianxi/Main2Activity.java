package com.example.lianxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        show();
    }

    private void show() {
        Customer aCustomer = fuzhangFactory.createACustomer();
        Customer bCustomer = fuzhangFactory.createBCustomer();
        Log.e("Main2Activity", "aCustomer:" + aCustomer.buyyifu());
        Log.e("Main2Activity", "bCustomer:" + bCustomer.buyyifu());
    }
}

class fuzhangFactory {
    public static Customer createACustomer() {

        return new ACustomer();
    }

    public static Customer createBCustomer() {

        return new BCustomer();
    }
}

//顾客马恩东
class ACustomer implements Customer {

    @Override
    public String buyyifu() {
        return "顾客马恩东的羽绒服";
    }
}

//顾客李涛
class BCustomer implements Customer {

    @Override
    public String buyyifu() {
        return "顾客李涛的仙女裙";
    }
}

//定义买衣服接口
//Customer--顾客
interface Customer {
    String buyyifu();
}
