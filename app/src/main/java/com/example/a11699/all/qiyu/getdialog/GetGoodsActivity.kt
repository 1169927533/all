package com.example.a11699.all.qiyu.getdialog


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.a11699.all.R
import kotlinx.android.synthetic.main.dialog_getgood.*



class GetGoodsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_getgood)
        btn_sure.setOnClickListener {
            var ds:GetGoodsDialogFragment = GetGoodsDialogFragment()
            ds.show(supportFragmentManager,"getgood")
        }
    }
}
