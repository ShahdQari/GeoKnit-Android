package com.example.covied;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;

public class bluetooth extends AppCompatActivity implements Serializable {

    private CheckBox Enable;
    private ListView devicesListView;
    private BluetoothAdapter bluetoothAdapter;
    private ArrayAdapter<String> arrayAdapter;
    private TextView bt ;


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
        //devicesListView = findViewById(R.id.devicesListView);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bt = findViewById(R.id.bt);
        bt.setText(readfile(filename));


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



    }


    BluetoothDevice device;
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //Finding devices
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    // Get the BluetoothDevice object from the Intent
                    device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                }
                currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
                saveConnectionState(device + "\n" + currentDateTimeString + "\n\n");
        }
    };


    public static final String filename = "bt.txt";

    String currentDateTimeString;
    ArrayList<String> lines = new ArrayList<String>();

    public  void saveConnectionState(String text){
        try {
            FileOutputStream fos = openFileOutput(filename , Context.MODE_PRIVATE);
            fos.write(text.getBytes());
        }catch (IOException e) {
        e.printStackTrace();

            }
        }


    /*public void loadConnectionState(Context cxt) throws IOException {
        FileInputStream fis = cxt.getApplicationContext().openFileInput(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        String line;

        while ((line = ois.readUTF()) != null) {
            lines.add(line);
        }
        bt.setText(lines);
        //arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lines);
        //devicesListView.setAdapter(arrayAdapter);
        ois.close();

    }*/

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




