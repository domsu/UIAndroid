package com.uiandroid.examples.sample.viewlayers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.CheckBox;

import com.uiandroid.examples.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewLayersActivity extends AppCompatActivity {

    private final static int CARD_VERTICAL_MARGIN_DP = 10;

    @Bind(R.id.activity_view_layers_root)
    protected ViewGroup activityRoot;

    @Bind(R.id.activity_view_layers_container_one)
    protected ViewGroup containerOne;

    @Bind(R.id.activity_view_layers_container_two)
    protected ViewGroup containerTwo;

    @Bind(R.id.activity_view_layers_animate_view_layer)
    protected CheckBox checkBoxViewLayer;

    @Bind(R.id.activity_view_layers_animate_view_layer_incorrect)
    protected CheckBox checkBoxViewLayerIncorrect;

    private boolean animateDirection;
    private boolean animationInProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_layers);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_view_layers_animate)
    protected void onAnimateClick() {
        if (checkBoxViewLayer.isChecked()) {
            containerTwo.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            containerOne.setLayerType(View.LAYER_TYPE_HARDWARE, null);

            containerTwo.buildLayer();
            containerOne.buildLayer();

            animateViews(() -> {
                containerOne.setLayerType(View.LAYER_TYPE_NONE, null);
                containerTwo.setLayerType(View.LAYER_TYPE_NONE, null);
            });

        } else if (checkBoxViewLayerIncorrect.isChecked()) {
            activityRoot.setLayerType(View.LAYER_TYPE_HARDWARE, null);

            activityRoot.buildLayer();

            animateViews(() -> activityRoot.setLayerType(View.LAYER_TYPE_NONE, null));

        } else {
            animateViews(null);
        }
    }

    @OnClick(R.id.activity_view_layers_animate_view_layer)
    protected void onAnimateWithViewLayerClick() {
        checkBoxViewLayerIncorrect.setChecked(false);
    }

    @OnClick(R.id.activity_view_layers_animate_view_layer_incorrect)
    protected void onAnimateWithIncorrectViewLayerClick() {
        checkBoxViewLayer.setChecked(false);
    }

    private void animateViews(Runnable cleanUpAction) {
        if (!animationInProgress) {
            containerTwo
                    .animate()
                    .scaleX(0.5f)
                    .scaleY(0.5f)
                    .setDuration(133)
                    .withLayer()
                    .withEndAction(() ->
                            containerTwo
                                    .animate()
                                    .y(getAnimationTargetY(!animateDirection, containerOne))
                                    .setDuration(233)
                                    .setInterpolator(new DecelerateInterpolator())
                                    .withEndAction(() ->
                                            containerTwo
                                                    .animate()
                                                    .scaleX(1)
                                                    .scaleY(1)
                                                    .setDuration(134)
                                                    .start())
                                    .start())
                    .start();

            containerOne
                    .animate()
                    .y(getAnimationTargetY(animateDirection, containerTwo))
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);

                            animationInProgress = true;
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);

                            animationInProgress = false;
                            animateDirection = !animateDirection;

                            if (cleanUpAction != null)
                                cleanUpAction.run();
                        }
                    })
                    .start();
        }
    }

    private int getAnimationTargetY(boolean animateDirection, View v) {
        float margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, CARD_VERTICAL_MARGIN_DP, getResources().getDisplayMetrics());

        return (int) (animateDirection ? 0 + margin : v.getHeight() + 2 * margin);
    }

}
