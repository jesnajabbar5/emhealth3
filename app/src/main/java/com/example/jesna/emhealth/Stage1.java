package com.example.jesna.emhealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Stage1 extends AppCompatActivity {
    String URL_DIAGNOSIS = "https://servetechnoresearch.com/Emotion/diagnosis.php";
    String dmail;
    Button go;
    int i=0;
    String t;
    int test=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1);
        go=findViewById(R.id.go);
        Intent rintent=getIntent();
        dmail=((Intent) rintent).getStringExtra("HUPEmail");

        //Toast.makeText(Stage1.this,dmail,Toast.LENGTH_SHORT).show();
       go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    private void calculate() {


        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL_DIAGNOSIS,new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
            //Toast.makeText(Stage1.this,response,Toast.LENGTH_SHORT).show();
               try {
                    JSONArray array = new JSONArray(response);

                  //  while(array.length()>0) {

                        JSONObject object = array.getJSONObject(0);
                        t=object.getString("RESPONSE");
                        Toast.makeText(Stage1.this,t,Toast.LENGTH_SHORT).show();
                 //   }


                }
                catch (Exception e) {

                    Toast.makeText(Stage1.this, "exception\n" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();


                }

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(Stage1.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();

                params.put("DMAIL",dmail);

                return params;


            }
        };



        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
