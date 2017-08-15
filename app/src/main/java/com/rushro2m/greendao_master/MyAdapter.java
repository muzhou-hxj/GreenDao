package com.rushro2m.greendao_master;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rushro2m.greendao_master.bean.MovieBean;

import java.util.List;

/**
 * 作者：Rushro2m on 2017/8/15.
 * 邮箱：haoxujie1993@gmail.com
 * 版本：v1.0
 */


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<MovieBean> mData;
    private LayoutInflater inflater;
    private Context mContext;

    public MyAdapter(List<MovieBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText("ID:" + mData.get(position).getId() + "--->TITLE:" + mData.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
