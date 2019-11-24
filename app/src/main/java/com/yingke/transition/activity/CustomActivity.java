package com.yingke.transition.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yingke.transition.R;

import java.util.ArrayList;
import java.util.List;

public class CustomActivity extends AppCompatActivity {

    public static final String PARAMS_INDEX = "ANIM_INDEX";
    private List<Integer[]> mEnterExitResIds;
    private int mAnimIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        mEnterExitResIds = new ArrayList<>();

        Integer[] anim1 = {R.anim.anim_right_in, R.anim.anim_left_out};
        mEnterExitResIds.add(anim1);

        Integer[] anim2 = {R.anim.anim_top_in, R.anim.anim_bottom_out};
        mEnterExitResIds.add(anim2);

        Integer[] anim3 = {R.anim.anim_fade_in, R.anim.anim_fade_out};
        mEnterExitResIds.add(anim3);

        Integer[] anim4 = {R.anim.anim_scale_in, R.anim.anim_scale_out};
        mEnterExitResIds.add(anim4);

        mAnimIndex = getIntent().getIntExtra(PARAMS_INDEX, 0);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Integer[] anim = mEnterExitResIds.get(mAnimIndex);
        overridePendingTransition(anim[0], anim[1]);
    }
}
