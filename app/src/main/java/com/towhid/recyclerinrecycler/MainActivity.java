package com.towhid.recyclerinrecycler;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_parent;
    List<ParentModel> parentModelList;
    List<ChildModel> childModelList;
    List<BabyModel> babyModelList;
    ParentAdapter parentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_parent = findViewById(R.id.rv_parent);
        parentModelList = new ArrayList<>();
        childModelList = new ArrayList<>();
        babyModelList = new ArrayList<>();


        parentAdapter = new ParentAdapter(this, parentModelList);

        for (int i = 0; i < 5; i++){
            String  s="this is baby  " + i;
            babyModelList.add(new BabyModel(s));
        }

        for (int i = 0; i < 5; i++){
            String  s="this is child  " + i;
            childModelList.add(new ChildModel(s,babyModelList));
        }

        for (int i = 0; i < 40; i++)
        { String  s="this is Parrent " + i;
            parentModelList.add(new ParentModel(s, childModelList));
        }



        rv_parent.setLayoutManager(new LinearLayoutManager(this));
        rv_parent.setAdapter(parentAdapter);


    }


}
