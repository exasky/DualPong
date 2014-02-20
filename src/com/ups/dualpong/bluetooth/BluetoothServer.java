package com.ups.dualpong.bluetooth;

/**
 * Created by julien on 20/02/14.
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.ups.dualpong.game.Ball;

public class BluetoothServer extends Thread {
	private final BluetoothServerSocket blueServerSocket;
	private BluetoothAdapter blueAdapter;
	private static String APPNAME = "BTAPPDELAMORT";

	public BluetoothServer(BluetoothAdapter blueAdapter_) {
		this.blueAdapter = blueAdapter_;
		//
		BluetoothServerSocket tmp = null;
		try {

			tmp = blueAdapter.listenUsingRfcommWithServiceRecord(APPNAME,
					UUID.fromString("c065af87-b800-4bb3-a932-c4c130f2a50d"));
		} catch (IOException e) {
		}
		blueServerSocket = tmp;
	}

	@Override
	public void run() {
		BluetoothSocket blueSocket = null;
		while (true) {
			try {
				blueSocket = blueServerSocket.accept();
			} catch (IOException e) {
				break;
			}

			if (blueSocket != null) {
				manageConnectedSocket(blueSocket);
				try {
					blueServerSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	// TODO Recuperation de la bonne balle ;)
	private void manageConnectedSocket(BluetoothSocket blueSocket) {
		Log.i("BTserver", "manageConnectedSocked");
		try {
			InputStream in = blueSocket.getInputStream();
			byte[] tmp = new byte[0];
			in.read(tmp);
			Ball b = new Ball();
			b.unserialize(tmp, 960);

			Log.d("BTserver", "received : " + new String(tmp, "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}

	public void cancel() {
		try {
			blueServerSocket.close();
		} catch (IOException e) {
		}
	}
}