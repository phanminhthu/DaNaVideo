package com.dana.danazone04.danavideo.video;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.dana.danazone04.danavideo.BaseActivity;
import com.dana.danazone04.danavideo.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@SuppressLint("Registered")
@EActivity(R.layout.activity_play_video)
public class PlayVideoActivity extends BaseActivity {
    @ViewById
    VideoView mVideoView;
    @Extra
    String mUrl;

    @Override
    protected void afterView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (mUrl != null) {
            mVideoView.setVisibility(View.VISIBLE);
            mVideoView.setVideoURI(Uri.parse(mUrl));
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(mVideoView);
            mVideoView.setMediaController(mediaController);
            mVideoView.start();
        }
    }
}
