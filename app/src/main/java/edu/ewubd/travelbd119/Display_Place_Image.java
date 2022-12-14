package edu.ewubd.travelbd119;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Display_Place_Image extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Place_img_Adapter myAdapter;
    private List<Upload> uploadList;
    private ProgressBar progressBar;
    DatabaseReference databaseReference;
    Button back;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_place_image);

        recyclerView = findViewById(R.id.recyclerview_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = findViewById(R.id.progress_iddd);
        progressBar.setVisibility(View.VISIBLE);
        uploadList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Upload_Place_Image");


        //Thread
        new Thread(new Runnable() {
            public void run() {

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//database ttheke data anbo

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Upload upload = dataSnapshot1.getValue(Upload.class);
                            uploadList.add(upload);
                        }


                        myAdapter = new Place_img_Adapter(Display_Place_Image.this, uploadList);
                        recyclerView.setAdapter(myAdapter);


                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Error : " + error.getMessage(), Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });


            }

        }).start();
    }


}



/*    public void onClick(View v) {
        new Thread(new Runnable() {
            public void run() {
                // a potentially time consuming task
                final Bitmap bitmap =
                        processBitMap("image.png");
                imageView.post(new Runnable() {
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();

 */
