package com.example.jesna.emhealth;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login=findViewById(R.id.btnSingIn);
        Button newuser=findViewById(R.id.btnReg);
/*      TextView emai=findViewById(R.id.etEmail);
        TextView pass=findViewById(R.id.etPass);



     /*  login.setOnClickListener(new View.OnClickListener() {
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

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddQuestion.class);
                startActivity(intent);
            }
        });
login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(MainActivity.this,Answer_Question.class);
        startActivity(intent);
    }
});
    }

}