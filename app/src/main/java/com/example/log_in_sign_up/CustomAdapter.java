package com.example.log_in_sign_up;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<String> hotelList;

    public CustomAdapter(Context context, int resource, List<String> hotelList) {
        super(context, resource, hotelList);
        this.context = context;
        this.hotelList = hotelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(getLayoutResourceId(position), parent, false);

        // Customize the view for each list item
        TextView hotelNameTextView = view.findViewById(R.id.hotelName);
        ImageView hotelImageView = view.findViewById(R.id.hotelImage);

        String hotelName = hotelList.get(position);
        hotelNameTextView.setText(hotelName);

        // Set the appropriate image for each hotel
        int imageResourceId = getImageResourceId(position);
        hotelImageView.setImageResource(imageResourceId);

        return view;
    }

    // Define the layout resource ID for each hotel
    private int getLayoutResourceId(int position) {
        switch (position) {
            case 0:
                return R.layout.hotel1_layout;
            case 1:
                return R.layout.hotel2_layout;
            case 2:
                return R.layout.hotel3_layout;
            case 3:
                return R.layout.hotel4_layout;
            case 4:
                return R.layout.hotel5_layout;
            default:
                return 0;
        }
    }

    // Define the image resource ID for each hotel
    private int getImageResourceId(int position) {
        switch (position) {
            case 0:
                return R.drawable.h1;
            case 1:
                return R.drawable.h2;
            case 2:
                return R.drawable.h3;
            case 3:
                return R.drawable.h4;
            case 4:
                return R.drawable.h5;
            default:
                return 0;
        }
    }
}
