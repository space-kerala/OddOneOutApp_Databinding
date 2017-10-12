package com.example.root.demobind1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

/**
 * Created by root on 10/10/17.
 */

public class DataBindingListActivity extends AppCompatActivity  {
    private RecyclerView list;
    private UserAdapter adapter;
    private int coloumn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binding_list_activity);

        coloumn = 2;

        list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new StaggeredGridLayoutManager(coloumn, 1));
        //list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        User user1 = new User("ChintanRathod", 28, "file:///android_asset/lion.png");
        User user2 = new User("MaulikRathod", 25, "file:///android_asset/bear.png");
        User user3 = new User("MaulikRathod", 25, "file:///android_asset/jaguar.png");
        User user4 = new User("MaulikRathod", 25, "file:///android_asset/tux.png");


        ArrayList<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        adapter = new UserAdapter(userList, this);
        list.setAdapter(adapter);

    }

}