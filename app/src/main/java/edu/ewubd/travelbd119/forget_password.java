package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class forget_password extends AppCompatActivity {
    RadioButton phone, email;
    EditText code1, code2, code3, code4, new_password, confirm_password;
    Button submit;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.forgetpass);

        phone = findViewById(R.id.resetpass_phone);
        email = findViewById(R.id.resetpass_email);

        code1 = findViewById(R.id.code1);
        code2 = findViewById(R.id.code2);
        code3 = findViewById(R.id.code3);
        code4 = findViewById(R.id.code4);
        new_password = findViewById(R.id.newpass);
        confirm_password = findViewById(R.id.confirmpass);

        submit = findViewById(R.id.btn_submitpass);


    }
}
