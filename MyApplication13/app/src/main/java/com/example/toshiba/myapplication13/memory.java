package com.example.toshiba.myapplication13;

import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;

/**
 * Created by toshiba on 6/2/16.
 */
public class memory extends Fragment {
    public TextView textView1;
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        View v= inflater.inflate(R.layout.memory_fragment,container,false);

        textView1 = (TextView)v.findViewById(R.id.memory_textView1);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();

        ActivityManager activityManager = (ActivityManager)getActivity().getSystemService(getActivity().ACTIVITY_SERVICE);

        activityManager.getMemoryInfo(mi);
        long available_memory= mi.availMem;
        long total_memory = mi.totalMem;
        available_memory=available_memory/1048576;
        total_memory=total_memory/1048576;

        long  available_memory_percentage = (available_memory*100)/total_memory;

        String available_memory_string = Long.toString(available_memory);
        String total_memory_string = Long.toString(total_memory);
        String available_memory_percentage_string = Long.toString(available_memory_percentage);

        //textView1.setText("Total RAM  :  " + total_memory_string +" MB "+"\n\nAvailable RAM  :  "+available_memory_string+ " MB "+"("+available_memory_percentage_string+"%)");

        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long totalmemory=   stat.getTotalBytes();
        long availablememory =stat.getAvailableBytes();
        totalmemory=totalmemory/(1024*1024); //total memory in MB
        availablememory=availablememory/(1024*1024);

        long availablememory_percentage=(availablememory*100) / totalmemory;

        String totalmemory_string =Long.toString(totalmemory);
        String availablememory_string=Long.toString(availablememory);
        String availablememory_percentage_string= Long.toString(availablememory_percentage);

        boolean sdcard_mounted ;
        String sdcard_total_memory_string="No";
        String sdcard_available_memory_string = "NO";

        if(android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            sdcard_mounted=true;
        }
        else
        {
            sdcard_mounted=false;
        }
        if(sdcard_mounted==false)
        {
            sdcard_total_memory_string = "SD Card Not Present";
            sdcard_available_memory_string = "SD Card Not Present";
        }
        else
        {
            File sdcard_path = android.os.Environment.getExternalStorageDirectory();
            StatFs stat2 = new StatFs(sdcard_path.getPath());
            long sdcard_totalmemory= stat2.getTotalBytes();
            long sdcard_availablememory= stat2.getAvailableBytes();
            long sdcard_available_percentage ;
            sdcard_availablememory = sdcard_availablememory/(1024*1024);
            sdcard_totalmemory = sdcard_totalmemory/(1024*1024);
            sdcard_available_percentage = (sdcard_availablememory*100)/sdcard_totalmemory;
            sdcard_available_memory_string=Long.toString(sdcard_availablememory)+"MB ("+Long.toString(sdcard_available_percentage)+"%)" ;
            sdcard_total_memory_string = Long.toString(sdcard_totalmemory)+"MB";
        }


        textView1.setText("Total RAM  :  " + total_memory_string +" MB "+"\n\nAvailable RAM  :  "+available_memory_string+ " MB "+"("+available_memory_percentage_string+"%)"+"\n\nTotal System Memory  :  " +totalmemory_string+"\n\nAvailable System Memory  :   "+availablememory_string+"("+availablememory_percentage_string+"%)"+ "\n\nInternal Total Memory : " + sdcard_total_memory_string +"\n\nInternal Available Memory  : "+ sdcard_available_memory_string );






        return v;
    }
}
