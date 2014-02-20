package com.ups.dualpong;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ups.dualpong.bluetooth.BluetoothServer;
import com.ups.dualpong.bluetooth.BluetoothClient;

import java.util.Set;

/**
 * Created by julien on 20/02/14.
 */
public class JoinActivity extends Activity{
    private BluetoothClient BTclient;
    private BluetoothDevice device ;
    private BluetoothAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = BluetoothAdapter.getDefaultAdapter();

        if (!adapter.isEnabled()) {
            Log.i("JoinActivity", "Enabling BTAdapter");
            adapter.enable();
        }
//On présume que les trucs ont été pairés avant
        Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();
        String s = "";
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                //Affichage des devices pairés
                s+="NAME:"+device.getName() + "\n" + "adress:"+device.getAddress()+"\n";
                //Je cherche celui dont j'ai besoin (en fait les 2 tel avec lesquels j'ai testé
                if (device.getName().equals("HTC Dada")) {
                    System.out.println("HTDADA FOUND");
//                    this.htcDada=device;
                }
                if (device.getName().equals("HTC Desire C")) {
//                    this.htcC=device;
                    System.out.println("HTCC FOUND");
                }

            }
        }
    }

}
