package com.example.root.demobind1;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by root on 10/10/17.
 */

public class DataBindingListActivity extends AppCompatActivity  {
    private RecyclerView recycler;
    private ItemAdapter adapter;
    private JsonHandler jsonHandler;
    private ImageButton imageButtonBack;

    private MediaPlayer rightVoice, wrongVoice;


    private int coloumn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binding_list_activity);

        int level=1;
        coloumn = 2;



        imageButtonBack= (ImageButton) findViewById(R.id.imagebuttonback);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new StaggeredGridLayoutManager(coloumn, 1));



        jsonHandler = new JsonHandler(this);

        jsonHandler.readJson();

        SceneTracker.setLevel(level);
        adapter = new ItemAdapter(jsonHandler.getSceneData(level-1), this);
        recycler.setAdapter(adapter);

        rightVoice = MediaPlayer.create(this, R.raw.correct);
        wrongVoice = MediaPlayer.create(this, R.raw.wrong);




        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(SceneTracker.getLevel()>1 && SceneTracker.getLevel() <= SceneTracker.getTotalLevel()) {
                 adapter.prevScene();
             }

            }
        });



     /*   if(SceneTracker.getBack()==false) {
            imageButtonBack.setVisibility(View.GONE);

        }
        else {  imageButtonBack.setVisibility(View.VISIBLE);}*/
  Log.d("Tag",Integer.toString(SceneTracker.getTotalLevel()));

    }

}