package com.yingke.transition.activity;

import android.os.Bundle;

import com.yingke.transition.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ScaleUpActivity extends AppCompatActivity {
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
