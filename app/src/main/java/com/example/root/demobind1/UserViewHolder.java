package com.example.root.demobind1;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.root.demobind1.databinding.UserItemBinding;


/**
 * Created by root on 10/10/17.
 */

public class UserViewHolder extends RecyclerView.ViewHolder{
    private UserItemBinding binding;
    public Button button;

    public UserViewHolder(View layoutView) {
        super(layoutView);
        binding = DataBindingUtil.bind(layoutView);
        button = (Button)layoutView.findViewById(R.id.button_id);

    }

    public void bind(User user) {
        binding.setUser(user);
        
    }
}