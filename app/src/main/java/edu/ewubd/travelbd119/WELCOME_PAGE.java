package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.TextView;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

public class WELCOME_PAGE extends AppCompatActivity {

    TextView traveler, manager;

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome_page);

        traveler = findViewById(R.id.travel_idd);
        manager = findViewById(R.id.manage_idd);
        traveler.setOnClickListener(v-> traveler());
        manager.setOnClickListener(v-> manager());

    }

    private void traveler() {
        Intent i = new Intent(WELCOME_PAGE.this, Home.class);
        startActivity(i);
    }
    private void manager() {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }
}
