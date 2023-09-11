package com.example.chauquoctoan_2121110360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button createBtn = findViewById(R.id.create);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Registercheck.class);

                TextView txtText = findViewById(R.id.nameRegister);

                String data = txtText.getText().toString();
                intent.putExtra("name", data);

                String pronoun;
                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton3) {
                    pronoun = "Chúc mừng anh: ";
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton2) {
                    pronoun = "Chúc mừng chị: ";
                } else pronoun = "";
                sendData(data, pronoun);
            }
        });
    }

    public void sendData(String data, String pronoun) {
        Intent intent = new Intent(getApplicationContext(),Registercheck.class);
        intent.putExtra("name", data);
        intent.putExtra("pronoun", pronoun);
        startActivity(intent);

    }
}