package com.example.covied;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class permit extends AppCompatActivity {

    private static final String TAG = "permit";
    private EditText displayDate , Time1 , Time2 , description;
    private TextView smsCountTxt;
    private Button Accept;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    int T1Hour , T1Minute , T2Hour , T2Minute;
    Bundle bundle;
    int pendingSMSCount = 10;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permit);

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
                        return true;
                    case R.id.hospatil:
                        startActivity(new Intent(getApplicationContext(), hospital.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        displayDate = (EditText) findViewById(R.id.editTextDate);
        Time1 = (EditText) findViewById(R.id.editTextTime2);
        Time2 = (EditText) findViewById(R.id.editTextTime3);
        description = (EditText) findViewById(R.id.description);
        Accept = (Button) findViewById(R.id.Accept);



        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        permit.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy" + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                displayDate.setText(date);
            }
        };

        time(Time1 , T1Hour , T1Minute);
        time(Time2 , T2Hour , T2Minute);


        bundle = new Bundle();
        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String describtion1 = description.getText().toString();
                    String Time0 = Time1.getText().toString();
                    String Time00 = Time2.getText().toString();
                    String displayDate1 = displayDate.getText().toString();
                    bundle.putString("Date", displayDate1);
                    bundle.putString("Time from", Time0);
                    bundle.putString("Time to", Time00);
                    bundle.putString("describtion", describtion1);
                    Intent intent = new Intent(permit.this , accept_permit.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
            }
        });

    }

    public void time(final TextView textView , final int hour , final int minute) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        permit.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hour, int minute) {
                                hour = hour;
                                minute = minute;
                                String time = hour + ":" + minute;
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = simpleDateFormat.parse(time);
                                    SimpleDateFormat sdf = new SimpleDateFormat(
                                            "HH:mm aa"
                                    );
                                    textView.setText(sdf.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(hour, minute);
                timePickerDialog.show();
            }
        });


    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.counter, menu);

        final MenuItem menuItem = menu.findItem(R.id.counter);

        View actionView = MenuItemCompat.getActionView(menuItem);
        smsCountTxt = (TextView) actionView.findViewById(R.id.notification_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.counter: {

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupBadge() {

        if (smsCountTxt != null) {
            if (pendingSMSCount == 0) {
                if (smsCountTxt.getVisibility() != View.GONE) {
                    smsCountTxt.setVisibility(View.GONE);
                }
            } else {
                smsCountTxt.setText(String.valueOf(Math.min(pendingSMSCount, 99)));
                if (smsCountTxt.getVisibility() != View.VISIBLE) {
                    smsCountTxt.setVisibility(View.VISIBLE);
                }
            }
        }
    }*/
}
