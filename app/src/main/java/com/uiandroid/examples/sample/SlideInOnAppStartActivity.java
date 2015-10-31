package com.uiandroid.examples.sample;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.uiandroid.examples.R;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class SlideInOnAppStartActivity extends AppCompatActivity {

    private static final int START_DELAY = 300;
    private static final int DURATION_INITIAL = 400;
    private static final int DURATION_NEXT_VIEW_FACTOR = 30;
    private static final float INTERPOLATOR_FACTOR = 2f;

    private boolean animated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animated_slide_in);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus && !animated)
            startSlideInAnimation();
    }

    private void startSlideInAnimation() {
        ViewGroup windowRoot = (ViewGroup) findViewById(android.R.id.content);
        ViewGroup contentRoot = (ViewGroup) windowRoot.getChildAt(0);

        for (int i = 0; i < contentRoot.getChildCount(); i++) {
            View v = contentRoot.getChildAt(i);

            v.setTranslationY(windowRoot.getHeight());
            v.setAlpha(0);

            v.animate()
                    .translationY(0)
                    .alpha(1)
                    .setStartDelay(START_DELAY)
                    .setDuration(DURATION_INITIAL + DURATION_NEXT_VIEW_FACTOR * i)
                    .setInterpolator(new DecelerateInterpolator(INTERPOLATOR_FACTOR)).start();
        }

        animated = true;
    }

}
