package com.example.a11699.all.qiyu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.a11699.all.R
import com.example.a11699.all.qiyu.dialogFragment.PayBottomDialogFragment
import kotlinx.android.synthetic.main.activity_pay_dialog_fragment.*

class PayDialogFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_dialog_fragment)
        btn.setOnClickListener {
            var s = PayBottomDialogFragment()
            s.show(supportFragmentManager,"pay")
        }
    }
}
