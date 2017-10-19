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
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, "hai", Toast.LENGTH_SHORT).show();
                Item usr1 = new Item(true, "file:///android_asset/plant.png");
                Item usr2 = new Item(false, "file:///android_asset/apple.png");
                Item usr3 = new Item(false, "file:///android_asset/watermelon.png");
                Item usr4 = new Item(false, "file:///android_asset/grape.png");

                Items.clear();
                Items.add(usr1);
                Items.add(usr2);
                Items.add(usr3);
                Items.add(usr4);
                ItemAdapter.this.notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Items.size();
    }
}



