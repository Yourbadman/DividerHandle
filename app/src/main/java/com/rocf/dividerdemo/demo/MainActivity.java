package com.rocf.dividerdemo.demo;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import com.rocf.dividerdemo.R;

public class MainActivity extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Use an existing ListAdapter that will map an array
        // of strings to TextViews
        setListAdapter(new ArrayAdapter<String>(this,
                R.layout.main_list_item,mStrings));

        getListView().setTextFilterEnabled(true);

    }

    public String[] mStrings = Names.mNameStrings;

}
