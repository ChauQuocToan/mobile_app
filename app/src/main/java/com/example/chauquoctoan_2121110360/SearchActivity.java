package com.example.chauquoctoan_2121110360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ImageView backsearch=findViewById(R.id.backsearch);
        backsearch.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                Intent backsearch = new Intent(getApplicationContext(), Home.class);
                startActivity(backsearch);
            }
        });
    }
}