package com.example.covied;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class test extends AppCompatActivity {

    private CheckBox fever , cough , tired, pain
            , congestion,headache , conjunctivitis ,
            sore_throat , diarrhea , loss , rash , change;

    private Button Accept ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        fever = findViewById(R.id.fever);
        cough = findViewById(R.id.cough);
        tired = findViewById(R.id.tired);
        pain = findViewById(R.id.pain);
        congestion = findViewById(R.id.congestion);
        headache = findViewById(R.id.headache);
        conjunctivitis = findViewById(R.id.conjunctivitis);
        sore_throat = findViewById(R.id.sore_throat);
        diarrhea = findViewById(R.id.diarrhea);
        loss = findViewById(R.id.loss);
        rash = findViewById(R.id.rash);
        change = findViewById(R.id.change);
        Accept = findViewById(R.id.Accept);

        BottomNavigationView bottomNavigationView = findViewById(R.id.NavigationView);
        bottomNavigationView.setSelectedItemId(R.id.test);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.test:
                        return true;
                    case R.id.map:
                        startActivity(new Intent(getApplicationContext(), map.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.permit:
                        startActivity(new Intent(getApplicationContext(), permit.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.hospatil:
                        startActivity(new Intent(getApplicationContext(), hospital.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fever.isChecked() && cough.isChecked() && tired.isChecked()){
                    if (pain.isChecked() || congestion.isChecked() || headache.isChecked()
                            ||conjunctivitis.isChecked() || sore_throat.isChecked()
                            || diarrhea.isChecked() || loss.isChecked()
                            || rash.isChecked() || change.isChecked()){
                        alert("you are infected" , "yes");
                    }

                }
                else if(fever.isChecked() || cough.isChecked() || tired.isChecked()){
                    if (pain.isChecked() || congestion.isChecked() || headache.isChecked()
                            ||conjunctivitis.isChecked() || sore_throat.isChecked()
                            || diarrhea.isChecked() || loss.isChecked()
                            || rash.isChecked() || change.isChecked()){
                        alert("you may be infected" , "Maybe");
                    }
                }
                else if (pain.isChecked() || congestion.isChecked() || headache.isChecked()
                        ||conjunctivitis.isChecked() || sore_throat.isChecked()
                        || diarrhea.isChecked() || loss.isChecked()
                        || rash.isChecked() || change.isChecked()){
                    alert("you are not infected" , "No");
                }
            }
        });
    }

    public void alert(final String Message , final String ca){
        final AlertDialog.Builder builder = new AlertDialog.Builder(test.this);
        builder.setTitle("test result");
        builder.setMessage(Message);
        builder.setCancelable(true);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(test.this , profile.class);
                Bundle bundle = new Bundle();
                bundle.putString("case", ca);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}