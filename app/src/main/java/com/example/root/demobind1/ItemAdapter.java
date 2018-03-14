package com.example.root.demobind1;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import java.util.List;

import ai.elimu.analytics.eventtracker.EventTracker;

/**
 * Created by root on 9/10/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<Item> items;
    private Context c;
    private JsonHandler jsonHandler;
    private int count = 0;
    private Animation animation;
    private boolean anim = false;
    private ImageButton imageButtonB;
    private MediaPlayer rightVoice, wrongVoice;


    public ItemAdapter(List<Item> items, Context c) {
        this.items = items;
        this.c = c;
        jsonHandler = new JsonHandler(c);

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(c);
        View sceneItem = inflater.inflate(R.layout.scene_item, parent, false);

        rightVoice = MediaPlayer.create(c, R.raw.correct);
        wrongVoice = MediaPlayer.create(c, R.raw.wrong);
        return new ItemViewHolder(sceneItem);
    }


    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {


        holder.bind(items.get(position));
        holder.imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {

                if (((boolean) view.getTag()) && SceneTracker.getLevel() <= SceneTracker.getTotalLevel()) {


                    ((ImageButton) view).setBackgroundColor(Color.GREEN);

                    if (count < 1) {
                        rightVoice.start();
                        count++;
                        rightVoice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {


                                //wrongVoice.reset();

                                DataBindingListActivity.nextButton.setVisibility(View.VISIBLE);
                                DataBindingListActivity.nextButton.setBackgroundColor(Color.GREEN);
                                DataBindingListActivity.nextButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {



                                        anim = true;
                                        items.clear();
                                        SceneTracker.setLevel(SceneTracker.getLevel() + 1);
                                        items = jsonHandler.getSceneData(SceneTracker.getLevel() - 1);
                                        ItemAdapter.this.notifyDataSetChanged();
                                        DataBindingListActivity.nextButton.setVisibility(View.GONE);
                                        v.setBackgroundColor(Color.TRANSPARENT);
                                        ((ImageButton) view).setBackgroundColor(Color.TRANSPARENT);
                                        count = 0;



                                    }
                                });


                            }

                        });

                    }



                } else {

                    wrongVoice.start();
                    view.setBackgroundColor(Color.RED);
                    anim = false;

                }


            }
        });

        holder.imageButton.setBackgroundColor(Color.TRANSPARENT);


        if (!anim) {

            Animation animation = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
            holder.imageButton.startAnimation(animation);


        } else {
            Animation animation = AnimationUtils.loadAnimation(c, android.R.anim.fade_out);
            holder.imageButton.startAnimation(animation);

        }


    }

    public void prevScene() {
        items.clear();
        SceneTracker.setLevel(SceneTracker.getLevel() - 1);
        items = jsonHandler.getSceneData(SceneTracker.getLevel() - 1);
        ItemAdapter.this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}



