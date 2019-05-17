package com.example.a11699.all.CustomLayoutManger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a11699.all.R;

import java.util.List;

/**
 * 作者：余智强
 * 2018/12/26
 */
public class RecycleAdapter extends RecyclerView.Adapter {
    Context context;
    List<String> list;
    View view;

    public RecycleAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.custon_layoutmanager_item,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((MyViewHolder)viewHolder).item_text.setText(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_text = itemView.findViewById(R.id.item_text);
        }
    }
}
