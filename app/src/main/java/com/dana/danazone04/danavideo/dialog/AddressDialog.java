package com.dana.danazone04.danavideo.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dana.danazone04.danavideo.BaseDialog;
import com.dana.danazone04.danavideo.R;


public class AddressDialog extends BaseDialog implements View.OnClickListener {
    /**
     * Interface dialog click listener
     */
    public interface OnDialogClickListener {
        void onAdd1();
        void onAdd2();
        void onAdd3();
        void onAdd4();
        void onAdd5();

    }

    private OnDialogClickListener mListener;


    public AddressDialog(Context context, OnDialogClickListener listener) {
        super(context);
        mListener = listener;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        ImageView mImgCancelDialog = (ImageView) findViewById(R.id.mImgCancelDialog);
        TextView mTvAdd1 = (TextView)findViewById(R.id.mTvAdd1);
        TextView mTvAdd2 = (TextView)findViewById(R.id.mTvAdd2);
        TextView mTvAdd3 = (TextView)findViewById(R.id.mTvAdd3);
        TextView mTvAdd4 = (TextView)findViewById(R.id.mTvAdd4);
        TextView mTvAdd5 = (TextView)findViewById(R.id.mTvAdd5);


        mImgCancelDialog.setOnClickListener(this);
        mTvAdd1.setOnClickListener(this);
        mTvAdd2.setOnClickListener(this);
        mTvAdd3.setOnClickListener(this);
        mTvAdd4.setOnClickListener(this);
        mTvAdd5.setOnClickListener(this);

    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_date;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mImgCancelDialog:
                dismiss();
                break;

            case R.id.mTvAdd1:
                mListener.onAdd1();
                dismiss();
                break;

            case R.id.mTvAdd2:
                mListener.onAdd2();
                dismiss();
                break;

            case R.id.mTvAdd3:
                mListener.onAdd3();
                dismiss();
                break;
            case R.id.mTvAdd4:
                mListener.onAdd4();
                dismiss();
                break;
            case R.id.mTvAdd5:
                mListener.onAdd5();
                dismiss();
                break;
        }
    }
}


