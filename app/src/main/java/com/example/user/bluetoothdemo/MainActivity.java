package com.example.user.bluetoothdemo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends Activity {

    BluetoothAdapter BA;

    public void turnBluetoothOff(View view){

       BA.disable();
       if(BA.isEnabled()){
           Toast.makeText(getApplicationContext(),"Bluetooth could not be turn off",Toast.LENGTH_SHORT).show();
       }else{
           Toast.makeText(getApplicationContext(),"Bluetooth turned off",Toast.LENGTH_SHORT).show();
       }
    }

    public void findDiscoverableDevice(View view){

        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(i);
    }

    public void pairedDevice(View view){

        Set<BluetoothDevice> pairs = BA.getBondedDevices();

        ArrayList pairedDeviceArrayList = new ArrayList();

        for (BluetoothDevice bluetoothDevice : pairs){

            pairedDeviceArrayList.add(bluetoothDevice.getName());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,pairedDeviceArrayList);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(BA.isEnabled()){

            Toast.makeText(getApplicationContext(),"Bluetooth is on",Toast.LENGTH_SHORT).show();

        }else{

            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(i);
            if (BA.isEnabled()) {
                Toast.makeText(getApplicationContext(), "Bluetooth turned on", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
