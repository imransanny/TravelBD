package edu.ewubd.travelbd119;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {

    TextView signup, login, forgetpass;
    ProgressBar progressBar;
    EditText username, pass;
    CheckBox remember_user, remember_password;
    private FirebaseAuth mAuth;




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


        if(s2.equals("YES")){

                    Intent i = new Intent(MainActivity.this, Home.class);

                    startActivity(i);



                    // finish()

        }



        //=======================================================================





        signup = findViewById(R.id.signup_main_id);
        signup.setOnClickListener(v->SIGNUP());

        forgetpass = findViewById(R.id.forget_main);

        pass = findViewById(R.id.pass_main);
        progressBar = findViewById(R.id.Progreess_mainactivity);
        login.setOnClickListener(v->Login());
        remember_user = findViewById(R.id.checkbox_remember_userID);
        remember_password = findViewById(R.id.checkbox_remember_password);







    }

    private void Login() {
        String email = username.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if(email.isEmpty()){
            username.setError("Email is required!");
            username.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            username.setError("Please enter a valid email!");
            username.requestFocus();
            return;
        }
        if(password.isEmpty()){
            pass.setError("Password is required");
            pass.requestFocus();
            return;
        }
        if(password.length()<6){
            pass.setError("Min password length is 6 characters!");
            pass.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);



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

        //Shared Preference Store Checkbox
        //============================================================

        SharedPreferences sp = this.getSharedPreferences("Remember_login_Sharedpref", MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();

        if(remember_user.isChecked()){
            e.putString("REMEMBER_USERID", "YES");
            System.out.println("click user yes");
        }else{
            e.putString("REMEMBER_USERID", "NO");
            System.out.println("click user no");
        }

        if(remember_password.isChecked()){
            e.putString("REMEMBER_PASSWORD", "YES");
            System.out.println("click pass yes");
        }else{
            e.putString("REMEMBER_PASSWORD", "NO");
            System.out.println("click pass no");
        }
        e.apply();

        //=============================================

        progressBar.setVisibility(View.GONE);



    }





    private void SIGNUP() {

        progressBar.setVisibility(View.VISIBLE);
        Bundle extras = getIntent().getExtras();
        String TRAVELER_USER = extras.getString("TRAVELER").trim();

        Intent i = new Intent(MainActivity.this, Sign_UP.class);
        i.putExtra("TRAVELER1", TRAVELER_USER);
        startActivity(i);
        progressBar.setVisibility(View.GONE);
    }

}