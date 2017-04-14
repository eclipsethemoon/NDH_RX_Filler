package com.selu.neilhoang.ndh_rx_filler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
        private Button button;
        private EditText userWNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button =(Button) findViewById(R.id.loginBtn);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userWNum = (EditText) findViewById(R.id.userWNum);
                if(userWNum.length() != 7)
                {
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.TOP|Gravity.LEFT,0,0);
                    toast.makeText(LoginActivity.this,"Enter a seven digits number",toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.TOP|Gravity.LEFT,0,0);
                    toast.makeText(LoginActivity.this,"Succeeded Login!",toast.LENGTH_LONG).show();
                    Intent display = new Intent(LoginActivity.this,DisplayActivity.class);
                    startActivity(display);
                }
            }
        });
    }
}
