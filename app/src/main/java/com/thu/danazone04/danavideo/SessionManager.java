package com.thu.danazone04.danavideo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by PC on 1/17/2018.
 */

public class SessionManager {
    private static final String SHARED_PREFERENCES_NAME = "com.thu.danazone04.danavideo";
    private static final String KEY_SAVE_NAME = "key_save_name";
    private static final String KEY_SAVE_PHONE = "key_save_phone";
    private static final String KEY_SAVE_PASS = "key_save_pass";
    private static final String KEY_SAVE_PRODUCT = "key_save_product";
    private static final String KEY_SAVE_ADDRESS = "key_save_address";
    private static final String KEY_SAVE_PRICE = "key_save_price";
    private static final String KEY_SAVE_NUMBER = "key_save_number";
    private static final String KEY_SAVE_YOU = "key_save_you";
    private static final String KEY_SAVE_WEB = "key_save_web";

    private static SessionManager sInstance;

    private SharedPreferences sharedPref;

    public synchronized static SessionManager getInstance() {
        if (sInstance == null) {
            sInstance = new SessionManager();
        }
        return sInstance;
    }

    private SessionManager() {
        // no instance
    }

    public void init(Context context) {
        sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }


    public void setKeyName(String token) {
        sharedPref.edit().putString(KEY_SAVE_NAME, token).apply();
    }

    public String getKeySaveName() {
        return sharedPref.getString(KEY_SAVE_NAME, "");
    }


    public String getKeySavePhone() {
        return sharedPref.getString(KEY_SAVE_PHONE, "");
    }

    public void setKeyPhone(String token) {
        sharedPref.edit().putString(KEY_SAVE_PHONE, token).apply();
    }


    public String getKeySavePass() {
        return sharedPref.getString(KEY_SAVE_PASS, "");
    }

    public void setKeySavePass(String token) {
        sharedPref.edit().putString(KEY_SAVE_PASS, token).apply();
    }

    public String getKeySaveProduct() {
        return sharedPref.getString(KEY_SAVE_PRODUCT, "");
    }

    public void setKeySaveProduct(String token) {
        sharedPref.edit().putString(KEY_SAVE_PRODUCT, token).apply();
    }

    public String getKeySaveAddress() {
        return sharedPref.getString(KEY_SAVE_ADDRESS, "");
    }

    public void setKeySaveAddress(String token) {
        sharedPref.edit().putString(KEY_SAVE_ADDRESS, token).apply();
    }

    public String getKeySavePrice() {
        return sharedPref.getString(KEY_SAVE_PRICE, "");
    }

    public void setKeySavePrice(String token) {
        sharedPref.edit().putString(KEY_SAVE_PRICE, token).apply();
    }

    public String getKeySaveNumber() {
        return sharedPref.getString(KEY_SAVE_NUMBER, "");
    }

    public void setKeySaveNumber(String token) {
        sharedPref.edit().putString(KEY_SAVE_NUMBER, token).apply();
    }

    public String getKeySaveYou() {
        return sharedPref.getString(KEY_SAVE_YOU, "");
    }

    public void setKeySaveYou(String token) {
        sharedPref.edit().putString(KEY_SAVE_YOU, token).apply();
    }

    public String getKeySaveWeb() {
        return sharedPref.getString(KEY_SAVE_WEB, "");
    }
    public void setKeySaveWeb(String token){
        sharedPref.edit().putString(KEY_SAVE_WEB, token).apply();
    }

    public void removeSaveProduct() {
        sharedPref.edit().remove(KEY_SAVE_PRODUCT).commit();
    }

    public void removeSaveAddress() {
        sharedPref.edit().remove(KEY_SAVE_ADDRESS).commit();
    }

    public void removeSavePrice() {
        sharedPref.edit().remove(KEY_SAVE_PRICE).commit();
    }

    public void removeSaveNumber() {
        sharedPref.edit().remove(KEY_SAVE_NUMBER).commit();
    }

    public void removeSaveYou() {
        sharedPref.edit().remove(KEY_SAVE_YOU).commit();
    }
    public void removeSaveWeb() {
        sharedPref.edit().remove(KEY_SAVE_WEB).commit();
    }
}
