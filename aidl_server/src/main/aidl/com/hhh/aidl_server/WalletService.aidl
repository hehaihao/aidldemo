// WalletService.aidl
package com.hhh.aidl_server;
import com.hhh.aidl_server.WalletBean;
// Declare any non-default types here with import statements
interface WalletService {

    void sendWalletBean(in List<WalletBean> walletBean);

}
