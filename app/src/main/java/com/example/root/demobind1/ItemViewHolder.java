package com.example.root.demobind1;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.root.demobind1.databinding.SceneItemBinding;


/**
 * Created by root on 10/10/17.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder{
    private SceneItemBinding binding;
    public Button button;

    public ItemViewHolder(View layoutView) {
        super(layoutView);
        binding = DataBindingUtil.bind(layoutView);
        button = (Button)layoutView.findViewById(R.id.button_id);

    }

    public void bind(Item item) {
        binding.setItem(item);
        
    }
}