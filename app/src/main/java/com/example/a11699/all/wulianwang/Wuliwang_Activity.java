package com.example.a11699.all.wulianwang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.a11699.all.R;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class Wuliwang_Activity extends AppCompatActivity {

    String ip = "192.168.43.71";
    int port = 80;
    Socket socket = null;
    OutputStream os = null;   //输出流
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                socket = new Socket(ip,port);
               // shurunei("on");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuliwang_);
        new Thread(runnable).start();

    }

    public void kaideng(View view) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                shurunei("on");
            }
        });
        thread.start();
    }

    public void guandeng(View view) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                shurunei("off");
            }
        });
        thread.start();

    }

    void shurunei(String content){
        byte[] bstream = new byte[0];  //转化为字节流
        try {
            bstream = content.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            os = socket.getOutputStream();
            os.write(bstream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
