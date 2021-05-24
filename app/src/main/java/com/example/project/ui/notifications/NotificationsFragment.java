package com.example.project.ui.notifications;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
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

import java.util.ArrayList;
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

        notifList.add(new NotificationData(R.mipmap.shortage, "Shreelata" , shortBoy ));
        notifList.add(new NotificationData(getNotifType(nType), "MAD" ,classStart ));
        notifList.add(new NotificationData(R.mipmap.classs, "CN" ,classStart ));
        notifList.add(new NotificationData(R.mipmap.shortage, "Shreyas" ,shortBoy ));
        notifList.add(new NotificationData(R.mipmap.classs, "CG" , classStart));
        notifList.add(new NotificationData(R.mipmap.shortage, "Shaun" ,shortBoy ));
        notifList.add(new NotificationData(R.mipmap.shortage, "Tejas" ,shortBoy ));
        notifList.add(new NotificationData(R.mipmap.shortage, "Suraj" ,shortBoy ));
        notifList.add(new NotificationData(R.mipmap.classs, "COD" ,classStart ));
        notifList.add(new NotificationData(R.mipmap.classs, "COD" ,classStart ));
        notifList.add(new NotificationData(R.mipmap.classs, "COD" ,classStart ));
        notifList.add(new NotificationData(R.mipmap.classs, "COD" ,classStart ));
        notifList.add(new NotificationData(R.mipmap.classs, "COD" ,classStart ));
        notifList.add(new NotificationData(R.mipmap.classs, "COD" ,classStart ));
         nadapter = new NotificationAdapter(getActivity(),notifList);
        listView.setAdapter(nadapter);

        return root;
    }
}