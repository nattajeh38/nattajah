package com.example.jah.workshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Simple_Activity extends AppCompatActivity {

    private ListView lvSimple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_);

        lvSimple = (ListView) findViewById(R.id.lvSimple);

        String[] valuse = new String[] {"Name"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                valuse


        );
        lvSimple.setAdapter(adapter);
    }
}
