package com.example.asus.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StepsActivity extends AppCompatActivity {
    ExpandableListView exV;
    List<String>langs;

    ExpandableListAdapter exAd;

    Map<String, List<String>> topics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        exV=(ExpandableListView) findViewById(R.id.ex1);
        fillData();

        MyExListAdapter exAd=new MyExListAdapter(this, langs, topics);
        exV.setAdapter(exAd);

    }
    public void fillData()
    {
        langs=new ArrayList<>();
        topics=new HashMap<>();

        langs.add("Step1");
        langs.add("Step2");

        List<String> Step1= new ArrayList<>();
        List<String> Step2= new ArrayList<>();

        Step1.add("Description of Step 1");
        Step2.add("Description of Step 2");

        topics.put(langs.get(0),Step1);
        topics.put(langs.get(1),Step2);

    }
}
