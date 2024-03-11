package com.example.log_in_sign_up;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<String> data;
    private int[] images;

    public CustomAdapter(Context context, int layoutResourceId, String[] customTexts2, String[] texts2, String[] strings, ArrayList<String> data, int[] images, int[] hotelImages2, String[] customTexts) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
        this.images = images;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.textView = row.findViewById(R.id.hotelName);
            holder.imageView = row.findViewById(R.id.hotelImage);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.textView.setText(data.get(position));
        holder.imageView.setImageResource(images[position]);

        return row;
    }

    static class ViewHolder {
        TextView textView;
        ImageView imageView;
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
