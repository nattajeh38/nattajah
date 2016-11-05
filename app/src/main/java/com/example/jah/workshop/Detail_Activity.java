package com.example.jah.workshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Detail_Activity extends AppCompatActivity {

    static String Topinews [] = {"Top News"};
    static String Date[] = {"4 พฤษจิกายน 2559"};
    int[] resId = {R.drawable.andorid};

    private  android.widget.ListView lvMenu1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);

        lvMenu1 = (android.widget.ListView) findViewById(R.id.lvMenu);
    }
}
