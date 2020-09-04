package com.example.covied;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class accept_permit extends AppCompatActivity {
    TextView permit_ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accept_permit);

        BottomNavigationView bottomNavigationView = findViewById(R.id.NavigationView);
        bottomNavigationView.setSelectedItemId(R.id.permit);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), profile.class));
                        overridePendingTransition(0, 0);
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

        permit_ac = findViewById(R.id.permit_ac);

        SharedPreferences sh = getSharedPreferences("permit", MODE_PRIVATE);

        permit_ac.setText("Date: " + sh.getString("Date", "")
                + "\nTime from: " +  sh.getString("Time from", "")
                + "\nTime to: " + sh.getString("Time to", "")
                + "\ndescription: " + sh.getString("description", ""));
    }
}
