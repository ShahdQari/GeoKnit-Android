package com.example.covied;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class signin extends AppCompatActivity {
    EditText Name;
    EditText Phone;
    EditText Email;
    EditText pass1;
    EditText pass2;
    Button SginUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_design);

        Name = findViewById(R.id.Name);
        Phone = findViewById(R.id.phoneNum);
        Email = findViewById(R.id.Email);
        pass1= findViewById(R.id.pass1);
        pass2= findViewById(R.id.pass2);
        SginUp= findViewById(R.id.SginUp);

        SginUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
            }
        });
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(Name)) {
            Toast t = Toast.makeText(this, "You must enter your name to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmail(Name) == false) {
            Name.setError("Enter your Name!");
        }

        if (isEmail(Email) == false) {
            Email.setError("Enter valid email!");
        }

        if (isEmail(Phone) == false) {
            Phone.setError("Enter your Phone Number!");
        }

        if (isEmail(pass1) == false) {
            pass1.setError("Enter your password!");
        }
        if (isEmail(pass2) == false) {
            pass2.setError("Enter your rpassword!");
        }
    }

}

