package com.yingke.transition.activity;

import android.os.Bundle;

import com.yingke.transition.R;

import androidx.appcompat.app.AppCompatActivity;

public class ThumbnailScaleUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
