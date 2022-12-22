package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.charset.StandardCharsets;

public class Sign_UP extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextView nid1;
    EditText nid, user_name, email, phone, pass, re_pass;
    Button save, cancle;
    ProgressBar progressBar;
    DatabaseReference databaseReference;
    String Check_User;
    int Traveler=0, Manager=0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mAuth = FirebaseAuth.getInstance();


        nid1 = findViewById(R.id.nid_text);
        nid = findViewById(R.id.nid_signupp);
        user_name = findViewById(R.id.userName_signUPP);
        email = findViewById(R.id.email_signupp);
        phone = findViewById(R.id.phone_signUPP);
        pass = findViewById(R.id.password_signupp);
        re_pass = findViewById(R.id.rePassword_singupp);
        save = findViewById(R.id.signUpSave);
        cancle = findViewById(R.id.signup_cancle);
        progressBar = findViewById(R.id.Progreess_signup);
        cancle.setOnClickListener(v->cancle());


        Bundle extras = getIntent().getExtras();
        String Check_User = extras.getString("TRAVELER1").trim();
        System.out.println("Sign up=" + Check_User + "==");

        if (Check_User.equals("TRA")) {
            nid.setVisibility(View.GONE);
            nid1.setVisibility(View.GONE);

            nid.setVisibility(View.GONE);
            nid1.setVisibility(View.GONE);
            System.out.println("This is a Traveler/USER");
            Traveler =1;


        } else if (Check_User.equals("MAN")) {
            System.out.println("This is Admin/Manager");

            // user.setVisibility(View.GONE);
            nid.setVisibility(View.GONE);
            nid1.setVisibility(View.GONE);

            nid.setVisibility(View.GONE);
            nid1.setVisibility(View.GONE);
            System.out.println("This is a Traveler/USER");
            Manager = 1;
        }

  save.setOnClickListener(v->registerUser());


    }

    private void cancle() {
        Intent i = new Intent(Sign_UP.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void registerUser() {
        System.out.println("SAve btn work");
         String user_name1 = user_name.getText().toString().trim();
         String email1 = email.getText().toString().trim();
         String phone1 = phone.getText().toString().trim();
         String password1 = pass.getText().toString().trim();
         String re_password1 = re_pass.getText().toString().trim();

        if(user_name1.isEmpty()){
            user_name.setError("Name is required");
            user_name.requestFocus();
            return;
        }if(user_name1.length()<=2){
            user_name.setError("Enter a valid name");
            user_name.requestFocus();
            return;
        }if(email1.isEmpty()){
            email.setError("Email address is required");
            email.requestFocus();
            return;
        }  if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
            email.setError("Please valid email address");
            email.requestFocus();
            return;
        }if(phone1.isEmpty()){
           // phone1.setError("Phone number is required");
            Toast.makeText(getApplicationContext(),"please Provide valid phone number",Toast.LENGTH_LONG).show();
      //      phone1.();

            return;
        }if(phone1.length()<10 || phone1.length()>13){
            Toast.makeText(getApplicationContext(),"please Provide valid phone number",Toast.LENGTH_LONG).show();
        }if (password1.isEmpty()){
            pass.setError("Password is required");
            pass.requestFocus();
            return;
        }
        if(password1.length()<6){
            pass.setError("Min Password length should be 6 characters!");
            pass.requestFocus();
            return;
        }if (re_password1.isEmpty()){
            re_pass.setError("Re-type your Password");
            re_pass.requestFocus();
            return;
        }
        if(!password1.equals(re_password1)){
            re_pass.setError("Password does not match");
            re_pass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);


//===========================================================
//USER Register

        mAuth.createUserWithEmailAndPassword(email1,password1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        System.out.println("VALUE Of =="+Traveler + Manager);
                        if(task.isSuccessful()){
                            if(Traveler==1){
                                System.out.println("Successfull TRAVERLER=====");
                                User_info user = new User_info(user_name1, email1,phone1,password1, re_password1);

                                FirebaseDatabase.getInstance().getReference("Traveler")
                                        .child(phone1).setValue(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if(task.isSuccessful()){
                                                    Toast.makeText(Sign_UP.this, "User has been registered successfully.", Toast.LENGTH_LONG).show();
                                                    Intent i = new Intent(Sign_UP.this, MainActivity.class);
                                                    startActivity(i);
                                                    progressBar.setVisibility(View.GONE);
                                                }else{
                                                    //if store not sucessfulll redirect to login page
                                                    Toast.makeText(Sign_UP.this, "Falied to register!# Try again", Toast.LENGTH_LONG).show();
                                                    Intent i = new Intent(Sign_UP.this, MainActivity.class);
                                                    startActivity(i);
                                                    progressBar.setVisibility(View.GONE);

                                                }


                                            }
                                        });

                                progressBar.setVisibility(View.GONE);
                            }else if(Manager==1){
                                System.out.println("MAnager success"+Traveler+Manager);

                                Admin_info admin = new Admin_info(user_name1, email1,phone1,password1, re_password1);

                               databaseReference = FirebaseDatabase.getInstance().getReference("Manager");
                                databaseReference.child(phone1).setValue(admin).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if(task.isSuccessful()){
                                                    Toast.makeText(Sign_UP.this, "User has been registered successfully.", Toast.LENGTH_LONG).show();
                                                    Intent i = new Intent(Sign_UP.this, MainActivity.class);
                                                    startActivity(i);
                                                    progressBar.setVisibility(View.GONE);
                                                }else{
                                                    //if store not sucessfulll redirect to login page
                                                    Toast.makeText(Sign_UP.this, "Falied to register! Try again", Toast.LENGTH_LONG).show();
                                                    Intent i = new Intent(Sign_UP.this, MainActivity.class);
                                                    startActivity(i);
                                                    progressBar.setVisibility(View.GONE);

                                                }

                                            }
                                        });

                            }
                        }else{
                            System.out.println("aDIMIN17");
                            Toast.makeText(Sign_UP.this,"Failed to register!!! Try again", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


    }


}


