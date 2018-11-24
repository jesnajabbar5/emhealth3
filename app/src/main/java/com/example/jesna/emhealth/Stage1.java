package com.example.jesna.emhealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
    String URL_DIAGNOSIS = "https://emhealth.000webhostapp.com/diagnosis.php";
    String dmail;
    Button go;
    int i=0;
    String t;
    TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1);
        go=findViewById(R.id.go);
        res=findViewById(R.id.textView4);
        Intent rintent=getIntent();
        dmail=((Intent) rintent).getStringExtra("HUPEmail");


        //Toast.makeText(Stage1.this,dmail,Toast.LENGTH_SHORT).show();
       go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate(0);
            }
        });
    }
    private void calculate (final int i) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DIAGNOSIS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONArray array = new JSONArray(response);
                    // JSONObject object = array.getJSONObject(0);
                    JSONObject object = array.getJSONObject(i);
                    //  qid=count+1;
                    String test = object.getString("rsum");


                 //  Toast.makeText(Stage1.this,test, Toast.LENGTH_SHORT).show();
                   if(Integer.parseInt(test)<53)
                    {
                        res.setText("Don't worry,You have NO DEPPRESSION !!! May be you are stressed out of your work load..We recommend you to have a talk with someone you like...");
                        go.setText("THANK YOU");
                    }


                    if(Integer.parseInt(test)>=53&&Integer.parseInt(test)<=62)
                    {
                        res.setText("We have found that you are in a stage of mild depression... !!! Don't worry, Just go to music player and play your favourite song.All you need is s music therapy now.");
                        go.setText("THANK YOU");
                    }
                    if(Integer.parseInt(test)>72)
                    {
                        res.setText("Sorry!! You are in a stage of SEVERE DEPRESSION... !!! Don't worry,we are here to help you.Take a therapy.Answer some more questions.");
                        go.setText("OK");
                        go.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Stage1.this, LifeStatus.class);
                                startActivity(intent);
                            }
                        });

                    }



                } catch (Exception e) {

                    Toast.makeText(Stage1.this, "exception\n" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Stage1.this, "error", Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();

                params.put("DMAIL",dmail);

                return params;


            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    //private void calculate(final int i) {


        //StringRequest stringRequest=new StringRequest(Request.Method.POST,URL_DIAGNOSIS,new Response.Listener<String>(){

           // public void onResponse(String response){
            //Toast.makeText(Stage1.this,response,Toast.LENGTH_SHORT).show();
               /*try {
                    JSONArray array = new JSONArray(response);

                  //  while(array.length()>0) {

                        JSONObject object = array.getJSONObject(0);
                        //t=object.getString("RESPONSE");
                   String questionn = object.getString("rsum");

                   //Toast.makeText(Stage1.this,,Toast.LENGTH_SHORT).show();
                        Toast.makeText(Stage1.this,questionn,Toast.LENGTH_SHORT).show();

                 //   }


                }*/
                   /* StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DIAGNOSIS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    try {
                        JSONArray array = new JSONArray(response);
                        // JSONObject object = array.getJSONObject(0);
                        JSONObject object = array.getJSONObject(i);
                        //  qid=count+1;
                        String questionn = object.getString("rsum");

                    Toast.makeText(Stage1.this,questionn, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(Answer_Question.this,response, Toast.LENGTH_SHORT).show();

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
        requestQueue.add(stringRequest);*/
    }



