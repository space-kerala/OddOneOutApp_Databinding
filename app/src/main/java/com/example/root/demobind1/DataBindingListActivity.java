package com.example.root.demobind1;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import ai.elimu.analytics.eventtracker.EventTracker;
import ai.elimu.model.enums.content.Shape;

/**
 * Created by root on 10/10/17.
 */

public class DataBindingListActivity extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    public static Button nextButton;
    private static int permission, permission1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    // private RecyclerView recycler;
    private RecyclerViewEmptySupport recyclerViewEmptySupport;
    private ItemAdapter adapter;
    private JsonHandler jsonHandler;
    private ImageButton imageButtonBack;
    private MediaPlayer rightVoice, wrongVoice;
    private int coloumn;

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permission1 = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binding_list_activity);
        //  EventTracker.reportLetterLearningEvent(getApplicationContext(), “a”);
        verifyStoragePermissions(this);

        int level = 1;
        coloumn = 2;
        imageButtonBack = (ImageButton) findViewById(R.id.imagebuttonback);
        nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setVisibility(View.GONE);
        recyclerViewEmptySupport = (RecyclerViewEmptySupport) findViewById(R.id.recycler);
        recyclerViewEmptySupport.setLayoutManager(new StaggeredGridLayoutManager(coloumn, 1));
        recyclerViewEmptySupport.setEmptyView(findViewById(R.id.list_empty));
        recyclerViewEmptySupport.setButtonView(findViewById(R.id.reload));

        if ((permission == PackageManager.PERMISSION_GRANTED) && (permission1 == PackageManager.PERMISSION_GRANTED)) {


            jsonHandler = new JsonHandler(this);
            jsonHandler.readJson();

            SceneTracker.setLevel(level);
            adapter = new ItemAdapter(jsonHandler.getSceneData(level - 1), this);
            recyclerViewEmptySupport.setAdapter(adapter);

            rightVoice = MediaPlayer.create(this, R.raw.correct);
            wrongVoice = MediaPlayer.create(this, R.raw.wrong);


            imageButtonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (SceneTracker.getLevel() > 1 && SceneTracker.getLevel() <= SceneTracker.getTotalLevel()) {
                        adapter.prevScene();
                    }

                }
            });


        } else {
            verifyStoragePermissions(this);

        }

    }

    public void restartActivity(View view) {
        Intent intent = new Intent(this, DataBindingListActivity.class);
        startActivity(intent);
    }
}
