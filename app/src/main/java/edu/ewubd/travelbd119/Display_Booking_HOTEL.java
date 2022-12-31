package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Display_Booking_HOTEL extends AppCompatActivity {
    ListView lvenenvts;
    ArrayList<BOOKING> booking_list;
    private List<BOOKING> uploadList;
    CUSTOM_BOOKING_ADAPTER adapter;
    ArrayList<String> obj = new ArrayList<>();
    String uid;
    ArrayList<BOOKING> events;



    @SuppressLint("MissingInflatedId")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.booking_list);


        uploadList = new ArrayList<>();

        lvenenvts = findViewById(R.id.booking_lv_id);
        loadDataFirebase();





    }

    private void loadDataFirebase() {
        events = new ArrayList<>();

        SharedPreferences user_i = this.getSharedPreferences("USER_UID", MODE_PRIVATE);
        String uid_u = user_i.getString("Share_pref_UID", "");

        //firebase profile=================================

        System.out.println("else===");
        final ArrayList<String> list = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("BooKing_Hotel").child(uid_u);

        reference.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    list.add(dataSnapshot1.getValue().toString());

                    System.out.println("-"+list.size());

                }


                for(int i=0 ; i<list.size();i++){
                    // System.out.println(list.get(i));
                    String hotel_name = list.get(3);
                    String  name = list.get(4);
                    String phone = list.get(5);
                    String   email = list.get(2);
                    String address= list.get(0);
                    String   date = list.get(1);
                    System.out.println(name);
                    BOOKING upload = new BOOKING(hotel_name,name,phone,email,address,date);
                    events.add(upload);
                }


            }

            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Display_Booking_HOTEL.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });


        System.out.println(events.size());
        adapter = new CUSTOM_BOOKING_ADAPTER(this, events);
        lvenenvts.setAdapter(adapter);



    }




}





