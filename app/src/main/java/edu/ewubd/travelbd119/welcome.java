package edu.ewubd.travelbd119;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class welcome extends AppCompatActivity {

    TextView traveler, manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome);

        traveler = findViewById(R.id.travel_id);
        manager = findViewById(R.id.manage_id);
        traveler.setOnClickListener(v -> traveler());
        manager.setOnClickListener(v -> manager());

    }

    private void traveler() {
        Intent i = new Intent(welcome.this, Home.class);
        startActivity(i);
    }
    private void manager() {
        Intent i = new Intent(welcome.this, Home.class);
        startActivity(i);
    }
}
