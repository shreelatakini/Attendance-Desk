package com.example.project.ui.notifications;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    private ListView listView;
    private NotificationAdapter nadapter;

    int  getNotifType(String s1)
    {
        int rresid = this.getResources().getIdentifier(s1, "mipmap", "com.example.project");
        return rresid;
    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
  //      final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
//
        String classStart= " The class will start soon ";
        String shortBoy=" This nigga has shoertage ";
        String nType="classs";
        listView    = (ListView) root.findViewById(R.id.listV);
        ArrayList<NotificationData> notifList = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        //reading data
        ArrayList<NotificationData> notifListfor = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            JSONObject obj;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot notis: dataSnapshot.getChildren())
                {

                    int iconid= getNotifType((String) notis.child("type").getValue());
                    String title=   (String) notis.child("title").getValue();
                    String details =(String) notis.child("details").getValue();

                    notifList.add(new NotificationData
                               (
                                       iconid,
                                     title,
                                      details
                               )
                                    );

                }

                nadapter = new NotificationAdapter(getActivity(),notifList);
                listView.setAdapter(nadapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return root;
    }
}