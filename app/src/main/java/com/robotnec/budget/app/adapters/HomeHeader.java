package com.robotnec.budget.app.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robotnec.budget.R;

/**
 * @author zak <zak@swingpulse.com>
 */
public class HomeHeader implements ListHeader {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.view_home_header, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder) {

    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
