package com.yingke.transition.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.yingke.transition.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

public class SceneActivity extends AppCompatActivity {

    public static final String PARAMS_INDEX = "ANIM_INDEX";
    public static final String PARAMS_SLIDE = "PARAMS_SLIDE";
    public static final String PARAMS_EXPLODE = "PARAMS_EXPLODE";
    public static final String PARAMS_FADE = "PARAMS_FADE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        Map<String, Boolean> transitionMap = (Map<String, Boolean>) getIntent().getSerializableExtra(PARAMS_INDEX);

        final View mTopBackground = findViewById(R.id.top_background);
        final View mBottomBackground = findViewById(R.id.bottom_background);
        mBottomBackground.setBackgroundResource(R.color.theme_yellow_background);

        final TransitionSet transitionSet = new TransitionSet();
        for (String key : transitionMap.keySet()) {
            if (transitionMap.get(key)) {
                if (TextUtils.equals(key, PARAMS_SLIDE) ) {
                    Slide slide = new Slide(Gravity.BOTTOM);
                    slide.addTarget(R.id.image_shared);
                    transitionSet.addTransition(slide);
                }

                if (TextUtils.equals(key, PARAMS_EXPLODE)) {
                    Explode explode = new Explode();
                    explode.excludeTarget(android.R.id.statusBarBackground, true);
                    explode.excludeTarget(android.R.id.navigationBarBackground, true);
                    explode.excludeTarget(R.id.image_shared, true);
                    transitionSet.setOrdering(TransitionSet.ORDERING_SEQUENTIAL);
                    transitionSet.addTransition(explode);
                }

                if (TextUtils.equals(key, PARAMS_FADE)) {
                    Fade fade = new Fade(Fade.MODE_IN);
                    transitionSet.addTransition(fade);
                }
            }
        }

        transitionSet.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                mTopBackground.setVisibility(View.GONE);
                mBottomBackground.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                mTopBackground.setVisibility(View.VISIBLE);
                mBottomBackground.setVisibility(View.VISIBLE);

                Animator animationTop = ViewAnimationUtils.createCircularReveal(mTopBackground,
                        mTopBackground.getWidth() / 2,
                        mTopBackground.getHeight() / 2,
                        0,
                        Math.max(mTopBackground.getWidth() / 2, mTopBackground.getHeight() / 2));

                Animator animationBottom = ViewAnimationUtils.createCircularReveal(mBottomBackground,
                        mBottomBackground.getWidth(),
                        mBottomBackground.getHeight(),
                        0,
                        (float) Math.hypot(mBottomBackground.getWidth(), mBottomBackground.getHeight()));

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(500L);
                animatorSet.playTogether(animationTop, animationBottom);
                animatorSet.start();
                transitionSet.removeListener(this);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });


        getWindow().setEnterTransition(transitionSet);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }
}
