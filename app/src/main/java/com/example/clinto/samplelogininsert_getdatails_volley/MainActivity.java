package com.example.clinto.samplelogininsert_getdatails_volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText t1,t2,t3,t4;
    Button bt;
public static final String TAG=MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(EditText)findViewById(R.id.ed_name);
        t2=(EditText)findViewById(R.id.ed_age);
        t3=(EditText)findViewById(R.id.ed_mob);
        t4=(EditText)findViewById(R.id.ed_place);
        bt=(Button)findViewById(R.id.btn_submit);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();

            }
        });

    }

    private void insert() {
        final String name=t1.getText().toString();
        final String age=t2.getText().toString();
        final String mobileno=t3.getText().toString();
        final String place=t4.getText().toString();
        String url="http://192.168.1.4/registration/reg.php";
        StringRequest sr= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "OnResponse:" + response);
                Toast.makeText(MainActivity.this, "Response :" + response, Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"onErrorResponse:" +error);

            }
        }){
            @Override
            protected Map<String,String>getParams(){
                Map<String, String> params=new HashMap<String, String>();
                params.put("name",name);
                params.put("age",age);
                params.put("mobileno",mobileno);
                params.put("place",place);
                return params;
            }
        };
        RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
        rq.add(sr);
    }
}
