package com.uiandroid.examples.sample.sharedImageWithRevealEffect;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.uiandroid.examples.R;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    public static final int ITEM_COUNT = 40;

    private FirstActivity activity;

    public RecyclerAdapter(FirstActivity activity) {
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = activity.getLayoutInflater().inflate(R.layout.row_shared_image_reveal_first, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }

}

class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
