package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;


public class Hotel_Booking extends AppCompatActivity {

    EditText nameEditText, phoneEditText, emailEditText, addressEditText,booking_dateEditText;
    TextView name, phone,email,address,date;
    TextView confirm_Booking;
    public int mYear, mMonth, mDay;
    DatabaseReference databaseReference;
    ProgressBar progressBar;





    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_booking);

      booking_dateEditText = findViewById(R.id.date_edit_id);
      booking_dateEditText.setOnClickListener(v->booking_date());
      nameEditText = findViewById(R.id.name_edit_i);
      phoneEditText = findViewById(R.id.phone_edit_i);
        emailEditText = findViewById(R.id.email_edit_i);
        addressEditText = findViewById(R.id.address_edit_i);
        confirm_Booking = findViewById(R.id.Confirm_booking_id);
        progressBar = findViewById(R.id.progressBar);




        confirm_Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Hotel_Booking_Confirm();


            }
        });










    }

    public void Hotel_Booking_Confirm() {
        SharedPreferences users= this.getSharedPreferences("TRAVELER", MODE_PRIVATE);
        String Check_Users = users.getString("TRAVELER", "");



        System.out.println("cnofirm btn work");
        String user_name11 = nameEditText.getText().toString().trim();
        String phone11 = phoneEditText.getText().toString().trim();
        String email11 = emailEditText.getText().toString().trim();
        String address11 = addressEditText.getText().toString().trim();
        String Date = booking_dateEditText.getText().toString().trim();


progressBar.setVisibility(View.VISIBLE);



         Bundle extras = getIntent().getExtras();
         String Hotel_NAME = extras.getString("HOTEL_NAME").trim();
         System.out.println("Sign up=" + Hotel_NAME + "==");

        SharedPreferences user_i = this.getSharedPreferences("USER_UID", MODE_PRIVATE);
        String uid_u = user_i.getString("Share_pref_UID", "");




        BOOKING user_booking = new BOOKING(Hotel_NAME,user_name11, phone11,email11,address11, Date);


        FirebaseDatabase.getInstance().getReference("BooKing_Hotel")
                .child(uid_u).setValue(user_booking)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Hotel_Booking.this, "Thanks the manager will confrim your booking & call", Toast.LENGTH_LONG).show();

                            Intent i = new Intent(Hotel_Booking.this, Display_HOTEL_Image.class);
                            // i.putExtra("USER_CUREENT", user_id);
                            startActivity(i);
                            progressBar.setVisibility(View.GONE);
                        }else{
                            //if store not sucessfulll redirect to login page
                            Toast.makeText(Hotel_Booking.this, "this hotel uder maintenance", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Hotel_Booking.this, Display_HOTEL_Image.class);
                            startActivity(i);
                            progressBar.setVisibility(View.GONE);

                        }


                    }
                });



    }







public void booking_date() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        booking_dateEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }




}