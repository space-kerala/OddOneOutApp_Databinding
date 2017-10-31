package com.example.root.demobind1;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import java.util.List;

/**
 * Created by root on 9/10/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<Item> items;
    private Context c;
    private JsonHandler jsonHandler;
    private Animation animation;
    private boolean anim = false;
    private ImageButton imageButtonB;
    private MediaPlayer rightVoice, wrongVoice;





    public ItemAdapter(List<Item> items,Context c) {
        this.items = items;
        this.c = c;
        jsonHandler = new JsonHandler(c);

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //imageButtonB = (ImageButton)parent.findViewById(R.id.imagebuttonback);
        LayoutInflater inflater = LayoutInflater.from(c);
        View sceneItem = inflater.inflate(R.layout.scene_item, parent, false);

        //Log.d("Tagid",imageButtonB.toString());
        rightVoice = MediaPlayer.create(c, R.raw.correct);
        wrongVoice = MediaPlayer.create(c, R.raw.wrong);

        return new ItemViewHolder(sceneItem);
    }


    @Override
    public void onBindViewHolder( ItemViewHolder holder, int position) {


        holder.bind(items.get(position));
        holder.imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                //Toast.makeText(c, view.getTag().toString(), Toast.LENGTH_SHORT).show();
                if(((boolean)view.getTag()==true) && SceneTracker.getLevel()<= SceneTracker.getTotalLevel() )
                {


                    rightVoice.start();
                    rightVoice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {

                            anim=true;
                            items.clear();
                            SceneTracker.setLevel(SceneTracker.getLevel()+1);
                            items=jsonHandler.getSceneData(SceneTracker.getLevel()-1);
                            ItemAdapter.this.notifyDataSetChanged();
                        }
                    });



                }
                else {
                    anim=false;
                    wrongVoice.start();
                }

               /* if(SceneTracker.getLevel()>1){
                    imageButtonB.setVisibility(View.VISIBLE);
                }*/

                ItemAdapter.this.notifyDataSetChanged();

            }
        });



        if (anim==false) {

            Animation animation = AnimationUtils.loadAnimation(c, android.R.anim.slide_in_left);
            holder.imageButton.startAnimation(animation);


        }
        else {
            Animation animation = AnimationUtils.loadAnimation(c, android.R.anim.fade_out);
            holder.imageButton.startAnimation(animation);

        }


    }

    public void prevScene()
    {
        items.clear();
        SceneTracker.setLevel(SceneTracker.getLevel()-1);
        items=jsonHandler.getSceneData(SceneTracker.getLevel()-1);
        ItemAdapter.this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}



