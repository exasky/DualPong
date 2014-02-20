package com.ups.dualpong;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.ups.dualpong.bluetooth.BluetoothClient;

import java.util.Set;

public class JoinActivity extends Activity {
    private ListView devicesListView;
    private BluetoothAdapter BTadapter;
    private ArrayAdapter<String> ARadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_activity);
        devicesListView = (ListView)findViewById(R.id.deviceListView);


        BTadapter = BluetoothAdapter.getDefaultAdapter();
        if (BTadapter != null){
            if (!BTadapter.isEnabled())
                BTadapter.enable();
            Set<BluetoothDevice> pairedDevices = BTadapter.getBondedDevices();
            String[] devicesStr = new String[pairedDevices.size()];

            int i=0;
            for (BluetoothDevice device : pairedDevices) {
                devicesStr[i] = device.getName()+" ;"+device.getAddress();
                i++;
            }
            ARadapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1,
                    devicesStr);

            devicesListView.setAdapter(ARadapter);

            devicesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    connectDevice(adapterView, view, i, l);
                }
            });
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.join, menu);
		return true;
	}

    public void connectDevice(AdapterView<?> adapterView, View view, int i, long l){
        String dev = (String)adapterView.getAdapter().getItem(i);
        dev = dev.split(";")[1];

        BluetoothDevice device = BTadapter.getRemoteDevice(dev);

        BluetoothClient BTclient = new BluetoothClient(device, BTadapter, this);
        BTclient.start();
//        Intent intent = new Intent(this, GameActivity.class);
//        startActivity(intent);
    }
}
