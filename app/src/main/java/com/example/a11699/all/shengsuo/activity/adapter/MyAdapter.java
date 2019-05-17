package com.example.a11699.all.shengsuo.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a11699.all.R;
import com.example.a11699.all.shengsuo.activity.object.Shop_class;

import java.util.List;

/**
 * 作者：余智强
 * 2019/4/17
 */
 public class MyAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Shop_class> list_shop;

    public MyAdapter(Context context, List<Shop_class> list_shop) {
        this.context = context;
        this.list_shop = list_shop;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend_shoping,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Glide.with(context).load(list_shop.get(i).getShop_image_url()).into(((MyViewHolder)viewHolder).item_image);
        ((MyViewHolder)viewHolder).item_brand.setText(list_shop.get(i).getShop_brand());
        ((MyViewHolder)viewHolder).item_season.setText(list_shop.get(i).getShop_season());
    }

    @Override
    public int getItemCount() {
        return list_shop.size();
    }
    class MyViewHolder extends  RecyclerView.ViewHolder{
        ImageView item_image;//商品图片
        TextView item_brand,item_season;//商品牌子和商品季节
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.recommend_shop_item_img);
            item_brand = itemView.findViewById(R.id.recommend_shop_item_brand);
            item_season = itemView.findViewById(R.id.recommend_shop_item_season);
        }
    }
}
