package com.xiaoma.studyretrofit;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mDatas;
    private static final String TAG = "DemoAdapter";

    public DemoAdapter(Context context, List<String> datas) {
        this.mContext = context;
        mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_name, viewGroup, false);
        Log.e(TAG, "onCreateViewHolder: "+view.toString());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mName.setText(mDatas.get(i));
        Log.e(TAG, "onBindViewHolder: "+i);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tv_name);
        }
    }
}
