package com.ups.dualpong.bluetooth;

/**
 * Created by julien on 20/02/14.
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.ups.dualpong.CreateActivity;
import com.ups.dualpong.GameActivity;
import com.ups.dualpong.game.Ball;

import com.ups.dualpong.game.Ball;

public class BluetoothServer extends Thread {
    private final BluetoothServerSocket blueServerSocket;
    private BluetoothAdapter blueAdapter;
    private CreateActivity createActivity;
    private BluetoothSocket blueSocket = null;

    private static BluetoothServer instance = null;
    public static BluetoothServer getInstance(){
        return instance;
    }

    private static String APPNAME = "BTAPPDELAMORT";


    public BluetoothServer(BluetoothAdapter blueAdapter_, CreateActivity act) {
        instance = this;
        createActivity = act;
        this.blueAdapter=blueAdapter_;
        // On utilise un objet temporaire qui sera assigné plus tard à blueServerSocket car blueServerSocket est "final"
        BluetoothServerSocket tmp = null;
        try {
            // MON_UUID est l'UUID (comprenez identifiant serveur) de l'application. Cette valeur est nécessaire côté client également !
            tmp = blueAdapter.listenUsingRfcommWithServiceRecord(APPNAME, UUID.fromString("c065af87-b800-4bb3-a932-c4c130f2a50d"));
        } catch (IOException e) { }
        blueServerSocket = tmp;
    }

	@Override
	public void run() {

        try {
            blueSocket = blueServerSocket.accept();

        } catch (IOException e) {
        }
        if (blueSocket != null) {
            manageConnectedSocket(blueSocket);
            try {
                blueServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

	}



	private void manageConnectedSocket(BluetoothSocket blueSocket) {
		Log.i("BTserver", "manageConnectedSocked");
		try {
            Intent intent = new Intent(createActivity, GameActivity.class);
            createActivity.startActivity(intent);
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

	public void cancel() {
		try {
            instance = null;
			blueServerSocket.close();
            this.destroy();
		} catch (IOException e) {
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