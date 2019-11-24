package com.yingke.transition.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.transition.Slide;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yingke.transition.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yingke.transition.activity.CustomActivity.PARAMS_INDEX;
import static com.yingke.transition.activity.SceneActivity.PARAMS_EXPLODE;
import static com.yingke.transition.activity.SceneActivity.PARAMS_FADE;
import static com.yingke.transition.activity.SceneActivity.PARAMS_SLIDE;

public class ActivityContentActivity extends AppCompatActivity {

    RadioGroup radioGroup1;
    List<Integer[]> mEnterExitResIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Slide slide = new Slide();
        slide.setDuration(200);
        getWindow().setEnterTransition(slide);

        radioGroup1 = findViewById(R.id.rg_left_bottom);
        mEnterExitResIds = new ArrayList<>();
        Integer[] anim1 = {R.anim.anim_left_in, R.anim.anim_right_out};
        mEnterExitResIds.add(anim1);

        Integer[] anim2 = {R.anim.anim_bottom_in, R.anim.anim_top_out};
        mEnterExitResIds.add(anim2);

        Integer[] anim3 = {R.anim.anim_fade_in, R.anim.anim_fade_out};
        mEnterExitResIds.add(anim3);

        Integer[] anim4 = {R.anim.anim_scale_in, R.anim.anim_scale_out};
        mEnterExitResIds.add(anim4);

    }

    public void onCustom(View view) {
        ActivityOptionsCompat compat = null;
        Intent intent = new Intent(this, CustomActivity.class);
        int checkedId1 = radioGroup1.getCheckedRadioButtonId();
        switch (checkedId1) {
            case R.id.rb_left_in_right_out:
                compat = ActivityOptionsCompat.makeCustomAnimation(this, R.anim.anim_left_in, R.anim.anim_right_out);
                intent.putExtra(PARAMS_INDEX, 0);
                break;
            case R.id.rb_bottom_in_top_out:
                compat = ActivityOptionsCompat.makeCustomAnimation(this, R.anim.anim_bottom_in, R.anim.anim_top_out);
                intent.putExtra(PARAMS_INDEX, 1);
                break;
            case R.id.rb_fade_in_fade_out:
                compat = ActivityOptionsCompat.makeCustomAnimation(this, R.anim.anim_fade_in, R.anim.anim_fade_out);
                intent.putExtra(PARAMS_INDEX, 2);
                break;
            case R.id.rb_scale_in_scale_out:
                compat = ActivityOptionsCompat.makeCustomAnimation(this, R.anim.anim_scale_in, R.anim.anim_scale_out);
                intent.putExtra(PARAMS_INDEX, 3);
                break;
        }
        startActivity(intent, compat.toBundle());
    }

    public void onScaleUp(View view) {
        View  source = findViewById(R.id.image);
        int startX = source.getWidth() / 2;
        int startY = source.getWidth() / 2;

        ActivityOptions compat = ActivityOptions.makeScaleUpAnimation(source, startX, startY, 0, 0);
        startActivity(new Intent(this, ScaleUpActivity.class), compat.toBundle());
    }

    public void onThumbnailScaleUp(View view) {
        View  source = findViewById(R.id.image);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        int startX = source.getWidth() / 2;
        int startY = source.getWidth() / 2;

        ActivityOptions compat = ActivityOptions.makeThumbnailScaleUpAnimation(source, bitmap, startX, startY);
        startActivity(new Intent(this, ThumbnailScaleUpActivity.class), compat.toBundle());
    }

    public void onScenesTransition(View view) {
        CheckBox slide = findViewById(R.id.slide);
        CheckBox explode = findViewById(R.id.explode);
        CheckBox fade = findViewById(R.id.fade);

        Intent intent = new Intent(this, SceneActivity.class);

        Map<String, Boolean> transitionMap = new HashMap<>();
        transitionMap.put(PARAMS_SLIDE ,slide.isChecked() );
        transitionMap.put(PARAMS_EXPLODE, explode.isChecked() );
        transitionMap.put(PARAMS_FADE, fade.isChecked());
        intent = intent.putExtra(SceneActivity.PARAMS_INDEX, (Serializable) transitionMap);

        ActivityOptions compat = ActivityOptions.makeSceneTransitionAnimation(this);
        startActivity(intent, compat.toBundle());
    }

}
