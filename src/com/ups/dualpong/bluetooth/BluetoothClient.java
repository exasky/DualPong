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

import com.ups.dualpong.game.Ball;

public class BluetoothClient extends Thread {
	private final BluetoothSocket blueSocket;
	private final BluetoothDevice blueDevice;
	private BluetoothAdapter blueAdapter;

	public BluetoothClient(BluetoothDevice device, BluetoothAdapter blueAdapter_) {
		// On utilise un objet temporaire car blueSocket et blueDevice sont
		// "final"
		BluetoothSocket tmp = null;
		blueDevice = device;
		blueAdapter = blueAdapter_;

		// On r��cup��re un objet BluetoothSocket gr��ce �� l'objet
		// BluetoothDevice
		try {
			// MON_UUID est l'UUID (comprenez identifiant serveur) de
			// l'application. Cette valeur est n��cessaire c��t�� serveur
			// ��galement !
			tmp = blueDevice.createRfcommSocketToServiceRecord(UUID
					.fromString("c065af87-b800-4bb3-a932-c4c130f2a50d"));
		} catch (IOException e) {
		}
		blueSocket = tmp;
	}

	@Override
	public void run() {
		Log.d("thread client", "run started");
		// On annule la d��couverte des p��riph��riques (inutile puisqu'on est
		// en train d'essayer de se connecter)
		blueAdapter.cancelDiscovery();

		try {
			// On se connecte. Cet appel est bloquant jusqu'�� la r��ussite ou
			// la lev��e d'une erreur
			blueSocket.connect();
		} catch (IOException connectException) {
			// Impossible de se connecter, on ferme la socket et on tue le
			// thread
			try {
				blueSocket.close();
			} catch (IOException closeException) {
			}
			return;
		}

		// Utilisez la connexion (dans un thread s��par��) pour faire ce que
		// vous voulez
		manageConnectedSocket(blueSocket);
	}

	// Annule toute connexion en cours et tue le thread
	public void cancel() {
		try {
			blueSocket.close();
		} catch (IOException e) {
		}
	}

	public void manageConnectedSocket(BluetoothSocket blueSocket) {
		Log.i("BTClient", "ManageConnectedSocket");
		try {
			OutputStream out = blueSocket.getOutputStream();
			Ball ball = new Ball(5, 10, 20, 30);

			byte[] tmp = new String("test").getBytes("UTF-8");
			out.write(tmp);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}
}