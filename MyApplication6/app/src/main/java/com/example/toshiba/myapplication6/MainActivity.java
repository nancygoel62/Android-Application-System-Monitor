package com.example.toshiba.myapplication6;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MainActivity extends AppCompatActivity {

    private TextView text1,text3,text4,text5, text6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1=(TextView)findViewById(R.id.textView);
        text3=(TextView)findViewById(R.id.textView3);
        text4=(TextView)findViewById(R.id.textView4);
        text5=(TextView)findViewById(R.id.textView5);
        text6 = (TextView)findViewById(R.id.textView6);
        int api_level= Build.VERSION.SDK_INT;
        String android_version = Build.VERSION.RELEASE;

        String api_level_string= Integer.toString(api_level);


        //String security_patch_level = Build.VERSION.SECURITY_PATCH;

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

        text1.setText("Device : "+Device+"\n\nManufacture  :  "+manufacture +"\n\nModel  :   "+Model+"\n\nAndroid Version  :  "+android_version+"\n\nAPI level :   "+ api_level_string+"\n\nHardware  :  "+Hardware+"\n\nBuild Id  :  "+Build_id+"\n\nProduct  :  "+Product+ "\n\nBoard Used  : "+Board_used+"\n\nHardware Serial No : "+hardware_Serail_no+"\n\nType  :  "+Type_of_device +"\n\nTags  : "+tags+"\n\nRoot Access  : "+root_access);
        //text1.setText(android_version+api_level_string+Build_id+Device+Hardware);


        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long available_memory= mi.availMem;
        long total_memory = mi.totalMem;
        available_memory=available_memory/1048576;
        total_memory=total_memory/1048576;

        long  available_memory_percentage = (available_memory*100)/total_memory;

        String available_memory_string = Long.toString(available_memory);
        String total_memory_string = Long.toString(total_memory);
        String available_memory_percentage_string = Long.toString(available_memory_percentage);

 //       text3.setText("Total RAM  :  " + total_memory_string +" MB "+"\n\nAvailable RAM  :  "+available_memory_string+ " MB "+"("+available_memory_percentage_string+"%)");


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


        text3.setText("Total RAM  :  " + total_memory_string +" MB "+"\n\nAvailable RAM  :  "+available_memory_string+ " MB "+"("+available_memory_percentage_string+"%)"+"\n\nTotal System Memory  :  " +totalmemory_string+"\n\nAvailable System Memory  :   "+availablememory_string+"("+availablememory_percentage_string+"%)"+ "\n\nInternal Total Memory : " + sdcard_total_memory_string +"\n\nInternal Available Memory  : "+ sdcard_available_memory_string );


        String VM = System.getProperty("java.vm.name");


        String java_vm_name=System.getProperty("java.vm.name");
        String java_vm_vendor = System.getProperty("java.vm.vendor");
        String java_vm_version= System.getProperty("java.vm.version");
        String java_vendor_url = System.getProperty("java.vendor.url");

        String os_arch = System.getProperty("os.arch");
        String os_name=System.getProperty("os.name");
        String os_version =System.getProperty("os.version");


        text4.setText("Java VM Name :  "+java_vm_name+"\n\nJava VM Vendor :  "+java_vm_vendor+"\n\nJava VM Version :  "+java_vm_version+ "\n\n Kernal Architecture  : " +os_arch +"\n\nKernal Name :  "+os_name +"\n\nKernal Version  :  "+os_version );

        String final_text=null;
        int i,j;
        i=0;j=0;
        int arr[]={1,2,3,4,5,20,22,28,29};

        try {
            RandomAccessFile file_meminfo = new RandomAccessFile("/proc/meminfo", "r");
            String line_text ;
            boolean valid=true;
            while(valid)
            {
                line_text = file_meminfo.readLine();
                if(line_text!=null)
                {
                    i++;
                    if(j<=8&&arr[j]==i)

                    {final_text = final_text+"\n\n"+line_text;j++;}
                }
                else
                {valid=false;}
            }
        }
        catch (IOException ex)
        {

        }
        text5.setText(final_text);

        String final_text2=null;

        int cores  = Runtime.getRuntime().availableProcessors();
        final_text2 = Integer.toString(cores);
        text6.setText(final_text2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
