package com.hhh.aidl_server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;

/**
 * 新版ocToken2.0获取privateKeyKeyStore和助记词wordskeystore的服务
 */
public class AIDLService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("AIDLService", "onCreate()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    Binder binder = new WalletService.Stub(){

        @Override
        public void sendWalletBean(List<WalletBean> walletBeans) throws RemoteException {
            //此处为新版ocToken2.0接收旧版本数据
            String s = "接收到的数据\nprivateKeyKyeStore：" +
                    walletBeans.get(0).getPrivateKeyKyeStore()+
                    "\n助记词wordskeystore：" +
                    walletBeans.get(0).getWordskeystore();
            Log.e("AIDLService", s);
            Intent intent = new Intent(AIDLService.this,MainActivity.class);
            intent.putExtra("server_data",s);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("AIDLService","onDestroy()");
    }
}
