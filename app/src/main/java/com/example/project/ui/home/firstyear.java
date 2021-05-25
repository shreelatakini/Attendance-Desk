package com.example.project.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.MainActivity;
import com.example.project.R;
import com.example.project.ui.notifications.NotificationData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;




public class firstyear extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView tv;
    String nTitless;
    String nDetailss;
    String switch1="shortage";
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

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch simpleSwitch = (Switch) findViewById(R.id.switch1);
        Button clickButton = (Button) findViewById(R.id.send);
        TextView nT=(TextView)findViewById(R.id.nTitle);
        TextView nD=(TextView)findViewById(R.id.nDet);
        tv=(TextView)findViewById(R.id.textView7);

        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( simpleSwitch.isChecked())
                {
                    simpleSwitch.setText(" Class");
                    switch1="classs";
                }
                else
                {
                    simpleSwitch.setText("Shortage ");
                    switch1="shortage";
                }

            }
        });

        clickButton.setOnClickListener( new View.OnClickListener() {
            String keynade;

            @Override
            public void onClick(View v) {
                nDetailss = (String) nD.getText().toString();
                nTitless = (String) nT.getText().toString();
                HashMap<String,Object> map = new HashMap<>();
                map.put("classcode", nTitless);
                map.put("details", nDetailss);
                map.put("type",switch1);
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference();


//                // reading data from FireBase
//                ref.addValueEventListener(new ValueEventListener() {
//                    JSONObject obj;
//
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        for (DataSnapshot notis: dataSnapshot.getChildren())
//                        {
//                            Log.i("info obtained ", notis.getKey());
//                            keynade=notis.getKey();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        System.out.println("The read failed: " + databaseError.getCode());
//                    }
//                });

                //writing data to database

                ref.child("1").child("A").setValue("map").
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                Log.i(" Push ","completed ");

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Log.i(" Push ","failed");
                    }
                });
                nD.setText("");
                nT.setText("");
                simpleSwitch.toggle();
            }
        });



    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


}