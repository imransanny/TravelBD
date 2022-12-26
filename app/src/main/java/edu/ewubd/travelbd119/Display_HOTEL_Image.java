package edu.ewubd.travelbd119;



        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.annotation.SuppressLint;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ProgressBar;
        import android.widget.Toast;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;
        import java.util.List;

public class Display_HOTEL_Image extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Hotel_img_Adapter myAdapter;
    private List<Hotels_Upload> uploadList_hotels;
    private ProgressBar progressBar;
    DatabaseReference databaseReference;



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
        uploadList_hotels = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Upload_HOTEL_Image");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//database ttheke data anbo

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Hotels_Upload upload = dataSnapshot1.getValue(Hotels_Upload.class);
                    uploadList_hotels.add(upload);
                }


                myAdapter = new Hotel_img_Adapter(Display_HOTEL_Image.this, uploadList_hotels);
                recyclerView.setAdapter(myAdapter);




                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Error : "+error.getMessage(),Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });



    }
}