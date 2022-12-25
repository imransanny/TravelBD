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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WELCOME_PAGE extends AppCompatActivity  {

    TextView traveler, manager;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome_page);


        //check if u already login
        SharedPreferences sp = this.getSharedPreferences("Remember_login_Sharedpref", MODE_PRIVATE);


        String s1 = sp.getString("REMEMBER_USERID", "");
        String s2 = sp.getString("REMEMBER_PASSWORD", "");

        //check user
       // String user_id = mAuth.getCurrentUser().getUid();

       //databaseReference = FirebaseDatabase.getInstance().getReference().child("Traveler").child(user_id);


     //   System.out.println("user_id = "+user_id);
       // System.out.println("curent = "+databaseReference);



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
