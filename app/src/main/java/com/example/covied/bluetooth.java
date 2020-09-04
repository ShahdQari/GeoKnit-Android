package com.example.covied;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public class bluetooth extends AppCompatActivity implements Serializable {

    private CheckBox Enable;
    private BluetoothAdapter bluetoothAdapter;
    private TextView bt ;
    ArrayAdapter<String> mBTArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_layout);

        BottomNavigationView bottomNavigationView = findViewById(R.id.NavigationView);
        bottomNavigationView.setSelectedItemId(R.id.profile);
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

        Enable = findViewById(R.id.Enable);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bt = findViewById(R.id.bt);

        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Blutooth not support", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (bluetoothAdapter.isEnabled()) {
           Enable.setChecked(true);
        }


        Enable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    bluetoothAdapter.disable();
                    Toast.makeText(bluetooth.this, "turend off", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intenton = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intenton, 0);
                    Toast.makeText(bluetooth.this, "turend on", Toast.LENGTH_SHORT).show();
                }
            }
        });

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);
        bluetoothAdapter.startDiscovery();
        mBTArrayAdapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        bt.setText(readfile(filename));

    }


    BluetoothDevice device;
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //Finding devices
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    // Get the BluetoothDevice object from the Intent
                    device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
                    mBTArrayAdapter.add(device.getAddress() + "\n" + currentDateTimeString);
                    mBTArrayAdapter.notifyDataSetChanged();
                    saveConnectionState(device.getAddress() + "\n" + currentDateTimeString);
                }
        }
    };


    public static final String filename = "bt.txt";

    String currentDateTimeString;

    public  void saveConnectionState(String Text){
        try {
            FileOutputStream fos = openFileOutput(filename , Context.MODE_PRIVATE);
            fos.write(Text.getBytes());
            fos.getFD().sync();
        }catch (IOException e) {
        e.printStackTrace();

            }
        }

    public String readfile(String file){
        String text = "";
        try {
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            text = new String(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text ;
    }

    }




