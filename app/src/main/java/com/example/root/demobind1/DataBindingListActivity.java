package com.example.root.demobind1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by root on 10/10/17.
 */

public class DataBindingListActivity extends AppCompatActivity  {
    private RecyclerView recycler;
    private ItemAdapter adapter;
    private JsonHandler jsonHandler;


    private int coloumn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binding_list_activity);

        int level=1;
        coloumn = 2;

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new StaggeredGridLayoutManager(coloumn, 1));
        //list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        /*Item Item1 = new Item(false, "file:///android_asset/lion.png");
        Item Item2 = new Item(false, "file:///android_asset/bear.png");
        Item Item3 = new Item(false, "file:///android_asset/jaguar.png");
        Item Item4 = new Item(true, "file:///android_asset/tux.png");


        ArrayList<Item> ItemList = new ArrayList<>();
        ItemList.add(Item1);
        ItemList.add(Item2);
        ItemList.add(Item3);
        ItemList.add(Item4);*/

        jsonHandler = new JsonHandler(this);

        jsonHandler.readJson();

        SceneTracker.setLevel(level);
        adapter = new ItemAdapter(jsonHandler.getSceneData(level-1), this);
        recycler.setAdapter(adapter);






    }

}