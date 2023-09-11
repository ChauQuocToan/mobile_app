package com.example.chauquoctoan_2121110360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Registercheck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registercheck);




        Intent intent =this.getIntent();

        String data=intent.getStringExtra("name");
        TextView txtKq=findViewById(R.id.name);
        txtKq.setText(data);

        String gender = getIntentExtra("pronoun");
        TextView genderTxt = findViewById(R.id.textKQ);
        genderTxt.setText(gender);

        Button continues = findViewById(R.id.continue1123);
        continues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });

    }
    private String getIntentExtra(String key) {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(key)) {
            return intent.getStringExtra(key);
        }
        return "";
    }
}

