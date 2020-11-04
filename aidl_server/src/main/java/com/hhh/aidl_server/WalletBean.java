package com.hhh.aidl_server;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用于提供数据bean，可拓展
 */
public class WalletBean implements Parcelable {
    public WalletBean(String privateKeyKyeStore, String wordskeystore) {
        this.privateKeyKyeStore = privateKeyKyeStore;
        this.wordskeystore = wordskeystore;
    }

    private String privateKeyKyeStore;
    private String wordskeystore;

    protected WalletBean(Parcel in) {
        privateKeyKyeStore = in.readString();
        wordskeystore = in.readString();
    }

    public static final Creator<WalletBean> CREATOR = new Creator<WalletBean>() {
        @Override
        public WalletBean createFromParcel(Parcel in) {
            return new WalletBean(in);
        }

        @Override
        public WalletBean[] newArray(int size) {
            return new WalletBean[size];
        }
    };

    public String getPrivateKeyKyeStore() {
        return privateKeyKyeStore;
    }

    public void setPrivateKeyKyeStore(String privateKeyKyeStore) {
        this.privateKeyKyeStore = privateKeyKyeStore;
    }

    public String getWordskeystore() {
        return wordskeystore;
    }

    public void setWordskeystore(String wordskeystore) {
        this.wordskeystore = wordskeystore;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(privateKeyKyeStore);
        parcel.writeString(wordskeystore);
    }

    public void readFromParcel(Parcel reply) {
        privateKeyKyeStore=reply.readString();
        wordskeystore=reply.readString();

    }
}
