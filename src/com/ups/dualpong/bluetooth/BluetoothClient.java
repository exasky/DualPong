package com.ups.dualpong.bluetooth;

/**
 * Created by julien on 20/02/14.
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;

import com.ups.dualpong.CreateActivity;
import com.ups.dualpong.GameActivity;
import com.ups.dualpong.JoinActivity;
import com.ups.dualpong.game.Ball;

public class BluetoothClient extends Thread {
	private final BluetoothSocket blueSocket;
	private final BluetoothDevice blueDevice;
	private BluetoothAdapter blueAdapter;
	private int viewWidth;
    private JoinActivity joinActivity;

    private static BluetoothClient instance = null;
    public static BluetoothClient getInstance() {return instance;    }


	public BluetoothClient(BluetoothDevice device,
			BluetoothAdapter blueAdapter_,JoinActivity act) {
        joinActivity = act;
		BluetoothSocket tmp = null;
		blueDevice = device;
		blueAdapter = blueAdapter_;

		try {
			tmp = blueDevice.createRfcommSocketToServiceRecord(UUID.fromString("c065af87-b800-4bb3-a932-c4c130f2a50d"));
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
            instance = null;
			blueSocket.close();
            this.destroy();
		} catch (IOException e) {
		}
	}

	public void manageConnectedSocket(BluetoothSocket blueSocket) {
		Log.i("BTClient", "ManageConnectedSocket");
		try {
            //launch game
            Intent intent = new Intent(joinActivity, GameActivity.class);
            joinActivity.startActivity(intent);

            while (true){
                try {
                    this.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}





    public void sendBall(Ball b){
        byte[] tmp = b.serialize();
        try {
            OutputStream out = blueSocket.getOutputStream();
            out.write(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Ball receiveBall(int screenWidth){
        try {
            InputStream in = blueSocket.getInputStream();
            byte[] tmp = new byte[4];
            in.read(tmp);
            Ball b = new Ball();

            b.unserialize(tmp, screenWidth);

            return b;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}