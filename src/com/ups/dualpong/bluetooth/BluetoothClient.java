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
	private int viewWidth;

	public BluetoothClient(BluetoothDevice device,
			BluetoothAdapter blueAdapter_, int viewWidth) {
		BluetoothSocket tmp = null;
		blueDevice = device;
		blueAdapter = blueAdapter_;
		this.viewWidth = viewWidth;

		try {
			tmp = blueDevice.createRfcommSocketToServiceRecord(UUID
					.fromString("c065af87-b800-4bb3-a932-c4c130f2a50d"));
		} catch (IOException e) {
		}
		blueSocket = tmp;
	}

	@Override
	public void run() {
		Log.d("thread client", "run started");
		blueAdapter.cancelDiscovery();

		try {
			blueSocket.connect();
		} catch (IOException connectException) {
			try {
				blueSocket.close();
			} catch (IOException closeException) {
			}
			return;
		}
		manageConnectedSocket(blueSocket);
	}

	public void cancel() {
		try {
			blueSocket.close();
		} catch (IOException e) {
		}
	}

	// TODO Envoyer la bonne ball ;)
	public void manageConnectedSocket(BluetoothSocket blueSocket) {
		Log.i("BTClient", "ManageConnectedSocket");
		try {
			OutputStream out = blueSocket.getOutputStream();
			Ball ball = new Ball(5, 10, 20, 30);

			byte[] tmp = ball.serialize();
			int x = tmp[0];
			tmp[0] = Float.valueOf((x * 100) / this.viewWidth).byteValue();

			out.write(tmp);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}
}