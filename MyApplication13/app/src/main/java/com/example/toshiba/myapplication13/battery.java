package com.example.toshiba.myapplication13;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by toshiba on 3/2/16.
 */
public class battery extends Fragment {

    public battery() {
        //empty constructor
    }

    public TextView text1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.battery_fragment, container, false);
        text1 = (TextView) v.findViewById(R.id.textView1);
        text1.setText("Memory Section");

        getActivity().registerReceiver(this.batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


        return v;
    }

    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            String Technology = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);

            String health = " Unspecified ";
            int health_int = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
            if (health_int == 7)
                health = "COLD";
            else if (health_int == 4)
                health = "DEAD";
            else if (health_int == 2)
                health = "GOOD";
            else if (health_int == 3)
                health = "OVER HEAT";
            else if (health_int == 5)
                health = "OVER VOLTAGE";
            else if (health_int == 6)
                health = "UNSPECIFIED FAILURE";
            else
                health = "UNKNOWN";

            int level_int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            String level = Integer.toString(level_int);


            String plugged = "NOT PLUGGED";
            int plugged_int = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            if (plugged_int == 1)
                plugged = "AC CHARGER";
            else if (plugged_int == 2)
                plugged = "USB PORT";
            else if (plugged_int == 4)
                plugged = "WIRELESS";

            String status = "";
            int status_int = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
            if (status_int == 2)
                status = "CHARGING";
            else if (status_int == 3)
                status = "DISCHARGING";
            else if (status_int == 5)
                status = "FULLY CHARGED";
            else if (status_int == 4)
                status = "NOT CHARGING";
            else if (status_int == 1)
                status = "UNKNOWN";

            int temp_int;
            temp_int = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);

            Float temp_float = temp_int / 10.0F;
            String temp = Float.toString(temp_float);

            int volt_int;
            volt_int = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
            String volt = Integer.toString(volt_int);


            text1.setText("\n\n\nTechnology :  " + Technology + "\n\n\nHealth :  " + health + "\n\n\nLevel :  " + level + " %\n\n\nStatus :  " + status + "\n\n\nPlugged :  " + plugged + "\n\n\nTemperature :  " + temp + " celsius\n\n\nVoltage :  " + volt + " mV");

            //  System.out.print("level - "+ level );
        }
    };

}