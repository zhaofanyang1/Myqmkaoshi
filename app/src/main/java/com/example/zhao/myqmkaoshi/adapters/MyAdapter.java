package com.example.zhao.myqmkaoshi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.zhao.myqmkaoshi.Demo;
import com.example.zhao.myqmkaoshi.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class MyAdapter extends XRecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Demo.T1371543208049Bean> list;
    private Context context;
    int a = 0;

    public MyAdapter(List<Demo.T1371543208049Bean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item, null));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.source.setText(list.get(position).getSource());
        final RequestOptions requestOptions = RequestOptions.bitmapTransform(new CircleCrop());
        Glide.with(context)
                .load(list.get(position).getImgsrc())
                .apply(requestOptions)
                .into(holder.img);
        Log.e("MyAdapter", list.get(position).getImgsrc().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setdata != null) {
                    setdata.show(position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (a % 2 == 0) {
                    RequestOptions override = RequestOptions.bitmapTransform(new RoundedCorners(60)).override(300, 600);
                    Glide.with(context)
                            .load(list.get(position).getImgsrc())
                            .apply(override)
                            .into(holder.img);
                } else {
                    RequestOptions requestOptions = RequestOptions.bitmapTransform(new CircleCrop());
                    Glide.with(context)
                            .load(list.get(position).getImgsrc())
                            .apply(requestOptions)
                            .into(holder.img);
                }
                a++;
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView source;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            source = itemView.findViewById(R.id.source);
            img = itemView.findViewById(R.id.img);
        }
    }

    public setData setdata;

    public void setSetdata(setData setdata) {
        this.setdata = setdata;
    }

    public interface setData {
        void show(int position);
    }

    public setlongClickListener setlickListener;

    public void setSetlickListener(setlongClickListener setlickListener) {
        this.setlickListener = setlickListener;
    }

    public interface setlongClickListener {
        void ClickListener(int position);
    }
}
