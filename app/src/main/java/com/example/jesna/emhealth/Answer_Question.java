package com.example.jesna.emhealth;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Answer_Question extends AppCompatActivity {
    TextView tit;
    RadioButton opt1, opt2, opt3, opt4;
    Button next;
    String question[], answer[];
    public static TextView qst;
    //String URL_POST = "https://servetechnoresearch.com/Emotion/hupreg.php";
    String URL_POST_fetch = "https://servetechnoresearch.com/Emotion/fetch_question.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer__question);
        tit = findViewById(R.id.textView2);
        qst = findViewById(R.id.textView3);
        opt1 = findViewById(R.id.radioButton3);
        opt2 = findViewById(R.id.radioButton4);
        opt3 = findViewById(R.id.radioButton5);
        opt4 = findViewById(R.id.radioButton6);

        next = findViewById(R.id.next);

        //   fetchData process = new fetchData();
        // process.execute();

        InsertS();

           /* next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InsertSV();
                }
            });
        }


        private void InsertSV () {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(Answer_Question.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Answer_Question.this, "error", Toast.LENGTH_SHORT).show();
                }

            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    //String question = qstn.getText().toString();
                    String option1 = opt1.getText().toString();
                    String option2 = opt2.getText().toString();
                    String option3 = opt3.getText().toString();
                    String option4 = opt4.getText().toString();


                    //  params.put("QUESTION",question);
                    params.put("OPTION1", option1);
                    params.put("OPTION2", option2);
                    params.put("OPTION3", option3);
                    params.put("OPTION4", option4);

                    return params;

                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);*/
    }

        private void InsertS () {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST_fetch, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    try {

                        JSONArray array = new JSONArray(response);
                        JSONObject object = array.getJSONObject(0);
                        String name = object.getString("OPTION4");

                        Toast.makeText(Answer_Question.this, name, Toast.LENGTH_SHORT).show();
                       // Toast.makeText(Answer_Question.this,response, Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {

                        Toast.makeText(Answer_Question.this, "exception\n" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();


                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Answer_Question.this, "error", Toast.LENGTH_SHORT).show();
                }

            });


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
