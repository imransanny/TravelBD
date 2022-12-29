package edu.ewubd.travelbd119;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.storage.FirebaseStorage;



import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {


    ImageView profile_proPic;
    TextView profile_name, profile_email, profile_phn;
    ArrayList<Upload> events;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    private List<User_info> uploadList;
    String KeyOneTime  ="";
    String key;
    FirebaseUser userrr;




    @SuppressLint("MissingInflatedId")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile);

        uploadList = new ArrayList<>();
        //databaseReference = FirebaseDatabase.getInstance().getReference("Traveler");


        profile_name = findViewById(R.id.userName_signUPP);
        profile_email = findViewById(R.id.email_signupp);
        profile_phn = findViewById(R.id.phone_signUPP);
        profile_proPic = findViewById(R.id.profilePic);


/*
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            System.out.println("User is signed in");
            String name = user.getDisplayName();
            String email = user.getEmail();
            String uid = user.getUid();


        } else {
            System.out.println("not sign in");
        }

        */
       loadDataa();



    }



    private void loadDataa() {


        SharedPreferences sppp = this.getSharedPreferences("CURRENT_USER_INFO", MODE_PRIVATE);
        String c_user = sppp.getString("CURRENT_USER_E", "");
        String c_key = sppp.getString("Current_user_KEy", "");
        System.out.println("c_key" + c_key);

        //Store Data in data base==========================
        System.out.println("test");
        if (!c_key.equals(null)) {
            KEY_VALUE_Database db = new KEY_VALUE_Database(this);
            String v = db.getValueByKey(c_key);
            String[] fieldValues = v.split("___");
            for (int i = 0; i < fieldValues.length; i++) {
                //System.out.println(fieldValues[i]);
                String name = fieldValues[0];
                String phone = fieldValues[2];
                String pass = fieldValues[3];
                String re_pass = fieldValues[4];
                String sImage = fieldValues[5];
                String email = fieldValues[1];

                profile_name.setText(name);
                profile_email.setText(email);
                profile_phn.setText(phone);

                // decode base64 string
                byte[] bytes = Base64.decode(sImage, Base64.DEFAULT);
                // Initialize bitmap
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                // set bitmap on imageView
                profile_proPic.setImageBitmap(bitmap);

            }

        } else {


            //firebase profile=================================

            System.out.println("else===");
final ArrayList<String> list = new ArrayList<>();
DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Traveler").child("01636454867");

        reference.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

               for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                  // String value = snapsho.getValue(String.class);
                   list.add(dataSnapshot1.getValue().toString());
               }
                System.out.println("-"+list.size());
                for(int i=0 ; i<list.size();i++){
                   // System.out.println(list.get(i));
                   String sImage1 = list.get(0);
                    String  email1 = list.get(1);
                    String pass1 = list.get(2);
                    String   phone1 = list.get(3);
                    String repass1= list.get(4);
                    String   username1 = list.get(5);

                    profile_name.setText(username1);
                    profile_email.setText(email1);
                    profile_phn.setText(phone1);

                    // decode base64 string
                    byte[] bytes = Base64.decode(sImage1, Base64.DEFAULT);
                    Bitmap bitmap1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    profile_proPic.setImageBitmap(bitmap1);

                }


            }

            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });



           }




}
//==============================
    }


