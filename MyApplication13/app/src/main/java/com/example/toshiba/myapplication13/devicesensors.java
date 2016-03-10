package com.example.toshiba.myapplication13;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toshiba on 5/2/16.
 */
public class devicesensors extends Fragment {
    public  devicesensors()
    {

    }

    private ListView listview;
    private SensorManager msensormanager;
    private List<Sensor> sensorslist =null;

    private List<String> sensorlist_string= new ArrayList<String>();


    @Override
    public void onCreate(Bundle savedInstanceStates)
    {
        super.onCreate(savedInstanceStates);
    }
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,Bundle savedInstanceStates)
    {
        View v = inflater.inflate(R.layout.battery_fragment, container, false);
        listview = (ListView)v.findViewById(R.id.listview);

        msensormanager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);

        sensorslist = msensormanager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor s: sensorslist)
        {
            sensorlist_string.add("Name : "+ s.getName()+"\nVendor : "+ s.getVendor() + "\nVersion : "+s.getVersion()+"\nType : "+s.getType() + "\nPower : "+s.getPower() + "\n\n\n");
        }
        listview.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,sensorlist_string));


        return v;
    }
}
