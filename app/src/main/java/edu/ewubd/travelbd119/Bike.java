package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Bike extends AppCompatActivity {

    TextView slots;
    EditText check_in, check_out;
    Button available_bike;
    ImageView bike_pic;
    RadioButton slot1, slot2, slot3;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bikerent);

        slots = findViewById(R.id.bike_slots);

        check_in = findViewById(R.id.bike_checkin);
        check_out = findViewById(R.id.bike_checkout);

        available_bike = findViewById(R.id.bike_search);

        bike_pic = findViewById(R.id.image_bike);

        slot1 = findViewById(R.id.btn_bikeslot1);
        slot2 = findViewById(R.id.btn_bikeslot2);
        slot3 = findViewById(R.id.btn_bikeslot3);

      //  slot1.setOnClickListener(v -> select_slot_1());
       // slot2.setOnClickListener(v -> select_slot_2());
        // slot3.setOnClickListener(v -> select_slot_3());

        available_bike.setOnClickListener(v -> check_bike());
    }

    private void check_bike() {
        Intent i = new Intent(Bike.this, bike_availability.class);
        startActivity(i);
    }

}
