package com.dteixeira.sunrisealarm;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    CheckBox mCheckBox;
    CheckBox mCheckBoxBT;
    ArrayAdapter mArrayAdapter;
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = (Context)getApplicationContext();


        //BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        int duration = Toast.LENGTH_LONG;

        String status;
        // Check BT support and enable if supported
        if (mBluetoothAdapter == null){
            // Device does not support BT
            status = "Your device does not support Bluetooth";
        }
        else {
            String myAdress = mBluetoothAdapter.getAddress();
            String myName = mBluetoothAdapter.getName();
            status = myName + ":" + myAdress;
        }

        final Toast mToast = Toast.makeText(context, status, duration);
        mToast.show();


        mCheckBox = (CheckBox)findViewById(R.id.checkBox);
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckBox.isChecked()){
                    System.out.println("Checked!");
                }
                else{

                }
            }
        });

        mCheckBoxBT = (CheckBox)findViewById(R.id.checkBox2);
        mCheckBoxBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckBoxBT.isChecked()){
                    mBluetoothAdapter.enable();
                    mToast.show();
                }
                if (!mCheckBoxBT.isChecked()){
                    mBluetoothAdapter.disable();
                }
            }
        });

    }

    protected void onDestroy(){
        super.onDestroy();
        mBluetoothAdapter.disable();

    }



}
