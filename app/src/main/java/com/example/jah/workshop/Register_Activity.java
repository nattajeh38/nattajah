package com.example.jah.workshop;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Register_Activity extends AppCompatActivity {


    private EditText etDaisplay;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etPassCon;
    private Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);



        bindWidget();
        setListener();
        vslidate();

    }

    private boolean vslidate() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String passwordConfirm = etPassCon.getText().toString();
        String displayName = etDaisplay.getText().toString();

        if (username.isEmpty())
            return false;
        if (password.isEmpty())
            return false;
        if (passwordConfirm.isEmpty())
            return false;
        if (!password.equals(passwordConfirm))
            return false;
        if (displayName.isEmpty())
            return  false;

        return true;

    }

    private void bindWidget() {

        etDaisplay = (EditText) findViewById(R.id.etDisplay);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPassCon = (EditText) findViewById(R.id.etPassCon);
    }

    private void setListener(){
        Button btRegister = (Button) findViewById(R.id.btRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vslidate()) {
                    //ToDo
                    new Register1(etUsername.getText().toString(),
                            etPassword.getText().toString(),
                            etPassCon.getText().toString(),
                            etDaisplay.getText().toString()).execute();
                }else{
                    Toast.makeText(Register_Activity.this,"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private class Register1 extends AsyncTask<Void, Void, String>{

        private String username;
        private String password;
        private String passwordCon;
        private String displayName;

        public Register1(String username, String password, String passwordCon, String displayName) {
            this.username = username;
            this.password = password;
            this.passwordCon = passwordCon;
            this.displayName = displayName;
        }

        @Override
        protected void onPreExecute() { //
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) { //เขียนการเชื่อต่อกับ server

            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response;
            //ตัวส่งข้อมูล
            RequestBody requestBody = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .add("password_con" , passwordCon)
                    .add("display_name", displayName)
                    .build();
            request = new Request.Builder() //สร้างตัว request
                    .url("http://kimhun55.com/pollservices/signup.php")
                    .post(requestBody)
                    .build();
            try { //ทำการร้องขอกับ server
                response = client.newCall(request).execute();

                if(response.isSuccessful()){ //เชคว่าสำเร็จ
                    return response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) { // ผลรวมของการทำงานทั้งหมด
            super.onPostExecute(s);
            Toast.makeText(Register_Activity.this, s , Toast.LENGTH_SHORT).show();

            try {
                JSONObject rootObj = new JSONObject(s);
                if(rootObj.has("result")){
                    JSONObject resultObj = rootObj.getJSONObject("result");
                    if(resultObj.getInt("result") == 1) {
                        Toast.makeText(Register_Activity.this, resultObj.getString("result_desc"), Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(Register_Activity.this, resultObj.getString("result_desc"), Toast.LENGTH_SHORT).show();
                    }
                }

            } catch (JSONException ex) {

            }

        }
    }
}
