package com.example.jesna.emhealth;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class Normal extends AppCompatActivity {

    Button submit;
    EditText name;
    EditText email;
    EditText age;
    EditText pass;
    Spinner gender;
    String URL_POST="https://emhealth.000webhostapp.com/registration.php";
    String value1;
    String value2;
    String value3 ;
    String value4;
    String value5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        submit=findViewById(R.id.Submit);
        name=findViewById(R.id.nPname);
        email=findViewById(R.id.nemail);
        age=findViewById(R.id.nage);
        gender=findViewById(R.id.nspinner);
        pass=findViewById(R.id.npass);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().toString().equals("")||email.getText().toString().equals("")||gender.getSelectedItem().toString().equals("")||age.getText().toString().equals("")||pass.getText().toString().equals(""))
                {
                    Toast.makeText(Normal.this,"PLEASE FILL THE DETAILS !",Toast.LENGTH_SHORT).show();
                }
                else {
                    InsertSV();
                }
            }
        });
    }

    private void InsertSV(){
         value1=name.getText().toString();
         value2=email.getText().toString();
         value3 =gender.getSelectedItem().toString();
         value4=age.getText().toString();
         value5=pass.getText().toString();
        //Toast.makeText(this,value3,Toast.LENGTH_SHORT).show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL_POST,new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                Toast.makeText(Normal.this,response,Toast.LENGTH_SHORT).show();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(Normal.this,error.toString(),Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError{
                Map<String,String> params=new HashMap<String,String>();


                params.put("NAME",value1);
                params.put("EMAIL",value2);
                params.put("GENDER",value3);
                params.put("AGE",value4);
                params.put("PASSWORD",value5);
                return params;

            }
        };



        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        }

    }







