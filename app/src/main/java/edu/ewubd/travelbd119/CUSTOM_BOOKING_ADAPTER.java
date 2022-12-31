package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CUSTOM_BOOKING_ADAPTER extends ArrayAdapter<BOOKING> {
    private Context context;
    private ArrayList<BOOKING> values;

    public CUSTOM_BOOKING_ADAPTER(@NonNull Context context,@NonNull  ArrayList<BOOKING> objects) {
        super(context,  -1, objects);
        this.context = context;
        this.values = objects;
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.hotel_booking_item_show, parent, false);

     TextView hotel_name = rowView.findViewById(R.id.tv_hotel_name);
        TextView Booking_date = rowView.findViewById(R.id.tv_booking_date);
        TextView booking_customer_name = rowView.findViewById(R.id.tv_booking_customer_id);
        TextView phone_customer = rowView.findViewById(R.id.phone_booked);




        hotel_name.setText(values.get(position).getHotel_name());
        Booking_date.setText(values.get(position).getDate_b());
        booking_customer_name.setText(values.get(position).getName_b());
        phone_customer.setText(values.get(position).getPhone_b());

        return rowView;
    }



}
