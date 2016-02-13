package com.dteixeira.sunrisealarm;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
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

public class MainActivity extends AppCompatActivity {

    CheckBox mCheckBox;
    ArrayAdapter mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = (Context)getApplicationContext();


        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        CharSequence text = "Your device does not support Bluetooth";
        int duration = Toast.LENGTH_SHORT;
        Toast mToast = Toast.makeText(context, text, duration);

        // Check BT support and enable if supported
        if (mBluetoothAdapter == null){
            // Device does not support BT
            mToast.show();
        }
        else {
            mBluetoothAdapter.enable();
        }

        // Create a BroadcastReceiver for ACTION_FOUND
        final BroadcastReceiver mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                // When discovery finds a device
                if (BluetoothDevice.ACTION_FOUND.equals(action)){
                    // Get the BluetoothDevice object from the intent
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    // Add the name and address to an array adapter to show in a ListView
                    mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                }
            }
        };

        // Register the BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter); // Unregister during onDestroy

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

    }

    protected void onDestroy(){
        super.onDestroy();

    }

}
