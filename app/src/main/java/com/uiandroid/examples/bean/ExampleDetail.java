package com.uiandroid.examples.bean;

import android.app.Activity;

public class ExampleDetail {

    private final String title;
    private final Class<? extends Activity> activity;
    private final String url;

    public ExampleDetail(String title, String url, Class<? extends Activity> activity) {
        this.title = title;
        this.activity = activity;
        this.url = url;
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
}
