package com.uiandroid.examples.bean;

import android.app.Activity;

public class ExampleDetail {

    private final String title;
    private final String url;
    private final int minSdk;
    private final Class<? extends Activity> activity;

    public ExampleDetail(String title, String url, int minSdk, Class<? extends Activity> activity) {
        this.title = title;
        this.url = url;
        this.minSdk = minSdk;
        this.activity = activity;
    }

    public String getTitle() {
        return title;
    }

    public Class<? extends Activity> getActivity() {
        return activity;
    }

    public String getUrl() {
        return url;
    }

    public int getMinSdk() {
        return minSdk;
    }
}
