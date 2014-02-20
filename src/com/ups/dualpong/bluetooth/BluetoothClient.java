package com.ups.dualpong.bluetooth;

/**
 * Created by julien on 20/02/14.
 */
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class BluetoothClient extends Thread {
    private final BluetoothSocket blueSocket;
    private final BluetoothDevice blueDevice;
    private BluetoothAdapter blueAdapter;

    public BluetoothClient(BluetoothDevice device, BluetoothAdapter blueAdapter_) {
        // On utilise un objet temporaire car blueSocket et blueDevice sont "final"
        BluetoothSocket tmp = null;
        blueDevice = device;
        blueAdapter=blueAdapter_;

        // On récupère un objet BluetoothSocket grâce à l'objet BluetoothDevice
        try {
            // MON_UUID est l'UUID (comprenez identifiant serveur) de l'application. Cette valeur est nécessaire côté serveur également !
            tmp = blueDevice.createRfcommSocketToServiceRecord(UUID.fromString("c065af87-b800-4bb3-a932-c4c130f2a50d"));
        } catch (IOException e) { }
        blueSocket = tmp;
    }

    public void run() {
        Log.d("thread client","run started");
        // On annule la découverte des périphériques (inutile puisqu'on est en train d'essayer de se connecter)
        blueAdapter.cancelDiscovery();

        try {
            // On se connecte. Cet appel est bloquant jusqu'à la réussite ou la levée d'une erreur
            blueSocket.connect();
        } catch (IOException connectException) {
            // Impossible de se connecter, on ferme la socket et on tue le thread
            try {
                blueSocket.close();
            } catch (IOException closeException) { }
            return;
        }

        // Utilisez la connexion (dans un thread séparé) pour faire ce que vous voulez
        manageConnectedSocket(blueSocket);
    }

    // Annule toute connexion en cours et tue le thread
    public void cancel() {
        try {
            blueSocket.close();
        } catch (IOException e) { }
    }

    public void manageConnectedSocket(BluetoothSocket blueSocket) {
        Log.i("BTClient", "ManageConnectedSocket");
        try {
            OutputStream out = blueSocket.getOutputStream();
            byte[] tmp = new String("test").getBytes("UTF-8");
            out.write( tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}