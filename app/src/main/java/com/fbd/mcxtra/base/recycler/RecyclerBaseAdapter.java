package com.fbd.mcxtra.base.recycler;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by Gourav on 08-02-2017.
 */

public abstract class RecyclerBaseAdapter extends RecyclerView.Adapter<RecyclerBaseViewHolder> {


    public RecyclerBaseViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        return new RecyclerBaseViewHolder(binding);
    }

    public void onBindViewHolder(RecyclerBaseViewHolder holder,
                                 int position) {
        Object obj = getObjForPosition(position);
        holder.bind(obj);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    protected abstract Object getObjForPosition(int position);

    protected abstract int getLayoutIdForPosition(int position);
}
