package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    ImageView proPic;
    TextView name, email, phn,pass,re_pass;
    ArrayList<Event> events;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile);


        proPic = findViewById(R.id.profilePic);
        name = findViewById(R.id.userName_signUPP);
        email = findViewById(R.id.email_signupp);
        phn = findViewById(R.id.phone_signUPP);
        pass = findViewById(R.id.password_signupp);
        re_pass = findViewById(R.id.rePassword_singupp);

        loadDataa("hhj1672088913231");
    }

    private void loadDataa(String keyy) {


        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_p");

        if (rows.getCount() == 0) {
            return;
        }
        while (rows.moveToNext()) {

            String key = rows.getString(0);
            String eventData = rows.getString(1);
            String[] fieldValues = eventData.split("___");
            if(fieldValues.length ==9) {
                String name = fieldValues[0];
                System.out.println(name);
                String email = fieldValues[1];
                System.out.println(email);
                String phone = fieldValues[2];
                System.out.println(phone);
                String password = fieldValues[3];
                System.out.println(password);
                String re_enter_pass = fieldValues[4];
                System.out.println(re_enter_pass);
                String imageBase_64 = fieldValues[5];
                System.out.println(imageBase_64);

                //problem
               // Event e = new Event(key, name, place, capacity, budget, email, phone);
               // events.add(e);
            }else{
                System.out.println(fieldValues.length);
            }

        }
        db.close();

    }
}
