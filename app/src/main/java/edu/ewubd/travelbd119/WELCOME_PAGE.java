package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.TextView;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

public class WELCOME_PAGE extends AppCompatActivity  {

    TextView traveler, manager;

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome_page);


        //check if u already login
        SharedPreferences sp = this.getSharedPreferences("Remember_login_Sharedpref", MODE_PRIVATE);


        String s1 = sp.getString("REMEMBER_USERID", "");
        String s2 = sp.getString("REMEMBER_PASSWORD", "");

        if(s2.equals("YES")){

            Intent i = new Intent(WELCOME_PAGE.this, Home.class);
            startActivity(i);
            finish();

        }



        traveler = findViewById(R.id.travel_idd);
        manager = findViewById(R.id.manage_idd);
        traveler.setOnClickListener(v-> traveler());
        manager.setOnClickListener(v-> manager());








    }

    private void traveler() {
        Intent i = new Intent(WELCOME_PAGE.this, MainActivity.class);
        i.putExtra("TRAVELER","TRA");
        startActivity(i);
        //finish();
    }
    private void manager() {
        Intent i = new Intent(WELCOME_PAGE.this, MainActivity.class);
        i.putExtra("TRAVELER","MAN");
        startActivity(i);
    }





}
