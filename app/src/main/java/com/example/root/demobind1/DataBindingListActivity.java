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

        coloumn = 3;

        list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new StaggeredGridLayoutManager(coloumn, 1));
        //list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        User user1 = new User("ChintanRathod", 28, "https://i.stack.imgur.com/9udcy.jpg?s=328&g=1");
        User user2 = new User("MaulikRathod", 25, "https://avatars1.githubusercontent.com/u/17959401?v=3&s=400");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        adapter = new UserAdapter(userList, this);
        list.setAdapter(adapter);

    }


}