package com.example.jesna.emhealth;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
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

public class Family extends AppCompatActivity {

    Spinner spinner,spinner1;
    EditText ageEdit;
    Button sub;
    String URL_POST = "https://emhealth.000webhostapp.com/csv.php";
    ArrayAdapter<CharSequence> adapter,adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        spinner=(Spinner) findViewById(R.id.spinner1);
        sub=findViewById(R.id.Submit);

        spinner.setPrompt("Relationship with Person");
        adapter=ArrayAdapter.createFromResource(this,R.array.relation_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CSVbbb();
            }
        });





        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner1=(Spinner) findViewById(R.id.spinner);

        spinner1.setPrompt(" Sex");
        adapter1=ArrayAdapter.createFromResource(this,R.array.sex_array,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });




    }

    private void CSVbbb() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL_POST,new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                Toast.makeText(Family.this,response,Toast.LENGTH_SHORT).show();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }

        });



        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
