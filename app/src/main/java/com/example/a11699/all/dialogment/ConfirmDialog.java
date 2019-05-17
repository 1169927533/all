package com.example.a11699.all.dialogment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.a11699.all.R;

/**
 * 作者：余智强
 * 2019/2/26
 */
public class ConfirmDialog extends BaseDialog {
    private String type;

    public static ConfirmDialog newInstance(String type) {
        Log.i("zjc","newinstance被调用了");
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public int setUpLayoutId() {
        Log.i("zjc","setUpLayoutId被调用了");
        return R.layout.confirm_dialog;
    }

    @Override
    public void convertView(ViewHolder holder, final BaseDialog dialog) {
        Log.i("zjc","convertView被调用了");
        if ("1".equals(type)) {
            holder.setText(R.id.title, "提示");
            holder.setText(R.id.message, "您已支付成功!");
        } else if ("2".equals(type)) {
            holder.setText(R.id.title, "警告");
            holder.setText(R.id.message, "您的帐号已被冻结!");
        }

        holder.setOnClickListener(R.id.confim_cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        holder.setOnClickListener(R.id.confirm, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(getContext(), "确定", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
