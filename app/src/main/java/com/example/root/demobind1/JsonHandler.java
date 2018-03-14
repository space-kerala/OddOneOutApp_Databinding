package com.example.root.demobind1;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ai.elimu.analytics.eventtracker.EventTracker;


/**
 * Created by root on 17/10/17.
 */

public class JsonHandler extends DataBindingListActivity {
    private static JSONArray scenes;
    private Context ctx;

    public JsonHandler(Context ctx) {
        this.ctx = ctx;
    }


    public void readJson() {
        try {
            //Load File
            BufferedReader jsonReader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.scene_data)));
            StringBuilder builder = new StringBuilder();
            for (String line = null; (line = jsonReader.readLine()) != null; ) {
                builder.append(line).append("\n");
            }


            JSONObject root = new JSONObject(builder.toString());
            Log.d("TTag", root.toString());

            scenes = root.getJSONArray("scenes");


        } catch (FileNotFoundException e) {
            Log.e("jsonFile", "file not found");
        } catch (IOException e) {
            Log.e("jsonFile", "ioerror");
        } catch (JSONException e) {
            Log.e("jsonFile", "error while parsing json");
        }


    }

    public ArrayList<Item> getSceneData(int index) {
        JSONObject sceneItem;
        ArrayList<Item> itemList = new ArrayList<>();
        try {
            JSONArray scene = scenes.getJSONArray(index);
            SceneTracker.setTotalLevel(scene.length() + 1);

            for (int i = 0; i < scene.length(); i++) {

                sceneItem = scene.getJSONObject(i);
                Item item = new Item(sceneItem.getBoolean("answer"), sceneItem.getString("src"));


                String idStr = sceneItem.getString("src").substring(sceneItem.getString("src").lastIndexOf('/') + 1);
                String str = idStr.replaceAll("\\..*", "");
                //Log.d("TName",str);

                if (sceneItem.getBoolean("answer")) {
                    EventTracker.reportLetterLearningEvent(ctx, str);
                    Log.d("TName", str);
                }

                itemList.add(item);


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return itemList;
    }


}


