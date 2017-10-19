package com.example.root.demobind1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by root on 9/10/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private final List<Item> Items;
    private Context c;


    public ItemAdapter(List<Item> Items,Context c) {
        this.Items = Items;
        this.c = c;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(c);
        View sceneItem = inflater.inflate(R.layout.scene_item, parent, false);
        return new ItemViewHolder(sceneItem);
    }


    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        holder.bind(Items.get(position));
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getTag();
                Toast.makeText(c, view.getTag().toString(), Toast.LENGTH_SHORT).show();
               
                ItemAdapter.this.notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Items.size();
    }
}



