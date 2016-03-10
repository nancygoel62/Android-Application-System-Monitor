package com.example.toshiba.myapplication13;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by toshiba on 7/2/16.
 */
public class connections extends Fragment {

    TextView tex1;
    String state_string, ip_string, strength, mac_adress,wifi_name;
    int ip;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View v = inflater.inflate(R.layout.connections_fragment, container, false);
        tex1 = (TextView) v.findViewById(R.id.connections_textView1);

        final IntentFilter filters = new IntentFilter();
        filters.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filters.addAction("android.net.wifi.STATE_CHANGE");
        super.getActivity().registerReceiver(WifiInfoReceiver, filters);


        return v;
    }

    private BroadcastReceiver WifiInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            WifiManager wifiManager = (WifiManager) getActivity().getSystemService(getActivity().WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();

            int state = wifiManager.getWifiState();

            if (state == 0)
                state_string = "Disconnecteing ";
            else if (state == 1)
                state_string = "Disconnected";
            else if (state == 3)
                state_string = "Connected";
            else if (state == 2)
                state_string = "Connecting";

            if (state == 0 || state == 1) {
                ip_string = "None";
                strength = "None";
                mac_adress = "None";
                wifi_name="None";
            } else if (state == 3 || state == 2) {
                ip_string = Integer.toString(wifiInfo.getIpAddress());
                wifi_name=wifiInfo.getSSID();
                mac_adress = wifiInfo.getMacAddress();
                strength = Integer.toString(wifiInfo.getLinkSpeed());
            }

            tex1.setText("Wifi State =  " + state_string +"\n\nConnection  :  "+wifi_name +"\n\nIp Adress  =  " + ip_string + "\n\nStrength  =  " + strength + "\n\nMAC Adress  :  " + mac_adress);
        }
    };
}
