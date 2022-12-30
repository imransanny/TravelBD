package edu.ewubd.travelbd119;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView signup, login, forgetpass;
    ProgressBar progressBar;
    EditText username, pass;
    CheckBox remember_user, remember_password;
    private FirebaseAuth mAuth;
    String email1;
    String passs;
    String re_pass;
    String keyy;
    int clear = 1;
    String lastrow, firstrow;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        username = findViewById(R.id.username_main);
        login = findViewById(R.id.login_main);


        //check user
        //   String user_id = mAuth.getCurrentUser().getUid();


        // DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Traveler").child(user_id);


        // System.out.println("user_id = "+user_id);
        //System.out.println("curent = "+current_user_db);


        //============================================================================
        //shared preference check login

        SharedPreferences sp = this.getSharedPreferences("Remember_login_Sharedpref", MODE_PRIVATE);

        String s1 = sp.getString("REMEMBER_USERID", "");
        String s2 = sp.getString("REMEMBER_PASSWORD", "");


        if (s2.equals("YES")) {

            Intent i = new Intent(MainActivity.this, Home.class);

            startActivity(i);


            // finish()
        }

        //=======================================================================


        signup = findViewById(R.id.signup_main_id);
        signup.setOnClickListener(v -> SIGNUP());

        forgetpass = findViewById(R.id.forget_main);

        pass = findViewById(R.id.pass_main);
        progressBar = findViewById(R.id.Progreess_mainactivity);
        login.setOnClickListener(v -> Login());
        remember_user = findViewById(R.id.checkbox_remember_userID);
        remember_password = findViewById(R.id.checkbox_remember_password);

    }

    private void Login() {


        //validation checking==============================================
        String email = username.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (email.isEmpty()) {
            username.setError("Email is required!");
            username.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            username.setError("Please enter a valid email!");
            username.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            pass.setError("Password is required");
            pass.requestFocus();
            return;
        }
        if (password.length() < 6) {
            pass.setError("Min password length is 6 characters!");
            pass.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        //validation checking==============================================END


        //Shared Preference Store Checkbox
        //============================================================

        SharedPreferences sp = this.getSharedPreferences("Remember_login_Sharedpref", MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();

        if (remember_user.isChecked()) {
            e.putString("REMEMBER_USERID", "YES");
            System.out.println("click user yes");
        } else {
            e.putString("REMEMBER_USERID", "NO");
            System.out.println("click user no");
        }

        if (remember_password.isChecked()) {
            e.putString("REMEMBER_PASSWORD", "YES");
            System.out.println("click pass yes");
        } else {
            e.putString("REMEMBER_PASSWORD", "NO");
            System.out.println("click pass no");
        }
        e.apply();

        //=============================================


        //SQL LITE Login Checking===========================================

        KEY_VALUE_Database db = new KEY_VALUE_Database(this);
        System.out.println("test1");
        Cursor rows = db.execute("SELECT * FROM key_value_info_profile");

        System.out.println("test2");

//  while (rows.moveToLast()){
//      System.out.println("workinmg");
//     lastrow = rows.getString(0);
//      System.out.println("LAst row =="+lastrow);
//  }
//     while (rows.moveToFirst()){
//         firstrow =  rows.getString(0);
//         System.out.println("First row =="+firstrow);
//     }


        if (rows.getCount() != 0) {
            while (rows.moveToNext()) {

                keyy = rows.getString(0);
                System.out.println("main key" + keyy);
                String eventData = rows.getString(1);
                String[] fieldValues = eventData.split("___");

                if (fieldValues.length == 6) {
                    System.out.println("vvalue 6");
                    String name1 = fieldValues[0];
                    email1 = fieldValues[1];
                    String phone1 = fieldValues[2];
                    passs = fieldValues[3];
                    re_pass = fieldValues[4];

                    if (email1.equals(email) && re_pass.equals(password)) {
                        System.out.println("vvalue 7");
                        Intent i = new Intent(MainActivity.this, Home.class);

                        SharedPreferences sppp = this.getSharedPreferences("CURRENT_USER_INFO", MODE_PRIVATE);
                        // sppp.edit().clear().commit();
                        SharedPreferences.Editor ed = sppp.edit();
                        ed.putString("CURRENT_USER_E", email1);
                        ed.putString("Current_user_KEy", keyy);
                        ed.apply();
                        System.out.println("successfulling login SQL database");
                        System.out.println(keyy);
                        startActivity(i);
                        //======================================SQL Lite Checking End
                        clear = 2;
                        break;
                    } else {
                        System.out.println("vvalue 8");

                        if(rows.moveToLast()){
                             lastrow= rows.getString(0);
                        }

                        if (lastrow.equals(keyy)) {
                            System.out.println("last key succes" + lastrow);
                            //  if (keyy.equals(lastkey)){
                            FIRE();
                        } else {
                            System.out.println("vvalue 9");
                          //  break;
                        }


                    }
                    System.out.println("vvalue 10");
                    System.out.println("Row has been 0");


                    // }
                } else {
                    System.out.println("length = " + fieldValues.length);
                }


                if (clear == 2) {
                    break;
                }
            }
            db.close();

        } else if (rows.getCount() == 0) {
            System.out.println("Row has been null");
            FIRE();
        }

      /*    //Firebase Login===============================================
            System.out.println("Firebase login");
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        
                                        //redirect to user profile
                        startActivity(new Intent(MainActivity.this,Home.class));
                        //  String root = String.valueOf(databaseReference.child(email).getRoot());
                        //  System.out.println("PRint Roote = "+root);
                        progressBar.setVisibility(View.GONE);


                    }else{
                        Toast.makeText(MainActivity.this, " Failed to login!Please check your credentials", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }


                }
            });
            //Firebase Login===============================================END


        progressBar.setVisibility(View.GONE);

*/

    }

    private void FIRE() {
        String emaill = username.getText().toString().trim();
        String passwordd = pass.getText().toString().trim();

        System.out.println("HEre firebase login");
        //Firebase Login===============================================
        System.out.println("Firebase login");
        mAuth.signInWithEmailAndPassword(emaill, passwordd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {


                    //redirect to user profile
                    startActivity(new Intent(MainActivity.this, Home.class));
                    //  String root = String.valueOf(databaseReference.child(email).getRoot());
                    //  System.out.println("PRint Roote = "+root);
                    progressBar.setVisibility(View.GONE);


                } else {
                    Toast.makeText(MainActivity.this, " Failed to login!Please check your credentials", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }


            }
        });
        //Firebase Login===============================================END
    }


    private void SIGNUP() {

        progressBar.setVisibility(View.VISIBLE);

        // Bundle extras = getIntent().getExtras();
        // String TRAVELER_USER = extras.getString("TRAVELER").trim();
        Intent i = new Intent(MainActivity.this, Sign_UP.class);
        // i.putExtra("TRAVELER1", TRAVELER_USER);
        startActivity(i);
        progressBar.setVisibility(View.GONE);


    }


//hideing otherside touch

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

}