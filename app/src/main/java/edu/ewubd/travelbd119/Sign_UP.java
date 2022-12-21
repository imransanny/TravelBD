package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
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


        Bundle extras = getIntent().getExtras();
        String Check_User = extras.getString("TRAVELER1").trim();
        System.out.println("Sign up=" + Check_User + "==");

        if (Check_User.equals("TRA")) {
            nid.setVisibility(View.GONE);
            nid1.setVisibility(View.GONE);

            nid.setVisibility(View.GONE);
            nid1.setVisibility(View.GONE);
            System.out.println("This is a Traveler/USER");


        } else if (Check_User.equals("MAN")) {
            System.out.println("This is Admin/Manager");

            // user.setVisibility(View.GONE);
        }

  save.setOnClickListener(v->registerUser());


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

                        if(task.isSuccessful()){
                            if(Check_User.equals("TRA")){
                                User_info user = new User_info(user_name1, email1,phone1,password1, re_password1);

                                FirebaseDatabase.getInstance().getReference("Traveler")
                                        .child(user_name1).setValue(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if(task.isSuccessful()){
                                                    Toast.makeText(Sign_UP.this, "User has been registered successfully.", Toast.LENGTH_LONG).show();
                                                    progressBar.setVisibility(View.GONE);
                                                }else{
                                                    //if store not sucessfulll redirect to login page
                                                    Toast.makeText(Sign_UP.this, "Falied to register!# Try again", Toast.LENGTH_LONG).show();
                                                    progressBar.setVisibility(View.GONE);

                                                }


                                            }
                                        });

                                progressBar.setVisibility(View.GONE);
                            }else if(Check_User.equals("MAN")){

                                Admin_info Admin = new Admin_info(user_name1, email1,phone1,password1, re_password1);

                               databaseReference = FirebaseDatabase.getInstance().getReference("Manager");
                                databaseReference.child(user_name1).setValue(Admin).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if(task.isSuccessful()){
                                                    Toast.makeText(Sign_UP.this, "User has been registered successfully.", Toast.LENGTH_LONG).show();
                                                    progressBar.setVisibility(View.GONE);
                                                }else{
                                                    //if store not sucessfulll redirect to login page
                                                    Toast.makeText(Sign_UP.this, "Falied to register! Try again", Toast.LENGTH_LONG).show();
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


