package com.example.toshiba.myapplication13;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by toshiba on 6/2/16.
 */


public class about extends Fragment{
    public TextView textView1;

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle bundle)
    {
        View v=inflater.inflate(R.layout.about_fragment,container,false);
        textView1=(TextView)v.findViewById(R.id.about_textview1);

        String about_text= "Developer :  Nancy Goel \n\n Contact :\n nancygoel62@gmail.com ";
        textView1.setText(about_text);

        return v;
    }
}
