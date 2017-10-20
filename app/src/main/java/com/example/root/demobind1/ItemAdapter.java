package com.example.root.demobind1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.List;

/**
 * Created by root on 9/10/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<Item> items;
    private Context c;
    private JsonHandler jsonHandler;
    private Animation animation;
    private boolean animatio = true;



    public ItemAdapter(List<Item> items,Context c) {
        this.items = items;
        this.c = c;
        jsonHandler = new JsonHandler(c);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(c);
        View sceneItem = inflater.inflate(R.layout.scene_item, parent, false);
        return new ItemViewHolder(sceneItem);
    }


    @Override
    public void onBindViewHolder( ItemViewHolder holder, int position) {



      /*  Animation animation = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
        holder.imageButton.startAnimation(animation);*/



        holder.bind(items.get(position));

        holder.imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(c, view.getTag().toString(), Toast.LENGTH_SHORT).show();
                if(((boolean)view.getTag())==true )
                {
                    animatio = true;

                    items.clear();
                    SceneTracker.setLevel(SceneTracker.getLevel()+1);
                   items=jsonHandler.getSceneData(SceneTracker.getLevel()-1);
                }
                ItemAdapter.this.notifyDataSetChanged();
            }
        });

        if (animatio) {

            animation = AnimationUtils.loadAnimation(c, android.R.anim.slide_in_left);
            holder.imageButton.startAnimation(animation);
        } else {

            animation = AnimationUtils.loadAnimation(c, android.R.anim.decelerate_interpolator);
            holder.imageButton.startAnimation(animation);
        }





    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}



