package edu.ewubd.travelbd119;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class welcome extends AppCompatActivity {

    TextView travel, manage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome);

        travel = findViewById(R.id.travel);
        manage = findViewById(R.id.manage);

        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent Here to Login
            }
        });

        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intend here to Login as Manger
            }
        });
    }
}
