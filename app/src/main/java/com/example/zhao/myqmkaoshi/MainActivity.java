package com.example.zhao.myqmkaoshi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.zhao.myqmkaoshi.adapters.MyAdapter;
import com.example.zhao.myqmkaoshi.modle.Mainmodle;
import com.example.zhao.myqmkaoshi.presenter.Mainpresenter;
import com.example.zhao.myqmkaoshi.presenter.Mypresenter;
import com.example.zhao.myqmkaoshi.view.Myview;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Myview, XRecyclerView.LoadingListener, MyAdapter.setData, MyAdapter.setlongClickListener {

    private XRecyclerView xy;
    private List<Demo.T1371543208049Bean> lists = new ArrayList<>();
    private int page = 0;
    private MyAdapter adapter;
    private Mypresenter mypresenter;
    private FrameLayout fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mypresenter = new Mainpresenter(this, new Mainmodle());
        mypresenter.getData(page);
    }

    private void initView() {
        xy = (XRecyclerView) findViewById(R.id.xy);
        fm = (FrameLayout) findViewById(R.id.fm);
        adapter = new MyAdapter(lists, this);
        xy.setAdapter(adapter);
        xy.setLayoutManager(new LinearLayoutManager(this));
        xy.setLoadingListener(this);
        adapter.setSetdata(this);
        adapter.setSetlickListener(this);

    }

    @Override
    public void showList(List<Demo.T1371543208049Bean> list) {
        lists.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Log.e("MainActivity", error);
    }

    @Override
    public void onRefresh() {
        page = 0;
        mypresenter.getData(page);
        xy.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        page += 10;
        mypresenter.getData(page);
        xy.loadMoreComplete();
    }

    //点击事件
    @Override
    public void show(final int position) {
        // Intent intent = new Intent(MainActivity.this, Main2Activity.class);
       /* Demo.T1371543208049Bean t1371543208049Bean = lists.get(position);
        String url = t1371543208049Bean.getUrl();
        intent.putExtra("url", url);*/
        new Thread("posting") {
            @Override
            public void run() {
                EventBus.getDefault().postSticky(lists.get(position).getUrl());
            }
        }.start();
        xy.setVisibility(XRecyclerView.GONE);
        fm.setVisibility(XRecyclerView.VISIBLE);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fm, new AFragment());
        fragmentTransaction.commit();
        //  startActivity(intent);
    }

    @Override
    public void ClickListener(int position) {
        Toast.makeText(this, "长按点击事件", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onPause() {
        super.onPause();
        xy.setVisibility(View.VISIBLE);
        fm.setVisibility(View.GONE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        xy.setVisibility(View.VISIBLE);
        fm.setVisibility(View.GONE);
    }
}
