package com.example.log_in_sign_up;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ListActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String ITEM_SET_KEY = "itemSet";

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private Set<String> itemSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);

        // Load saved items
        loadItems();

        // Initialize adapter with empty list
        adapter = new ArrayAdapter<>(this, R.layout.list_item_layout, new ArrayList<String>());

        // Set adapter to list view
        listView.setAdapter(adapter);

        // Add new item if available
        String userName = getIntent().getStringExtra("userName");
        String hotelName = getIntent().getStringExtra("hotelName");
        String location = getIntent().getStringExtra("location");
        String activityName = getIntent().getStringExtra("activityName");
        String roomName = getIntent().getStringExtra("roomName");
        String selectedDate = getIntent().getStringExtra("selectedDate");

        if (userName != null && hotelName != null && location != null && activityName != null && roomName != null && selectedDate != null) {
            String itemDetails = userName + "\n" +
                    hotelName + "\n" +
                    location + "\n" +
                    activityName + "\n" +
                    roomName + "\n" +
                    selectedDate;

            // Add the item details to itemSet
            itemSet.add(itemDetails);

            // Save items
            saveItems();

            // Update adapter with new item
            adapter.add(itemDetails);
        }
    }

    private void loadItems() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        itemSet = preferences.getStringSet(ITEM_SET_KEY, new HashSet<String>());
    }

    private void saveItems() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(ITEM_SET_KEY, itemSet);
        editor.apply();
    }
}
