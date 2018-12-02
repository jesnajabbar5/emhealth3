package com.example.jesna.emhealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class LifeStatus extends AppCompatActivity {

    TextView tit;
    RadioButton opt1, opt2, opt3, opt4;
    Button next;
    String value;
    String demail;
    String qid=Integer.toString(0);
    int i=20;
    public static TextView qst;
    String URL_POST = "https://emhealth.000webhostapp.com/response.php";
    String URL_POST_fetch = "https://emhealth.000webhostapp.com/fetch_question.php";

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent mintent=getIntent();
        demail=((Intent) mintent).getStringExtra("HUPEmail");

        //  Toast.makeText(Answer_Question.this,sharedemail,Toast.LENGTH_SHORT).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer__question);
        addListenerOnButton();



        tit = findViewById(R.id.textView2);
        qst = findViewById(R.id.textView3);
        opt1 = findViewById(R.id.radioButton3);
        opt2 = findViewById(R.id.radioButton4);
        opt3 = findViewById(R.id.radioButton5);
        opt4 = findViewById(R.id.radioButton6);



        DisplayQuestion(i);

    }

    private void addListenerOnButton() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btnDisplay = (Button) findViewById(R.id.next);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              int selectedId = radioGroup.getCheckedRadioButtonId();

                                              // find the radiobutton by returned id
                                              radioButton = (RadioButton) findViewById(selectedId);


                                              if (radioButton== opt1)
                                              {
                                                  value="1";
                                              }
                                              else if (radioButton== opt2)
                                              {
                                                  value="2";
                                              }
                                              else if (radioButton== opt3)
                                              {
                                                  value="3";
                                              }
                                              else if (radioButton== opt4)
                                              {
                                                  value="4";
                                              }

                                              i++;
                                              if(i==45)
                                              {
                                                  InsertRESPONSE();
                                                  Intent intent = new Intent(LifeStatus.this, Stage2.class);
                                                  intent.putExtra("HUPEmail",demail);
                                                  startActivity(intent);
                                              }


                                              if(i!=45) {
                                                  InsertRESPONSE();
                                                  DisplayQuestion(i);
                                                  qid=Integer.toString(i);
                                                  radioGroup.clearCheck();

                                              }

                                          }

                                      }
        );


    }




    private void InsertRESPONSE() {


        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL_POST,new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                Toast.makeText(LifeStatus.this,response,Toast.LENGTH_SHORT).show();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(LifeStatus.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();

                params.put("RMAIL",demail);
                params.put("QID",qid);
                params.put("RESPONSE",value);


                return params;


            }
        };



        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void DisplayQuestion (final int i) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST_fetch, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONArray array = new JSONArray(response);
                    // JSONObject object = array.getJSONObject(0);
                    JSONObject object = array.getJSONObject(i);
                    //  qid=count+1;
                    String questionn = object.getString("QUESTION");
                    String op1 = object.getString("OPTION1");
                    String op2 = object.getString("OPTION2");
                    String op3 = object.getString("OPTION3");
                    String op4 = object.getString("OPTION4");
                    qst.setText(questionn);
                    opt1.setText(op1);
                    opt2.setText(op2);
                    opt3.setText(op3);
                    opt4.setText(op4);

                    //Toast.makeText(Answer_Question.this,questionn, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(Answer_Question.this,response, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {

                    Toast.makeText(LifeStatus.this, "exception\n" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LifeStatus.this, "error", Toast.LENGTH_SHORT).show();
            }

        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
