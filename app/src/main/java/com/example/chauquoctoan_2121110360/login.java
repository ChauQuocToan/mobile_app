package com.example.chauquoctoan_2121110360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    //tạo đk đăng nhâp
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnNext=findViewById(R.id.btnNext);
        Button btnNext1=findViewById(R.id.btnNext1);



        btnNext.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {

                Intent next = new Intent(getApplicationContext(), Register.class);
                startActivity(next);

            }
        });

           btnNext1.setOnClickListener(new View.OnClickListener()
           {

               @Override
               public void onClick(View view) {
                   isAllFieldsChecked = validateEditText();
                   if (isAllFieldsChecked) {
                       Intent next1=new Intent(getApplicationContext(), Home.class);
                       startActivity(next1);
                   }

               }
           });

    }
    private boolean validateEditText() {
        EditText username = findViewById(R.id.enteredUsername);
        EditText password = findViewById(R.id.enteredPassword);
        String usernameInput = username.getText().toString().trim();
        String passwordInput = password.getText().toString().trim();
        if (usernameInput.length() == 0) {
            username.setError("Vui lòng nhập tài khoản");
            return false;
        } else if (!usernameInput.equals("toan")) {
            username.setError("Tài khoản hoặc mật khẩu không đúng");
            return false;
        }

        if (passwordInput.length() == 0) {
            password.setError("Vui lòng nhập mật khẩu");
            return false;
        } else if (!passwordInput.equals("123456")) {
            password.setError("Tài khoản hoặc mật khẩu không đúng");
            return false;
        }

        return true;
    }
    }