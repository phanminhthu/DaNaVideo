package com.dana.danazone04.danavideo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dana.danazone04.danavideo.Camera.CameraActivity;
import com.dana.danazone04.danavideo.Camera.CameraActivity_;
import com.dana.danazone04.danavideo.video.PlayVideoActivity_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    public static final int PLAY_VIDEO = 100;

    @ViewById
    TextView mTvSubmit;
    @ViewById
    EditText mTvAddress;
    @ViewById
    EditText mEdtPhone;
    @ViewById
    EditText mEdtName;
    @ViewById
    EditText mEdtProduct;
    @ViewById
    CheckBox mCbProduct;
    @ViewById
    CheckBox mCbName;
    @ViewById
    CheckBox mCbPhone;
    @ViewById
    CheckBox mCbAddress;
    @ViewById
    EditText mEdtMoney;
    @ViewById
    CheckBox mCbMoney;
    @ViewById
    EditText mEdtWeb;
    @ViewById
    CheckBox mCbWeb;
    @ViewById
    TextView mTvPlay;
    @ViewById
    LinearLayout mLnSum;

    private String names, phones, addresss, prices, products, webs;
    private String url;


    @Override
    protected void afterView() {
        checkPermissions();
        mLnSum.setWeightSum(6);
        checkinCheckBox();
        setData();
    }

    private void checkinCheckBox() {
        if (SessionManager.getInstance().getKeySaveProduct().equals("")) {
            mCbProduct.setChecked(false);
        }
        if (SessionManager.getInstance().getKeySaveAddress().equals("")) {
            mCbAddress.setChecked(false);
        }

        if (SessionManager.getInstance().getKeySaveNumber().equals("")) {
            mCbPhone.setChecked(false);
        }
        if (SessionManager.getInstance().getKeySaveYou().equals("")) {
            mCbName.setChecked(false);
        }

        if (SessionManager.getInstance().getKeySavePrice().equals("")) {
            mCbMoney.setChecked(false);
        }
        if (SessionManager.getInstance().getKeySaveWeb().equals("")) {
            mCbWeb.setChecked(false);
        }
    }

    private void setData() {
        if (!SessionManager.getInstance().getKeySaveProduct().equals("")) {
            mEdtProduct.setText(SessionManager.getInstance().getKeySaveProduct());
        }

        if (!SessionManager.getInstance().getKeySaveYou().equals("")) {
            mEdtName.setText(SessionManager.getInstance().getKeySaveYou());
        }

        if (!SessionManager.getInstance().getKeySaveNumber().equals("")) {
            mEdtPhone.setText(SessionManager.getInstance().getKeySaveNumber());
        }

        if (!SessionManager.getInstance().getKeySaveAddress().equals("")) {
            mTvAddress.setText(SessionManager.getInstance().getKeySaveAddress());
        }

        if (!SessionManager.getInstance().getKeySavePrice().equals("")) {
            mEdtMoney.setText(SessionManager.getInstance().getKeySavePrice());
        }

        if (!SessionManager.getInstance().getKeySaveWeb().equals("")) {
            mEdtWeb.setText(SessionManager.getInstance().getKeySaveWeb());
        }
    }

    @Click({R.id.mTvSubmit, R.id.mTvPlay})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTvPlay:
                if (url != null) {
                    PlayVideoActivity_.intent(MainActivity.this).mUrl(url).start();
                }
                break;

            case R.id.mTvSubmit:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED) {
                    showAlertDialog("Cho phép các quyền truy cập để tiếp tục sử dụng dịch vụ!");
                    return;
                }
                getData();
                if (mCbProduct.isChecked() && mEdtProduct.getText().toString().equals("")) {
                    showAlertDialog("Vui lòng nhập tên sản phẩm");
                    return;
                }

                if (mCbName.isChecked() && mEdtName.getText().toString().equals("")) {
                    showAlertDialog("Vui lòng nhập tên nhân viên");
                    return;
                }
                if (mCbPhone.isChecked() && mEdtPhone.getText().toString().equals("")) {
                    showAlertDialog("Vui lòng nhập số điện thoại");
                    return;
                }

                if (mCbAddress.isChecked() && mTvAddress.getText().toString().equals("")) {
                    showAlertDialog("Vui lòng nhập địa chỉ");
                    return;
                }

                if (mCbMoney.isChecked() && mEdtMoney.getText().toString().equals("")) {
                    showAlertDialog("Vui lòng nhập giá tiền");
                    return;
                }
                if (mCbWeb.isChecked() && mEdtWeb.getText().toString().equals("")) {
                    showAlertDialog("Vui lòng nhập địa chỉ website");
                    return;
                }

                CameraActivity_.intent(MainActivity.this)
                        .mName(names)
                        .mPhone(phones)
                        .mAddress(addresss)
                        .mProduct(products)
                        .mPrice(prices)
                        .mWeb(webs)
                        .startForResult(PLAY_VIDEO);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLAY_VIDEO) {
            mLnSum.setWeightSum(2);
            mTvPlay.setVisibility(View.VISIBLE);
            url = data.getStringExtra("video");
        }
    }

    private void getData() {
        String name = mEdtName.getText().toString();
        String phone = mEdtPhone.getText().toString();
        String address = mTvAddress.getText().toString();
        String product = mEdtProduct.getText().toString();
        String price = mEdtMoney.getText().toString();
        String webbb = mEdtWeb.getText().toString();
        if (mCbName.isChecked()) {
            name = mEdtName.getText().toString();
            names = name;
            SessionManager.getInstance().setKeySaveYou(name);
        } else {
            name = "";
            names = name;
        }

        if (mCbPhone.isChecked()) {
            phone = mEdtPhone.getText().toString();
            phones = phone;
            SessionManager.getInstance().setKeySaveNumber(phone);
        } else {
            phone = "";
            phones = phone;
        }

        if (mCbAddress.isChecked()) {

            address = mTvAddress.getText().toString();
            addresss = address;
            SessionManager.getInstance().setKeySaveAddress(address);

        } else {
            address = "";
            addresss = address;
        }

        if (mCbProduct.isChecked()) {

            product = mEdtProduct.getText().toString();
            products = product;
            SessionManager.getInstance().setKeySaveProduct(product);

        } else {
            product = "";
            products = product;
        }

        if (mCbMoney.isChecked()) {

            price = mEdtMoney.getText().toString();
            prices = price;
            SessionManager.getInstance().setKeySavePrice(price);

        } else {
            price = "";
            prices = price;
        }

        if (mCbWeb.isChecked()) {

            webbb = mEdtWeb.getText().toString().trim();
            webs = webbb;
            SessionManager.getInstance().setKeySaveWeb(webbb);

        } else {
            webbb = "";
            webs = webbb;
        }
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED
                ) {
            // getData();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 101);
            }
        }
    }
}
