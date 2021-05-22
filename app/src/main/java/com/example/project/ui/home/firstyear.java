package com.example.project.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.project.R;

import java.util.ArrayList;
import java.util.List;



public class firstyear extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstyear);
        Spinner yearone =(Spinner)findViewById(R.id.sp1);
        yearone.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        List<String> categories = new ArrayList<String>();
        categories.add("Section 'A'");
        categories.add("Section 'B'");
        categories.add("Section 'C'");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        yearone.setAdapter(dataAdapter);

        Switch simpleSwitch = (Switch) findViewById(R.id.switch1);



        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( simpleSwitch.isChecked())
                {
                    simpleSwitch.setText(" Class");
                }
                else
                {
                    simpleSwitch.setText(" Shortage ");
                }

            }
        });

    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


}