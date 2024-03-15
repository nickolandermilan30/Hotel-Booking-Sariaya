package com.example.log_in_sign_up;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

        // Set adapter
        listView.setAdapter(adapter);

        // Add new item
        String userName = getIntent().getStringExtra("userName");
        String hotelName = getIntent().getStringExtra("hotelName");
        String location = getIntent().getStringExtra("location");
        String activityName = getIntent().getStringExtra("activityName");
        String roomName = getIntent().getStringExtra("roomName");
        String selectedDate = getIntent().getStringExtra("selectedDate");

        String itemDetails = userName + "\n" +
                 hotelName + "\n" +
                 location + "\n" +
                activityName + "\n" +
                roomName + "\n" +
                selectedDate;

        itemSet.add(itemDetails);

        // Notify adapter of changes
        adapter.add(itemDetails);
        adapter.notifyDataSetChanged();

        // Save items
        saveItems();

        // Set adapter
        adapter = new ArrayAdapter<String>(this, R.layout.list_item_layout, new ArrayList<String>(itemSet)) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.list_item_layout, null);
                }

                TextView userNameTextView = view.findViewById(R.id.userNameTextView);
                TextView hotelNameTextView = view.findViewById(R.id.hotelNameTextView);
                TextView locationTextView = view.findViewById(R.id.locationTextView);
                TextView activityNameTextView = view.findViewById(R.id.activityNameTextView);
                TextView roomNameTextView = view.findViewById(R.id.roomNameTextView);
                TextView selectedDateTextView = view.findViewById(R.id.selectedDateTextView);

                // Split itemDetails to get individual fields
                String[] fields = getItem(position).split("\n");

                // Set text for TextViews
                userNameTextView.setText(fields[0]);
                hotelNameTextView.setText(fields[1]);
                locationTextView.setText(fields[2]);
                activityNameTextView.setText(fields[3]);
                roomNameTextView.setText(fields[4]);
                selectedDateTextView.setText(fields[5]);

                return view;
            }
        };
        listView.setAdapter(adapter);
    }

    private void loadItems() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        itemSet = preferences.getStringSet(ITEM_SET_KEY, new HashSet<String>());
        itemSet.clear(); // Linisin ang itemSet
    }


    private void saveItems() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(ITEM_SET_KEY, itemSet);
        editor.apply();
    }
}
