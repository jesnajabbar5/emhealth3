package com.example.jesna.emhealth;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddQuestion extends AppCompatActivity {

    EditText ques,op1,op2,op3,op4;
    Button sub;
    String question ;
    String option1 ;
    String option2 ;
    String option3 ;
    String option4 ;
    String URL_POST="https://emhealth.000webhostapp.com/addquestion.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        ques = findViewById(R.id.ques);
        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        op3 = findViewById(R.id.op3);
        op4 = findViewById(R.id.op4);

        sub=findViewById(R.id.submit);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertSV();
            }
        });


    }

    private void InsertSV() {
         question = ques.getText().toString();
         option1 = op1.getText().toString();
         option2 = op2.getText().toString();
         option3 = op3.getText().toString();
         option4 = op4.getText().toString();

        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL_POST,new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                Toast.makeText(AddQuestion.this,response,Toast.LENGTH_SHORT).show();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(AddQuestion.this,error.toString(),Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();

                params.put("QUESTION",question);
                params.put("OPTION1",option1);
                params.put("OPTION2",option2);
                params.put("OPTION3",option3);
                params.put("OPTION4",option4);
                return params;

            }
        };



        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}




