package com.example.project.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;




public class AttendanceAdapter extends ArrayAdapter<AttendanceData> {

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

            CheckBox cb = (CheckBox) listItem.findViewById(R.id.present);
            cb.setChecked(false);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(cb.isChecked())
                        currentNotif.setAttendance(0);
                    else
                        currentNotif.setAttendance(1);

                }
            });
            return listItem;
        }
    }
