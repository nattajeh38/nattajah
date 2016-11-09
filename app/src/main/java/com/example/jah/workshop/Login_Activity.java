package com.example.jah.workshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login_Activity extends AppCompatActivity {

    private EditText etName;
    private EditText etPass;
    private Button btOk;
    private Button btRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        bindWidget();
        setListener();
        vslidate();


        etName = (EditText) findViewById(R.id.etName);
        etPass = (EditText) findViewById(R.id.etPass);
    }


    private boolean vslidate() {

        String name = etName.getText().toString();
        String pass = etPass.getText().toString();

        if (name.isEmpty())
            return false;
        if (pass.isEmpty())
            return false;

        return true;
    }

    private void bindWidget() {


    }

    private void setListener() {
        btOk = (Button) findViewById(R.id.btOk);
        btRegis = (Button) findViewById(R.id.btRegis);
        etName = (EditText) findViewById(R.id.etName);
        etPass = (EditText) findViewById(R.id.etPass);

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vslidate()) {
                    //Todo
                    new Login_Activity.Ok1(etName.getText().toString(),
                            etPass.getText().toString()).execute();
                } else {
                    Toast.makeText(Login_Activity.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login_Activity.this, Register_Activity.class);
                startActivity(i);
            }
        });
    }

    private class Ok1 extends AsyncTask<Void, Void, String> {

        private String name;
        private String pass;

        public Ok1(String name, String pass) {
            this.name = name;
            this.pass = pass;
        }

        protected void onPreExecute() { //
            super.onPreExecute();
        }

        protected String doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response;

            RequestBody requestBody = new FormBody.Builder()
                    .add("username", name)
                    .add("password", pass)
                    .build();

            request = new Request.Builder() //สร้างตัว request
                    .url("http://kimhun55.com/pollservices/login.php")
                    .post(requestBody)
                    .build();

            try { //ทำการร้องขอกับ server
                response = client.newCall(request).execute();

                if (response.isSuccessful()) { //เชคว่าสำเร็จ
                    return response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String s) { // ผลรวมของการทำงานทั้งหมด
            super.onPostExecute(s);
            Toast.makeText(Login_Activity.this,s,Toast.LENGTH_SHORT).show();

            try {
                JSONObject rootObj = new JSONObject(s);
                if(rootObj.has("result")){
                    JSONObject resultObj = rootObj.getJSONObject("result");
                    if(resultObj.getInt("result") == 1) {
                        Intent i = new Intent(Login_Activity.this, NewList_Activity.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(Login_Activity.this, resultObj.getString("result_desc"), Toast.LENGTH_SHORT).show();
                    }
                }

            } catch (JSONException ex) {

            }

        }
    }
}
