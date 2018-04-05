package com.example.root.demobind1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import java.util.Collections;
import java.util.List;



/**
 * Created by root on 9/10/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<Item> items;
    private Context c;
    private int count=0;
    private JsonHandler jsonHandler;
    private Animation animation;
    private boolean anim = false;
    private ImageButton imageButtonB;
    private MediaPlayer rightVoice, wrongVoice;





    public ItemAdapter(List<Item> items,Context c) {
        this.items = items;
        this.c = c;
        jsonHandler = new JsonHandler();
        Collections.shuffle(items);

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
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {


        holder.bind(items.get(position));
        holder.imageButton.setAlpha(1f);

        DataBindingListActivity.levelText.setText(String.valueOf(SceneTracker.getLevel()));
       holder.imageButton.setSoundEffectsEnabled(false);

        holder.imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {




                int color = Color.TRANSPARENT;
                Drawable background = view.getBackground();
                if (background instanceof ColorDrawable)
                    color = ((ColorDrawable) background).getColor();


                Log.d("getTag", String.valueOf(view.getTag()));
                Log.d("getTag",String.valueOf(String.valueOf(view.getTag())=="true"));

                Boolean a = Boolean.valueOf(String.valueOf(view.getTag()));

                if( a==true) {


                    if (SceneTracker.getLevel() == SceneTracker.getTotalLevel()) {

                        if (color != Color.GREEN) {


                            ((ImageButton) view).setBackgroundColor(Color.GREEN);
                            SceneTracker.setCorrectedItem((SceneTracker.getCorrectedItem() + 1));
                            DataBindingListActivity.right.setText(String.valueOf(SceneTracker.getCorrectedItem()));

                            rightVoice.start();
                            rightVoice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    Intent intent = new Intent(c.getApplicationContext(), LastActivity.class);
                                    c.startActivity(intent);
                                }
                            });
                        }

                    }
                    else {


                        if (color != Color.GREEN) {


                        ((ImageButton) view).setBackgroundColor(Color.GREEN);
                        // EventTracker.reportLetterLearningEvent(c, SceneTracker.getflag());
                        Log.d("TName", SceneTracker.getflag());
                        SceneTracker.setCorrectedItem((SceneTracker.getCorrectedItem() + 1));
                        DataBindingListActivity.right.setText(String.valueOf(SceneTracker.getCorrectedItem()));
                        //((ImageButton) view).setImageResource(R.drawable.tick1);


                        //if(count<1) {
                        rightVoice.start();
                        count++;
                        rightVoice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {


                                anim = true;
                                items.clear();
                                SceneTracker.setLevel(SceneTracker.getLevel() + 1);
                                items = jsonHandler.getSceneData(SceneTracker.getLevel() - 1);
                                if (items.size() != 0) {
                                    Collections.shuffle(items);
                                }
                                ItemAdapter.this.notifyDataSetChanged();
                                DataBindingListActivity.nextButton.setVisibility(View.GONE);
                                view.setBackgroundColor(Color.TRANSPARENT);
                                //  ((ImageButton) view).setBackgroundColor(Color.TRANSPARENT);
                                count = 0;
                                view.setAlpha(1);






                      /*       DataBindingListActivity.nextButton.setVisibility(View.VISIBLE);
                             DataBindingListActivity.nextButton.setBackgroundColor(Color.GREEN);
                             DataBindingListActivity.nextButton.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {

                                     anim = true;
                                     items.clear();
                                     SceneTracker.setLevel(SceneTracker.getLevel() + 1);
                                     items = jsonHandler.getSceneData(SceneTracker.getLevel() - 1);
                                     if (items.size() != 0) {
                                         Collections.shuffle(items);
                                     }
                                     ItemAdapter.this.notifyDataSetChanged();
                                     DataBindingListActivity.nextButton.setVisibility(View.GONE);
                                     v.setBackgroundColor(Color.TRANSPARENT);
                                     //  ((ImageButton) view).setBackgroundColor(Color.TRANSPARENT);
                                     count = 0;


                                 }
                             });*/


                            }
                        });

                        //    }
                    }
                }

                } else  {

                   /* int color = Color.TRANSPARENT;
                    Drawable background = view.getBackground();
                    if (background instanceof ColorDrawable)
                        color = ((ColorDrawable) background).getColor();*/

                    if(color!=Color.RED){

                       view.setAlpha(0.5f);
                        SceneTracker.setWrongItem((SceneTracker.getWrongItem()+1));
                        DataBindingListActivity.wrong.setText(String.valueOf(SceneTracker.getWrongItem()));
                        wrongVoice.start();
                    }

                    // ((ImageButton) view).setImageResource(R.drawable.cross1);
                    view.setBackgroundColor(Color.RED);


                    anim = false;

                    //rightVoice.stop();
                    //ItemAdapter.this.notifyDataSetChanged();

                }




               /* if(SceneTracker.getLevel()>1){
                    imageButtonB.setVisibility(View.VISIBLE);
                }*/

                //  ItemAdapter.this.notifyDataSetChanged();

            }

        });

        holder.imageButton.setBackgroundColor(Color.TRANSPARENT);



        if (anim==false) {

            Animation animation = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
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
        if(items.size()!=0){
            Collections.shuffle(items);
        }
        ItemAdapter.this.notifyDataSetChanged();
        count= 0;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}



