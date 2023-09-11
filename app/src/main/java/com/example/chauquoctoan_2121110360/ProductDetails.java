package com.example.chauquoctoan_2121110360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;

public class ProductDetails extends AppCompatActivity {
    private int quantity = 0;
    private int price = 10; // Giá sản phẩm
    private TextView tvQuantity;
    private TextView tvPrice;
    private String url = "https://api.npoint.io/44e8ddfaaeefdfef9387";
    private int itemId;
    private TextView titleTextView;
    private TextView priceTextView;
    private ImageView details_img;
    private TextView details_desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        itemId = getIntent().getIntExtra("itemId", -1);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvPrice = findViewById(R.id.tvPrice);
        Button addcart=findViewById(R.id.addcart);
        ImageView backdetail=findViewById(R.id.backdetail);
        backdetail.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                Intent backdetail = new Intent(getApplicationContext(), Home.class);
                startActivity(backdetail);
            }
        });
        addcart.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {

                Intent addcart = new Intent(getApplicationContext(),Cart.class);
                startActivity(addcart);

            }
        });

    }
    public void increaseQuantity(View view) {
        quantity++;
        displayQuantityAndPrice();
    }

    public void decreaseQuantity(View view) {
        if (quantity > 0) {
            quantity--;
            displayQuantityAndPrice();
        }
        titleTextView = findViewById(R.id.details_title);
        priceTextView = findViewById(R.id.details_price);
        details_img = findViewById(R.id.details_img);


        fetchProductData(itemId);
    }


    private void displayQuantityAndPrice() {
        tvQuantity.setText(String.valueOf(quantity));
        int totalPrice = quantity * price;
        tvPrice.setText(String.valueOf(totalPrice));
    }

    private void fetchProductData(int itemId) {
        String productDetailUrl = url + "/" + (itemId - 1);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, productDetailUrl, null,
                response -> {
                    try {
                        String title = response.getString("title");
                        String price = response.getString("price");
                        String desc = response.getString("desc");
                        String imageUrl  = response.getString("image");

                        titleTextView.setText(title);
                        priceTextView.setText(price+".000đ");
                        Picasso.get().load(imageUrl).into(details_img);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.i(ContentValues.TAG, "Error: " + error.toString()));

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}
