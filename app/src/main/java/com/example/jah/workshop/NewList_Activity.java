package com.example.jah.workshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class NewList_Activity extends AppCompatActivity {

    private ListView lvMenu;

    static String[]NameTopic = {"Topic New",
            "Topic New1",
            "Topic New2",
            "Topic New3",
            "Topic New4",
            "Topic New5",};
    static String[] DateName = {
            "31 ตุลาคม 2559",
            "31 ตุลาคม 2559",
            "31 ตุลาคม 2559",
            "31 ตุลาคม 2559",
            "31 ตุลาคม 2559",
            "31 ตุลาคม 2559"};
    int[] resId={R.drawable.andorid,
            R.drawable.andorid,
            R.drawable.andorid,
            R.drawable.andorid,
            R.drawable.andorid,
            R.drawable.andorid,
            R.drawable.andorid,};





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list_);

        lvMenu = (ListView) findViewById(R.id.lvMenu);
        lvMenu.setAdapter(new CustomAdapter(getApplicationContext(),NameTopic,DateName,resId));

    }

}
