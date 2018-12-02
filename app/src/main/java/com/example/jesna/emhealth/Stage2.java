package com.example.jesna.emhealth;

import android.app.Activity;
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

public class Stage2 extends AppCompatActivity {


    String URL_FEATURE = "https://emhealth.000webhostapp.com/features.php";
    String URL_FINAL = "https://emhealth.000webhostapp.com/stage2.php";
    String mail2;
    Button go;
    TextView res;
    int f1,f2,f3,f4,f5;
    int[] nums;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1);
        go = findViewById(R.id.go);
        res = findViewById(R.id.textView4);
        Intent intent2 = getIntent();
        mail2 = ((Intent) intent2).getStringExtra("HUPEmail");

        addfeature();
        //Toast.makeText(Stage1.this,dmail,Toast.LENGTH_SHORT).show();
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    DisplayFeature();
            }
        });
    }

    private void addfeature() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FEATURE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(Stage2.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Stage2.this, "error", Toast.LENGTH_SHORT).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("DMAIL", mail2);

                return params;


            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void DisplayFeature () {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FINAL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               // String feature="The main external factor that leads to you depressive emotion is :"+response;

//                res.setText(feature);

               // Toast.makeText(Stage2.this, response, Toast.LENGTH_SHORT).show();

                try {
                    JSONArray array = new JSONArray(response);
                    // JSONObject object = array.getJSONObject(0);
                    JSONObject object = array.getJSONObject(0);
                    //  qid=count+1;
                    final String ftr1 = object.getString("F1");
                    final String ftr2 = object.getString("F2");
                    String ftr3 = object.getString("F3");
                    String ftr4 = object.getString("F4");
                    String ftr5 = object.getString("F5");

                    String str1="\nWORK PRESSURE:"+ftr1;
                    String str2="\nCOPING STYLE:"+ftr2;
                    String str3="\nSELF PROMOTION BURDEN:"+ftr3;
                    String str4="\nECONOMIC BURDEN:"+ftr4;
                    String str5="\nSOCIAL SUPPORT:"+ftr5;

                    go.setText("VIEW RECOMMENDATION");
                    res.setText(str1);
                    res.append(str2);
                    res.append(str3);
                    res.append(str4);
                    res.append(str5);
                    //Toast.makeText(Answer_Question.this,questionn, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(Answer_Question.this,response, Toast.LENGTH_SHORT).show();

                    f1=Integer.parseInt(ftr1);
                     f2=Integer.parseInt(ftr2);
                     f3=Integer.parseInt(ftr3);
                     f4=Integer.parseInt(ftr4);
                     f5=Integer.parseInt(ftr5);


                } catch (Exception e) {

                    Toast.makeText(Stage2.this, "exception\n" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();


                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Stage2.this, "error", Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("DMAIL", mail2);

                return params;


            }
        };


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int max = f1;
                max = (max > f2) ? max : f2;
                max = (max > f3) ? max : f3;
                max = (max > f4) ? max : f4;
                max = (max > f5) ? max : f5;


                if(max==f1)
                {
                    String result="The main external factor that lead to your depression is WORK PRESSURE\n";

                    res.setText(result);
                    res.append("OUR RECOMMENDATIONS :\n MUSIC THERAPY\n EXERCISE THERAPY");
                }

                else if(max==f2)
                {
                    String result="The main external factor that lead to your depression is COPYING STYLE\n";

                    res.setText(result);
                    res.append("OUR RECOMMENDATIONS :\n TRY SOMETHING NEW\n READ INTERESTING STORIES AND PLAY GAMES");
                }

                else if(max==f3)
                {
                    String result="The main external factor that lead to your depression is SELF PROMOTION BURDEN\n";

                    res.setText(result);
                    res.append("OUR RECOMMENDATIONS :\n CULTIVATE A VARIETY OF HOBBIES\n KEEP DOING WHAT YOU LIKE TO");
                }

                else if(max==f4)
                {
                    String result="The main external factor that lead to your depression is ECONOMIC BURDEN\n";

                    res.setText(result);
                    res.append("OUR RECOMMENDATIONS :\n PSYCHOTHERAPY\n REMEMBER MONEY IS NOT EVERYTHING...");
                }

                else if(max==f5)
                {
                    String result="The main external factor that lead to your depression is EXTERNAL SOCIAL SUPPORT\n";

                    res.setText(result);
                    res.append("OUR RECOMMENDATIONS :\n TRY TO COMMUNICATE WITH YOUR FAMILY MEMBERS,FRIENDS AND LOVER!!");
                }

                go.setText("THANK YOU");

                go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        System.exit(0);
                    }
                });
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
