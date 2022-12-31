package edu.ewubd.travelbd119;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
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
    String uid;
    Handler mHandler;



    @SuppressLint("MissingInflatedId")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile);
        mHandler=new Handler();

        uploadList = new ArrayList<>();
        //databaseReference = FirebaseDatabase.getInstance().getReference("Traveler");


        profile_name = findViewById(R.id.userName_signUPP);
        profile_email = findViewById(R.id.email_signupp);
        profile_phn = findViewById(R.id.phone_signUPP);
        profile_proPic = findViewById(R.id.profilePic);

        SharedPreferences users= this.getSharedPreferences("TRAVELER", MODE_PRIVATE);
        String Check_Users = users.getString("TRAVELER", "");


     if(Check_Users.equals("TRA")){
         loadDataa();
         System.out.println("TRA");
     }else if(Check_Users.equals("MAN")){
         adminLoadData();
         System.out.println("MAN");

     }else{
         System.out.println("USER not Define");
     }


    }

    private void adminLoadData() {

//check current user========================================
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            System.out.println("User is signed in");
            String name = user.getDisplayName();
            String email = user.getEmail();
            uid = user.getUid();
            String phon = user.getPhoneNumber();
            System.out.println("name"+name);
            System.out.println("email = +"+email);
            System.out.println("uid"+uid);
            System.out.println("Phone"+phon);


        } else {
            System.out.println("not sign in");
        }

        //check current user========================================END

        SharedPreferences sppp = this.getSharedPreferences("CURRENT_USER_INFO", MODE_PRIVATE);
        String c_user = sppp.getString("CURRENT_USER_E", "");
        String c_key = sppp.getString("Current_user_KEy", "");
        System.out.println("c_key" + c_key);

        //Store Data in data base==========================
        System.out.println("test");
        if (!c_key.equals("")) {
            System.out.println("ckey is null");
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

//Thread======
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        // decode base64 string
                        byte[] bytes = Base64.decode(sImage, Base64.DEFAULT);
                        // Initialize bitmap
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        // set bitmap on imageView
                        profile_proPic.setImageBitmap(bitmap);

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                // mProgressBar.setProgress(currentProgressCount);
                            }
                        });


                    }
                }).start();//Thread=========


            }

        } else {

            //firebase profile=================================

            System.out.println("else===");
            final ArrayList<String> list = new ArrayList<>();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ADMIN").child(uid);

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
                        String sImage1 = list.get(4);
                        String  email1 = list.get(0);
                        String pass1 = list.get(1);
                        String   phone1 = list.get(2);
                        String repass1= list.get(3);
                        String   username1 = list.get(5);

                        profile_name.setText(username1);
                        profile_email.setText(email1);
                        profile_phn.setText(phone1);

//Thread=====
                        //    new Thread(new Runnable() {
                        //    @Override
                        //    public void run() {

                        // decode base64 string
                        byte[] bytes = Base64.decode(sImage1, Base64.DEFAULT);
                        Bitmap bitmap1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        profile_proPic.setImageBitmap(bitmap1);

                        //   mHandler.post(new Runnable() {
                        //     @Override
                        //      public void run() {
                        // mProgressBar.setProgress(currentProgressCount);
                        //     }
                        //     });


                        //         }
                        //    }).start();//Thread=============


                    }


                }

                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Profile.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                }
            });



        }

    }






    private void loadDataa() {

//check current user========================================
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            System.out.println("User is signed in");
            String name = user.getDisplayName();
            String email = user.getEmail();
              uid = user.getUid();
            String phon = user.getPhoneNumber();
            System.out.println("name"+name);
            System.out.println("email = +"+email);
            System.out.println("uid"+uid);
            System.out.println("Phone"+phon);

          /* final ArrayList<String> currentlist = new ArrayList<>();
            DatabaseReference r = FirebaseDatabase.getInstance().getReference("Traveler").child(uid);
            r.addValueEventListener(new ValueEventListener() {
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    currentlist.clear();

                                                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                                                        // String value = snapsho.getValue(String.class);
                                                        currentlist.add(dataSnapshot1.getValue().toString());
                                                    }
                                                    System.out.println("-"+currentlist.size());
                                                    for(int i=0 ; i<currentlist.size();i++){
                                                        // System.out.println(list.get(i));
                                                        String sImage1 = currentlist.get(0);
                                                        String  email1 = currentlist.get(1);
                                                        String pass1 = currentlist.get(2);
                                                        String   phone1 = currentlist.get(3);
                                                        String repass1= currentlist.get(4);
                                                        String   username1 = currentlist.get(5);

                                                        profile_name.setText(username1);
                                                        profile_email.setText(email1);
                                                        profile_phn.setText(phone1);
                                                        System.out.println(email1);
                                                        System.out.println(phone1);
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
*/
//================
        } else {
            System.out.println("not sign in");
        }

        //check current user========================================END

        SharedPreferences sppp = this.getSharedPreferences("CURRENT_USER_INFO", MODE_PRIVATE);
        String c_user = sppp.getString("CURRENT_USER_E", "");
        String c_key = sppp.getString("Current_user_KEy", "");
        System.out.println("c_key" + c_key);

        //Store Data in data base==========================
        System.out.println("test");
        if (!c_key.equals("")) {
            System.out.println("ckey is null");
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

//Thread======
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                // decode base64 string
                byte[] bytes = Base64.decode(sImage, Base64.DEFAULT);
                // Initialize bitmap
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                // set bitmap on imageView
                profile_proPic.setImageBitmap(bitmap);

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                // mProgressBar.setProgress(currentProgressCount);
                            }
                        });


                    }
                }).start();//Thread=========


            }

        } else {

            //firebase profile=================================

            System.out.println("else===");
final ArrayList<String> list = new ArrayList<>();
DatabaseReference reference = FirebaseDatabase.getInstance().getReference("USER").child(uid);

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

//Thread=====
                //    new Thread(new Runnable() {
                    //    @Override
                    //    public void run() {

                            // decode base64 string
                    byte[] bytes = Base64.decode(sImage1, Base64.DEFAULT);
                    Bitmap bitmap1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    profile_proPic.setImageBitmap(bitmap1);

                         //   mHandler.post(new Runnable() {
                           //     @Override
                          //      public void run() {
                                    // mProgressBar.setProgress(currentProgressCount);
                           //     }
                       //     });


               //         }
                //    }).start();//Thread=============


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


