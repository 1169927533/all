package com.example.a11699.all.contextstudy

import android.content.Context
import android.content.ContextWrapper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.a11699.all.R
import kotlinx.android.synthetic.main.activity_context.*
import java.lang.reflect.Field

class ContextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context)
        btn_resetsharedlocation.setOnClickListener {

        }
        print(this)
        print(baseContext)
        print(applicationContext)
    }

    fun print(context: Context) {
        Log.i("zjc", context.toString())
    }


    fun changeSpLocation(){
        var field:Field
        //获取ContextWrapper对象中的mBase变量 该变量保存有ContextImpl对象
        field = ContextWrapper::class.java.getDeclaredField("mBase")
        field.isAccessible = true
        //获取mBase变量
        var obj = field.get(this)
    }
}
