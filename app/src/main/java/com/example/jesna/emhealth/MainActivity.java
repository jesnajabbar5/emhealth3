package com.example.jesna.emhealth;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;


import org.json.JSONObject;

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

public class MainActivity extends AppCompatActivity {
     String result;
     String[] status = {"false"};
     String URL_POST = "https://servetechnoresearch.com/Emotion/login.php";
     String emailn;
     String passwordn;
     Button login;
     Button newuser;
     TextView email;
     TextView password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.btnSingIn);
        newuser = findViewById(R.id.btnReg);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPass);

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Normal.class);
                startActivity(intent);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Correct();
            }
        });


    }
        private void Correct () {
            emailn = email.getText().toString();
            passwordn = password.getText().toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {
                @Override

                public void onResponse(String response) {
                    try {

                        JSONObject object = new JSONObject(response);

                        status[0] = (String) object.getString("status");
                        if (status[0].equals("true")) {

                            Intent intent = new Intent(MainActivity.this, AddQuestion.class);
                            startActivity(intent);
                        } else {
                            Toast toast = Toast.makeText(MainActivity.this, "Invalid Username or Password ", Toast.LENGTH_SHORT);
                            toast.show();
                        }


                    } catch (Exception e) {

                        Toast.makeText(MainActivity.this, "exception\n" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();


                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }

            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("EMAIL", emailn);
                    params.put("PASSWORD", passwordn);
                    return params;

                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }}





     /* login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
         String passw=
                if()
                Intent intent=new Intent(MainActivity.this,Family.class);
                startActivity(intent);
            }
        });

        nor=findViewById(R.id.normal_button);
        nor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,Normal.class);
                startActivity(intent);

            }
        });


      login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(MainActivity.this,);
              startActivity(intent);

          }
      });
*/


/*login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(MainActivity.this,Answer_Question.class);
        startActivity(intent);
    }
});
    }

}*/