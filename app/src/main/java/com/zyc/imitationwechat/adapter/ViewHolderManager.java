package com.zyc.imitationwechat.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zyc.imitationwechat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolderManager {

    public static class MainViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text_volume)
        TextView textVolume;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
