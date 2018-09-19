package com.dana.danazone04.danavideo.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.util.Base64;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.dana.danazone04.danavideo.BaseActivity;
import com.dana.danazone04.danavideo.MainActivity_;
import com.dana.danazone04.danavideo.R;
import com.dana.danazone04.danavideo.SessionManager;
import com.dana.danazone04.danavideo.register.RegisterActivity_;
import com.dana.danazone04.danavideo.utils.ConnectionUtil;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressLint("Registered")
@EActivity(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {
    @ViewById
    ImageView mImgLogo;
    private Handler mHandler = new Handler();

    @Override
    protected void afterView() {
        getSupportActionBar().hide();
        if(!ConnectionUtil.isConnected(this)){
            showAlertDialog("Vui lòng kiểm tra kết nối internet");
            return;
        }

        Animation mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(400);
        mAnimation.setRepeatCount(android.view.animation.Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);
        mImgLogo.startAnimation(mAnimation);

        printKeyHash();

        if (!SessionManager.getInstance().getKeySavePhone().equals("") && !SessionManager.getInstance().getKeySavePass().equals("")) {
            MainActivity_.intent(SplashActivity.this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK
                    | Intent.FLAG_ACTIVITY_NEW_TASK).start();
            finish();
        } else {
            Runnable mActivityStarter = new Runnable() {
                @Override
                public void run() {
                    RegisterActivity_.intent(SplashActivity.this).start();
                    finish();
                }
            };
            mHandler.postDelayed(mActivityStarter, 2000);
        }
    }

    private void printKeyHash() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo("com.example.danazone04.employeesdana",
                    getPackageManager().GET_SIGNATURES);
            for(Signature signature : packageInfo.signatures){
                MessageDigest ms = null;
                try {
                    ms = MessageDigest.getInstance("SHA");
                    System.out.println("111111111111111: " + Base64.encodeToString(ms.digest(), Base64.DEFAULT));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                ms.update(signature.toByteArray());
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}

