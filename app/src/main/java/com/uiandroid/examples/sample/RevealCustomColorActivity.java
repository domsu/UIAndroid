package com.uiandroid.examples.sample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.uiandroid.examples.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RevealCustomColorActivity extends AppCompatActivity {

    @Bind(R.id.activity_reveal_custom_color_container)
    protected ViewGroup container;

    @Bind(R.id.activity_reveal_custom_color_value)
    protected TextView value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_custom_color);

        ButterKnife.bind(this);
    }

    @TargetApi(21)
    @SuppressLint("NewApi")
    @OnClick(R.id.activity_reveal_custom_color_clear)
    protected void onClearClick() {
        final ViewGroupOverlay overlay = container.getOverlay();

        final View revealView = new View(this);
        revealView.setBottom(container.getHeight());
        revealView.setRight(container.getWidth());
        revealView.setBackgroundColor(getColor(R.color.colorAccent));
        overlay.add(revealView);

        final Animator revealAnimator =
                ViewAnimationUtils.createCircularReveal(revealView,
                        revealView.getWidth(), revealView.getHeight(), 0.0f, container.getWidth() * 1.5f);
        revealAnimator.setDuration(
                getResources().getInteger(android.R.integer.config_longAnimTime));

        revealAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                value.setText("0");
            }
        });

        final Animator alphaAnimator = ObjectAnimator.ofFloat(revealView, View.ALPHA, 0.0f);
        alphaAnimator.setDuration(
                getResources().getInteger(android.R.integer.config_mediumAnimTime));

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(revealAnimator).before(alphaAnimator);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                value.setText("0");
                overlay.remove(revealView);
            }
        });

        animatorSet.start();

    }
}