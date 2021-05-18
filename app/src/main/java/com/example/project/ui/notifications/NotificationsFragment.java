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

    public class NotificationData {

        // Store the id of the  movie poster
        private int nIcon;
        // Store the name of the movie
        private String nName;
        // Store the release date of the movie
        private String nDetails;

        // Constructor that is used to create an instance of the NotificationData object
        public NotificationData(int cnIcon, String cnName, String cnDetails) {
            this.nIcon = cnIcon;
            this.nName = cnName;
            this.nDetails = cnDetails;
        }

        public int getnIcon() {
            return nIcon;
        }

        public void setnIcon(int anIcon) {
            this.nIcon = anIcon;
        }

        public String getnName() {
            return nName;
        }

        public void setnName(String anName) {
            this.nName = anName;
        }

        public String getnDetails() {
            return nDetails;
        }

        public void setnDetails(String anDetails) {
            this.nDetails = anDetails;
        }
    }

    public class NotificationAdapter extends ArrayAdapter<NotificationData> {

        private Context nContext;
        private List<NotificationData> nList = new ArrayList<>();

        public NotificationAdapter(@NonNull Context context,
                                   @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<NotificationData> list) {
            super(context, 0 , list);
            nContext = context;
            nList = list;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItem = convertView;
            if(listItem == null)
                listItem = LayoutInflater.from(nContext).inflate(R.layout.listeve,parent,false);

            NotificationData currentNotif = nList.get(position);

            ImageView image = (ImageView)listItem.findViewById(R.id.icon);
            image.setImageResource(currentNotif.getnIcon());

            TextView name = (TextView) listItem.findViewById(R.id.title);
            name.setText(currentNotif.getnName());

            TextView release = (TextView) listItem.findViewById(R.id.details);
            release.setText(currentNotif.getnDetails());

            return listItem;
        }
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
//        ListView listView = root.findViewById(R.id.listV);
//
//
//       NotificationData values[]= new NotificationData[20];
//
//        ArrayAdapter<NotificationData> adapter = new ArrayAdapter<NotificationData>(getActivity(), R.layout.listeve , values);
//        listView.setAdapter(adapter);
        String classStart= " The class will start soon ";
        String shortBoy=" This nigga has shoertage ";
        ListView listView = (ListView) root.findViewById(R.id.listV);
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

        NotificationAdapter notificationAdapter = new NotificationAdapter(getContext(),notifList);
        listView.setAdapter(notificationAdapter);

        return root;
    }
}