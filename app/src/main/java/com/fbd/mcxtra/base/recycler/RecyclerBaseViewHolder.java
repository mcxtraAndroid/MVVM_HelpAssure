package com.fbd.mcxtra.base.recycler;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.fbd.mcxtra.BR;


/**
 * Created by Gourav on 08-02-2017.
 */

public class RecyclerBaseViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    public RecyclerBaseViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Object obj) {
        binding.setVariable(BR.vm, obj);
        binding.executePendingBindings();
    }
}
