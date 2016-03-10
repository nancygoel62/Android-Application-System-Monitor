package com.example.toshiba.myapplication13;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by toshiba on 6/2/16.
 */
public class system extends Fragment {

    public TextView text1;
    public  system()
    {
        //empty constructor
    }
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,Bundle savedInstanceState )
    {
        View v=inflater.inflate(R.layout.system_fragmnent,container,false);
        text1= (TextView)v.findViewById(R.id.system_textView1);
        int api_level= Build.VERSION.SDK_INT;

        String android_version = Build.VERSION.RELEASE;
        String api_level_string= Integer.toString(api_level);
        String Build_id = Build.DISPLAY;
        String Device = Build.DEVICE;
        String Hardware= Build.HARDWARE;
        String manufacture= Build.MANUFACTURER;
        String Model=Build.MODEL;
        String Board_used= Build.BOARD;
        String Product = Build.PRODUCT;
        String hardware_Serail_no = Build.SERIAL;
        String Type_of_device= Build.TYPE;
        String BootLoader=Build.BOOTLOADER;
        String tags= Build.TAGS;
        String root_access ;

        if(tags!=null&&tags.contains("test-keys"))
            root_access = "Yes";
        else
            root_access="NO";

        //text1.setText(Html.fromHtml("Device : "+ "<font color=black>"+Device+"</font>"+"\n\nManufacture  :  "+manufacture +"\n\nModel  :   "+Model+"\n\nAndroid Version  :  "+android_version+"\n\nAPI level :   "+ api_level_string+"\n\nHardware  :  "+Hardware+"\n\nBuild Id  :  "+Build_id+"\n\nProduct  :  "+Product+ "\n\nBoard Used  : "+Board_used+"\n\nHardware Serial No : "+hardware_Serail_no+"\n\nType  :  "+Type_of_device +"\n\nTags  : "+tags+"\n\nRoot Access  : "+root_access));
        text1.setText("Device : "+Device+"\n\nManufacture  :  "+manufacture +"\n\nModel  :   "+Model+"\n\nAndroid Version  :  "+android_version+"\n\nAPI level :   "+ api_level_string+"\n\nHardware  :  "+Hardware+"\n\nBuild Id  :  "+Build_id+"\n\nProduct  :  "+Product+ "\n\nBoard Used  : "+Board_used+"\n\nHardware Serial No : "+hardware_Serail_no+"\n\nType  :  "+Type_of_device +"\n\nTags  : "+tags+"\n\nRoot Access  : "+root_access);
        return v;
    }
}
