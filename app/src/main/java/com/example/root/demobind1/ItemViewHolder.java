package com.example.root.demobind1;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.root.demobind1.databinding.SceneItemBinding;


/**
 * Created by root on 10/10/17.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder{
    private SceneItemBinding binding;
    public ImageButton imageButton;


    public ItemViewHolder(View layoutView) {
        super(layoutView);
        binding = DataBindingUtil.bind(layoutView);
        imageButton= (ImageButton)layoutView.findViewById(R.id.imagebutton);


    }

    public void bind(Item item) {
        binding.setItem(item);
        
    }
}