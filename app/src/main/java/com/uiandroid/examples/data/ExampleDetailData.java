package com.uiandroid.examples.data;

import android.os.Build;

import com.uiandroid.examples.bean.ExampleDetail;
import com.uiandroid.examples.sample.RevealCustomColorActivity;

public class ExampleDetailData {

    public final static ExampleDetail[] data = new ExampleDetail[]{
            new ExampleDetail("1. Reveal effect with custom color", "http://uiandroid.com/reveal-effect-custom-color/", Build.VERSION_CODES.LOLLIPOP, RevealCustomColorActivity.class)
    };

}
