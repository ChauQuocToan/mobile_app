package com.example.chauquoctoan_2121110360;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Cart extends AppCompatActivity {
    private int quantity = 0;
    private int quantity2 = 0;
    private int quantity3 = 0;
    private int price = 10; // Giá sản phẩm
    private TextView tvQuantity;
    private TextView tvQuantity2;
    private TextView tvQuantity3;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkAllCheckBox;
    private boolean isAllChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        tvQuantity = findViewById(R.id.tvQuantity);
        tvQuantity2 = findViewById(R.id.tvQuantity2);
        tvQuantity3 = findViewById(R.id.tvQuantity3);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkAllCheckBox = findViewById(R.id.checkAllCheckBox);
        ImageView backcart = findViewById(R.id.backcart);
        backcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backcart = new Intent(getApplicationContext(), Home.class);
                startActivity(backcart);
            }
        });

        checkAllCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!isAllChecked) {
                    checkBox1.setChecked(true);
                    checkBox2.setChecked(true);
                    checkBox3.setChecked(true);
                    isAllChecked = true;
                } else {
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    isAllChecked = false;
                }
            } else {
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                isAllChecked = false;
            }
        });
    }
///end checkbox//

    public void increaseQuantity(View view) {
        quantity++;
        displayQuantityAndPrice();
    }
    public void decreaseQuantity(View view) {
        if (quantity > 0) {
            quantity--;
            displayQuantityAndPrice();
        }
    }

    private void displayQuantityAndPrice() {
        tvQuantity.setText(String.valueOf(quantity));
    }




    public void increaseQuantity2(View view) {
        quantity2++;
        displayQuantityAndPrice2();
    }

    public void decreaseQuantity2(View view) {
        if (quantity2 > 0) {
            quantity2--;
            displayQuantityAndPrice2();
        }
    }

    private void displayQuantityAndPrice2() {
        tvQuantity2.setText(String.valueOf(quantity2));
    }







    public void increaseQuantity3(View view) {
        quantity3++;
        displayQuantityAndPrice3();
    }

    public void decreaseQuantity3(View view) {
        if (quantity3 > 0) {
            quantity3--;
            displayQuantityAndPrice3();
        }
    }

    private void displayQuantityAndPrice3() {
        tvQuantity3.setText(String.valueOf(quantity3));
    }

}

