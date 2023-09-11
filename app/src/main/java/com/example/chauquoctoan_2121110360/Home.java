
package com.example.chauquoctoan_2121110360;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private String url = "https://api.npoint.io/44e8ddfaaeefdfef9387";
    private RequestQueue mRequestQueue;
    private List<Item> itemList;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getData();
        ImageView XemThem=findViewById(R.id.XemThem);
        ImageView clickimage=findViewById(R.id.clickimage);
        ImageView nextsearch=findViewById(R.id.nextsearch);

        nextsearch.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                Intent nextsearch = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(nextsearch);
            }
        });
        XemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  XemThem = new Intent(getApplicationContext(), Cart.class);
                startActivity( XemThem);
            }
        });
        clickimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  Click = new Intent(getApplicationContext(), ProductDetails.class);
                startActivity( Click);
            }
        });

    }

    private void getData() {
        mRequestQueue = Volley.newRequestQueue(this);
        itemList = new ArrayList<>();
        itemAdapter = new ItemAdapter(itemList);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(itemAdapter);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        for (int i = 0; i < 4; i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int id = jsonObject.getInt("id");
                            String title = jsonObject.getString("title");
                            String price = jsonObject.getString("price");
                            String imageUrl = jsonObject.getString("image");
                            Item item = new Item(id, title, price+"đ", imageUrl);
                            itemList.add(item);
                        }
                        itemAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.i(TAG, "Error: " + error.toString()));

        mRequestQueue.add(jsonArrayRequest);
    }

    private class Item {
        private String title;
        private String price;
        private String imageUrl;
        private int id;



        public Item(int id, String title, String price, String imageUrl) {
            this.id = id;
            this.title = title;
            this.price = price;
            this.imageUrl = imageUrl;
        }

        public int getId() { return id; }

        public String getTitle() {
            return title;
        }

        public String getPrice() {
            return price;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
        private List<Item> itemList;

        public ItemAdapter(List<Item> itemList) {
            this.itemList = itemList;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            Item item = itemList.get(position);
            holder.titleTextView.setText(item.getTitle());
            holder.priceTextView.setText(item.getPrice());
            Picasso.get().load(item.getImageUrl()).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;
            private TextView titleTextView;
            private TextView priceTextView;

            public ItemViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.itemImage);
                titleTextView = itemView.findViewById(R.id.itemTitle);
                priceTextView = itemView.findViewById(R.id.itemPrice);

                itemView.setOnClickListener(v -> {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Item clickedItem = itemList.get(position);
                        int itemId = clickedItem.getId();

                        // Navigate to the detail page with the selected item's ID
                        Intent intent = new Intent(itemView.getContext(), ProductDetails.class);
                        intent.putExtra("itemId", itemId);
                        itemView.getContext().startActivity(intent);
                    }
                });
            }
        }
    }

}