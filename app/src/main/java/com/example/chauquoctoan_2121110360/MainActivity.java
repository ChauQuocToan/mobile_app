package com.example.chauquoctoan_2121110360;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<JSONObject> dataList;
    private ArrayAdapter<JSONObject> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<JSONObject>(this, R.layout.list_item, dataList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.list_item, parent, false);
                }

                // Get the views from the list item layout
                ImageView imageView = convertView.findViewById(R.id.imageView);
                TextView userIdText = convertView.findViewById(R.id.user_id);
                TextView idText = convertView.findViewById(R.id.id);
                TextView titleText = convertView.findViewById(R.id.title);

                // Get the JSON object at the current position
                JSONObject jsonObject = getItem(position);

                // Extract the data from the JSON object and set it to the views
                try {
                    String imageUrl = jsonObject.getString("imageUrl");
                    int userId = jsonObject.getInt("userId");
                    int id = jsonObject.getInt("id");
                    String title = jsonObject.getString("title");

                    Picasso.get()
                            .load(imageUrl)
                            .resize(100, 100)
                            .centerCrop()
                            .into(imageView);

                    userIdText.setText("User ID: " + String.valueOf(userId));
                    idText.setText("ID: " + String.valueOf(id));
                    titleText.setText("Title: " + title);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return convertView;
            }
        };

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JSONObject selectedData = (JSONObject) parent.getItemAtPosition(position);
                try {
                    String imageUrl = selectedData.getString("imageUrl");

                    // Chuyển sang trang khác và chuyển dữ liệu
                    Intent intent = new Intent(MainActivity.this, ct1.class);
                    intent.putExtra("selectedData", selectedData.toString());
                    intent.putExtra("imageUrl", imageUrl);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        String url = "https://api.npoint.io/de43430cb196695f4318";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        dataList.add(jsonObject);
                    }

                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}