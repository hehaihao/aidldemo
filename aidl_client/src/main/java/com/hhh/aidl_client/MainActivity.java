package com.hhh.aidl_client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hhh.aidl_server.WalletBean;
import com.hhh.aidl_server.WalletService;

import java.util.ArrayList;
import java.util.List;



/**
 * 旧版ocToken客户端发送老版本数据
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private EditText et_param1;

    private EditText et_param2;
    private WalletService wallet;


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this,"连接成功，可以发送数据",Toast.LENGTH_SHORT).show();
            wallet = WalletService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            wallet = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bindService();
    }

    private void bindService() {
        boolean is = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
        Log.e(TAG,is+"结果");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.hhh.aidl_server", "com.hhh.aidl_server.AIDLService"));
        startService(intent);
        boolean b = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        if(!b)
            Toast.makeText(MainActivity.this,"连接失败，请打开接收方",Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        et_param1 = findViewById(R.id.et_param1);
        et_param2 = findViewById(R.id.et_param2);
        Button btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                try {
                    String s1 = et_param1.getText().toString();
                    String s2 = et_param2.getText().toString();
                    if(TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2)){
                        Toast.makeText(this,"请输入发送的数据",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(wallet != null) {
                        List<WalletBean> list = new ArrayList<>();
                        list.add(new WalletBean(s1,s2));
                        wallet.sendWalletBean(list);
                    }else{
                        bindService();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
