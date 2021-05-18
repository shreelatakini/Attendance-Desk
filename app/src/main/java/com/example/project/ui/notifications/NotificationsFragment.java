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
    private MovieAdapter mAdapter;

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
        listView    = (ListView) root.findViewById(R.id.listV);
        ArrayList<NotificationData> notifList = new ArrayList<>();
        notifList.add(new NotificationData(R.mipmap.shortage, "Shreelata" , shortBoy ));
        notifList.add(new NotificationData(R.mipmap.classs, "MAD" ,classStart ));
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
//
//        listView = (ListView)root.findViewById(R.id.listV);
//        ArrayList<NotificationData> moviesList = new ArrayList<>();
//        moviesList.add(new NotificationData(R.mipmap.classs, "After Earth" , "2013"));
//        moviesList.add(new NotificationData(R.drawable.ic_dashboard_black_24dp, "Baby Driver" , "2017"));
//        moviesList.add(new NotificationData(R.drawable.ic_dashboard_black_24dp, "Deadpool" , "2016"));
//        moviesList.add(new NotificationData(R.drawable.ic_dashboard_black_24dp, "Divergent" , "2014"));
//        moviesList.add(new NotificationData(R.drawable.ic_dashboard_black_24dp, "Fight Club" , "1999"));
//        moviesList.add(new NotificationData(R.drawable.ic_dashboard_black_24dp, "Jaws" , "1975"));
//        moviesList.add(new NotificationData(R.drawable.ic_dashboard_black_24dp, "Pirates of the Caribbean" , "2011"));
//        moviesList.add(new NotificationData(R.drawable.ic_dashboard_black_24dp, "Star Wars" , "2016"));
//        moviesList.add(new NotificationData(R.drawable.ic_dashboard_black_24dp, "The Grey" , "2011"));
//
//        nadapter = new NotificationAdapter(getActivity(),moviesList);
//        listView.setAdapter(nadapter);
        return root;
    }
}