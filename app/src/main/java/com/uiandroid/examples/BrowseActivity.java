package com.uiandroid.examples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.uiandroid.examples.adapter.BrowseActivityAdapter;
import com.uiandroid.examples.adapter.DividerItemDecoration;
import com.uiandroid.examples.data.ExampleDetailData;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BrowseActivity extends AppCompatActivity {

    @Bind(R.id.browse_activity_recycler_view)
    protected RecyclerView recyclerView;

    @Bind(R.id.browse_activity_toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        ButterKnife.bind(this);

        setupView();
    }

    private void setupView() {
        setSupportActionBar(toolbar);

        BrowseActivityAdapter adapter = new BrowseActivityAdapter(ExampleDetailData.data, this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

}
