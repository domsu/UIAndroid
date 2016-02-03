package com.uiandroid.examples.sample.assetreduction;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uiandroid.examples.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssetReductionActivity extends AppCompatActivity {

    private int drawableRes[] = new int[]{  R.drawable.face_aquamarine, R.drawable.face_bisque, R.drawable.face_blue_violet, R.drawable.face_burly_wood, R.drawable.face_cadet_blue,
                                            R.drawable.face_chartreuse, R.drawable.face_chocolate, R.drawable.face_coral, R.drawable.face_cornflower_blue, R.drawable.face_crimson,
                                            R.drawable.face_cyan, R.drawable.face_dark_blue, R.drawable.face_dark_cyan, R.drawable.face_dark_golden_rod, R.drawable.face_dark_gray,
                                            R.drawable.face_dark_green, R.drawable.face_dark_khaki, R.drawable.face_dark_magenta, R.drawable.face_dark_olive_green, R.drawable.face_dark_orange};

    private String drawableColor[] = new String[]{  "#7FFFD4", "#FFE4C4", "#8A2BE2", "#DEB887", "#5F9EA0",
                                                    "#7FFF00", "#D2691E", "#FF7F50", "#6495ED", "#DC143C",
                                                    "#00FFFF", "#00008B", "#008B8B", "#B8860B", "#A9A9A9",
                                                    "#006400", "#BDB76B", "#8B008B", "#556B2F", "#FF8C00" };

    @Bind(R.id.activity_asset_reduction_images_container)
    protected ViewGroup imagesContainer;

    @Bind(R.id.activity_asset_reduction_result)
    protected TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_asset_reduction);
        ButterKnife.bind(this);

        buildImageViews();
    }

    @OnClick(R.id.activity_asset_reduction_disable_reduction)
    protected void refreshImagesReductionDisabled() {
        refreshImagesAndCheckPerformance(false);
    }

    @OnClick(R.id.activity_asset_reduction_enable_reduction)
    protected void refreshImagesReductionEnabled() {
        refreshImagesAndCheckPerformance(true);
    }

    private void refreshImagesAndCheckPerformance(boolean useReduction) {
        long startTime = System.nanoTime();

        refreshImages(useReduction);

        long stopTime = System.nanoTime();
        resultTextView.setText(String.format("Images displayed in %.4f ms",  ((1.0d * stopTime - startTime) / 1000000000d)));
    }

    private void refreshImages(boolean useReduction) {
        for (int i = 0; i < imagesContainer.getChildCount(); i++) {
            ImageView iv = (ImageView) imagesContainer.getChildAt(i);
            if (useReduction)
                loadImageWithReduction(iv, i);
            else
                loadImage(iv, i);
        }
    }

    private void loadImageWithReduction(ImageView view, int imageIndex) {
        Drawable drawable = changeColorOfDrawable(R.drawable.face_black, Color.parseColor(drawableColor[imageIndex]));
        view.setImageDrawable(drawable);
    }

    private void loadImage(ImageView view, int imageIndex) {
        view.setImageResource(drawableRes[imageIndex]);
    }

    private Drawable changeColorOfDrawable(@DrawableRes int drawableResId, int color) {
        Drawable drawable = ContextCompat.getDrawable(this, drawableResId);

        drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);

        return drawable;
    }

    private void buildImageViews() {
        for(int i = 0; i< drawableRes.length; i++)
            imagesContainer.addView(new ImageView(this));
    }
}
