package edu.ewubd.travelbd119;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView traveler, manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        traveler = findViewById(R.id.travel_id);
        manager = findViewById(R.id.manage_id);

        traveler.setOnClickListener(v-> traveler());
        manager.setOnClickListener(v-> manager());


    }

    private void traveler() {
        Intent i= new Intent(MainActivity.this, Home.class);
        startActivity(i);
    }

    private void manager() {
        Intent i= new Intent(MainActivity.this, Home.class);
    }


}