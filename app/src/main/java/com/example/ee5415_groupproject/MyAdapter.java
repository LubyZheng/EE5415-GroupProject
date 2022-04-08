package com.example.ee5415_groupproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
5
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends BaseAdapter  {
    //上下文
    private Context context;
    //数据项
    private List<String> data;
    public MyAdapter(List<String> data){
        this.data = data;
    }
    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(context == null)
            context = viewGroup.getContext();
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items,null);
            viewHolder = new ViewHolder();
            viewHolder.mText = (TextView)view.findViewById(R.id.textView);
            viewHolder.mBtn = (Button)view.findViewById(R.id.btn_edit);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder)view.getTag();
        viewHolder.mText.setOnClickListener(this);
        viewHolder.mBtn.setOnClickListener(this);
        return view;
    }


    static class ViewHolder{
        TextView mText;
        Button mBtn;
    }

}