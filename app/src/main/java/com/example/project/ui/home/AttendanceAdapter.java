package com.example.project.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project.R;
import com.example.project.ui.notifications.NotificationData;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;




public class AttendanceAdapter extends ArrayAdapter<AttendanceData> {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    int sts;
        private Context nContext;
        private List<AttendanceData> attendlist = new ArrayList<>();

        public AttendanceAdapter(@NonNull Context context,
                                   @SuppressLint("SupportAnnotationUsage") @LayoutRes List<AttendanceData> list) {
            super(context, 0, list);
            nContext = context;
            attendlist = list;
        }

        public List<AttendanceData> getList(){
            return attendlist;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItem = convertView;
            if(listItem == null)
                listItem = LayoutInflater.from(nContext).inflate(R.layout.attendanceadapter, parent,false);

            AttendanceData currentNotif = attendlist.get(position);
            currentNotif.setAttendance(1);

            TextView name = (TextView) listItem.findViewById(R.id.name);
            name.setText(currentNotif.getName());

            TextView usn = (TextView) listItem.findViewById(R.id.details);
            usn.setText(currentNotif.getstudusn());


            ref.addValueEventListener(new ValueEventListener() {
                JSONObject obj;
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                sts= Integer.parseInt(String.valueOf(dataSnapshot.child(currentNotif.getYr()).child(currentNotif.getSx()).child("students").child(currentNotif.getstudusn()).child("counter").getValue()));

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });



            CheckBox cb = (CheckBox) listItem.findViewById(R.id.present);
            cb.setChecked(false);
            cb.setTag(usn);
            currentNotif.setCounter(0);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(cb.isChecked()) {
                        currentNotif.setAttendance(0);
                        currentNotif.setCounter(sts+1);
                        Log.i("settingcounter",Integer.toString(currentNotif.getCounter()));
                    }else{
                        currentNotif.setAttendance(1);
                    }
                    currentNotif.setCounter(0);
                }
            });
            return listItem;
        }
    }
