package com.example.a11699.all.layoutanimation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.example.a11699.all.R
import kotlinx.android.synthetic.main.activity_layout_animation_study.*

class LayoutAnimationStudy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_animation_study)
        recycleview.layoutManager = GridLayoutManager(this, 4)

        var list = ArrayList<Int>()
        list.add(1)
        list.add(1)
        list.add(1)
        list.add(1)
        list.add(1)
        list.add(1)
        list.add(1)
        list.add(1)

        recycleview.adapter = MyAdapter(list)

        //通过加载XML动画设置文件来创建一个Animation对象；
        var animation = AnimationUtils.loadAnimation(this, R.anim.slide_right);   //得到一个LayoutAnimationController对象；
        var controller = LayoutAnimationController(animation);   //设置控件显示的顺序；
        controller.order = LayoutAnimationController.ORDER_NORMAL
        controller.delay = (0.3f)
        recycleview.layoutAnimation = controller;
        recycleview.startLayoutAnimation();
    }

    private class MyAdapter(private val list: List<Int>) : Adapter<MyAdapter.MyViewHolder>() {
        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyAdapter.MyViewHolder {
            var view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item, viewGroup, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(myViewHolder: MyAdapter.MyViewHolder, i: Int) {

        }

        override fun getItemCount(): Int {
            return list.size
        }

        internal inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        }

    }
}
