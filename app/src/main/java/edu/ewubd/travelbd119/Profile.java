package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {

    ImageView proPic;
    TextView name, phn, nid, email;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile);

        name = findViewById(R.id.userName);
        phn = findViewById(R.id.profilePhone);
        nid = findViewById(R.id.profileNID);
        email = findViewById(R.id.profileEmail);

        proPic = findViewById(R.id.profilePic);


    }
}
