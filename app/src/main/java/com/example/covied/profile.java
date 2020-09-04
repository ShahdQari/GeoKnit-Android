package com.example.covied;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class profile extends AppCompatActivity {
    private TextView nameTV, emailTV, test;
    private ImageView photoIV;
    private Button persone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_design);

        BottomNavigationView bottomNavigationView = findViewById(R.id.NavigationView);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile:
                        return true;
                    case R.id.test:
                        startActivity(new Intent(getApplicationContext(), test.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.map:
                        startActivity(new Intent(getApplicationContext(), map.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.permit:
                        startActivity(new Intent(getApplicationContext(), permit.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.hospatil:
                        startActivity(new Intent(getApplicationContext(), hospital.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        nameTV = (TextView) findViewById(R.id.name);
        emailTV = (TextView) findViewById(R.id.email);
        photoIV = (ImageView) findViewById(R.id.photo);
        test = (TextView) findViewById(R.id.test12);
        persone = (Button) findViewById(R.id.persone);
        persone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, bluetooth.class);
                startActivity(intent);
            }
        });


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();

            nameTV.setText("Name: " + personName);
            emailTV.setText("Email: " + personEmail);
            Glide.with(this).load(personPhoto).into(photoIV);
        }

        SharedPreferences sh = getSharedPreferences("test", MODE_PRIVATE);
        test.setText("case: " + sh.getString("test" , ""));
    }
}



