package com.uiandroid.examples.data;

import android.os.Build;

import com.uiandroid.examples.bean.ExampleDetail;
import com.uiandroid.examples.sample.SlideInOnAppStartActivity;
import com.uiandroid.examples.sample.RevealCustomColorActivity;
import com.uiandroid.examples.sample.sharedImageWithRevealEffect.FirstActivity;

public class ExampleDetailData {

    public final static ExampleDetail[] data = new ExampleDetail[]{
            new ExampleDetail("3. Shared image with reveal effect", "http://uiandroid.com/shared-image-with-reveal-effect/", Build.VERSION_CODES.ICE_CREAM_SANDWICH, FirstActivity.class),
            new ExampleDetail("2. Slide in animation on app start", "http://uiandroid.com/slide-in-animation-on-app-start/", Build.VERSION_CODES.ICE_CREAM_SANDWICH, SlideInOnAppStartActivity.class),
            new ExampleDetail("1. Reveal effect with custom color", "http://uiandroid.com/reveal-effect-custom-color/", Build.VERSION_CODES.LOLLIPOP, RevealCustomColorActivity.class)
    };

}
