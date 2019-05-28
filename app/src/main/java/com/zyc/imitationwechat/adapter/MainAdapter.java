package com.zyc.imitationwechat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyc.imitationwechat.R;

public class MainAdapter extends RecyclerView.Adapter<ViewHolderManager.MainViewHolder> {

    private LinearLayoutManager layoutManager;
    private Context mContext;
    public MainAdapter (Context context) {
        mContext = context;
        layoutManager = new LinearLayoutManager(context);
    }

    public LinearLayoutManager getLayoutManager () {
        return layoutManager;
    }

    @NonNull
    @Override
    public ViewHolderManager.MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);
        return new ViewHolderManager.MainViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderManager.MainViewHolder mainViewHolder, int i) {
        mainViewHolder.textVolume.setText("" + i);
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
