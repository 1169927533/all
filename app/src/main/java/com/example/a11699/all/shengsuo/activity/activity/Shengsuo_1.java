package com.example.a11699.all.shengsuo.activity.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.a11699.all.R;
import com.example.a11699.all.shengsuo.activity.object.Shop_class;
import com.example.a11699.all.shengsuo.activity.adapter.*;
import java.util.ArrayList;
import java.util.List;

public class Shengsuo_1 extends AppCompatActivity {

    RecyclerView myList;

    List<String> list_iamge;//轮播的图片
    List<Shop_class> list_shop;//推荐的商品列表
    MyAdapter myAdapter;//推荐商品列表的适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shengsuo_1);
        myList = findViewById(R.id.my_list);
        initData();
        initview_child();
    }
    private void initData() {
        //设置图片资源:url或本地资源
        list_iamge = new ArrayList<>();
        list_iamge.add("http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg");
        list_iamge.add("http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg");
        list_iamge.add("http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg");
        list_iamge.add("http://img.zcool.cn/community/01fda356640b706ac725b2c8b99b08.jpg");
        list_iamge.add("http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg");
        list_iamge.add("http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg");

        //推荐商品列表数据
        list_shop = new ArrayList<>();
        Shop_class shop_class = new Shop_class("https://img.alicdn.com/imgextra/i1/1970250768/TB2KihQmTvI8KJjSspjXXcgjXXa_!!1970250768.jpg", "素洁", "2019秋季");
        list_shop.add(shop_class);
        shop_class = new Shop_class("https://img.alicdn.com/imgextra/i2/1970250768/TB2BCpumL6H8KJjy0FjXXaXepXa_!!1970250768.jpg", "海尔", "2019春季");
        list_shop.add(shop_class);
        shop_class = new Shop_class("https://img.alicdn.com/imgextra/i1/4025964321/O1CN01rHppbf1hn3tkIqZnQ_!!4025964321.jpg", "特步", "2019夏季");
        list_shop.add(shop_class);
        shop_class = new Shop_class("https://img.alicdn.com/imgextra/i1/1970250768/TB2KihQmTvI8KJjSspjXXcgjXXa_!!1970250768.jpg", "素洁", "2019秋季");
        list_shop.add(shop_class);
    }
    private void initview_child() {
            myAdapter = new MyAdapter(this, list_shop);
            myList.setAdapter(myAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            myList.setLayoutManager(linearLayoutManager);

    }
}
