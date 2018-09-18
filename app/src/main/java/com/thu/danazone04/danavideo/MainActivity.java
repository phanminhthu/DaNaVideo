package com.thu.danazone04.danavideo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.thu.danazone04.danavideo.Camera.CameraActivity;
import com.thu.danazone04.danavideo.Camera.CameraActivity_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
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

    private String names, phones, addresss, prices, products, webs;


    @Override
    protected void afterView() {
        checkPermissions();
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

    @Click(R.id.mTvSubmit)
    void onClick(View v) {
        getData();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            showAlertDialog("Cho phép các quyền truy cập để tiếp tục sử dụng dịch vụ!");
            return;
        }

        CameraActivity_.intent(MainActivity.this)
                .mName(names)
                .mPhone(phones)
                .mAddress(addresss)
                .mProduct(products)
                .mPrice(prices)
                .mWeb(webs)
                .start();
    }

    private void getData() {
        String name = mEdtName.getText().toString();
        String phone = mEdtPhone.getText().toString();
        String address = mTvAddress.getText().toString();
        String product = mEdtProduct.getText().toString();
        String price = mEdtMoney.getText().toString();
        String webbb = mEdtMoney.getText().toString();
        if (mCbName.isChecked()) {
            if (name.equals("")) {
                showAlertDialog("Vui lòng nhập tên nhân viên");
                return;
            } else {
                name = mEdtName.getText().toString();
                names = name;
                SessionManager.getInstance().setKeySaveYou(name);
            }
        } else {
            name = "";
            names = name;
        }

        if (mCbPhone.isChecked()) {
            if (phone.equals("")) {
                showAlertDialog("Vui lòng nhập số điện thoại");
                return;
            } else {
                phone = mEdtPhone.getText().toString();
                phones = phone;
                SessionManager.getInstance().setKeySaveNumber(phone);
            }
        } else {
            phone = "";
            phones = phone;
        }

        if (mCbAddress.isChecked()) {
            if (address.equals("Địa chỉ")) {
                showAlertDialog("Vui lòng nhập địa chỉ");
                return;
            } else {
                address = mTvAddress.getText().toString();
                addresss = address;
                SessionManager.getInstance().setKeySaveAddress(address);
            }
        } else {
            address = "";
            addresss = address;
        }

        if (mCbProduct.isChecked()) {
            if (product.equals("")) {
                showAlertDialog("Vui lòng nhập tên sản phẩm");
                return;
            } else {
                product = mEdtProduct.getText().toString();
                products = product;
                SessionManager.getInstance().setKeySaveProduct(product);
            }
        } else {
            product = "";
            products = product;
        }

        if (mCbMoney.isChecked()) {
            if (price.equals("")) {
                showAlertDialog("Vui lòng nhập giá tiền");
                return;
            } else {
                price = mEdtMoney.getText().toString();
                prices = price;
                SessionManager.getInstance().setKeySavePrice(price);
            }
        } else {
            price = "";
            prices = price;
        }

        if (mCbWeb.isChecked()) {
            if (webbb.equals("")) {
                showAlertDialog("Vui lòng nhập địa chỉ website");
                return;
            } else {
                webbb = mEdtWeb.getText().toString().trim();
                webs = webbb;
                SessionManager.getInstance().setKeySaveWeb(webbb);
            }
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
