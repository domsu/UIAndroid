package com.uiandroid.examples.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uiandroid.examples.BrowseActivity;
import com.uiandroid.examples.R;
import com.uiandroid.examples.bean.ExampleDetail;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BrowseActivityAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ExampleDetail[] examples;
    private BrowseActivity activity;
    private LayoutInflater layoutInflater;

    public BrowseActivityAdapter(ExampleDetail[] examples, BrowseActivity context) {
        this.examples = examples;
        this.activity = context;

        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.row_browse_activity_adapter, parent, false);

        return new ViewHolder(v, activity);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(examples[position]);
    }

    @Override
    public int getItemCount() {
        return examples.length;
    }

}

class ViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.row_browse_activity_adapter_title)
    protected TextView title;

    private BrowseActivity activity;
    private ExampleDetail exampleDetail;

    public ViewHolder(View itemView, BrowseActivity activity) {
        super(itemView);
        this.activity = activity;

        ButterKnife.bind(this, itemView);
    }

    public void onBind(ExampleDetail exampleDetail) {
        this.exampleDetail = exampleDetail;

        title.setText(exampleDetail.getTitle());
    }

    @OnClick(R.id.row_browse_activity_adapter_root)
    protected void onRootViewClick() {
        if (isDeviceOk()) {
            activity.startActivity(new Intent(activity, exampleDetail.getActivity()));
        } else {
            Snackbar.make(
                    activity.findViewById(android.R.id.content),
                    String.format(activity.getString(R.string.wrong_device_version), exampleDetail.getMinSdk()),
                    Snackbar.LENGTH_LONG
            ).show();
        }
    }

    @OnClick(R.id.row_browse_activity_adapter_icon)
    protected void onIconClick() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(exampleDetail.getUrl()));

        activity.startActivity(intent);
    }

    private boolean isDeviceOk() {
        return android.os.Build.VERSION.SDK_INT >= exampleDetail.getMinSdk();
    }
}
