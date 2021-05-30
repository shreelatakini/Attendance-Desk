package com.example.project.ui.dashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.R;
import com.example.project.ui.notifications.NotificationData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class DashboardData 
{
    private String subcode ;
    private String subname;
    private int weekpresent;
    private int weekabsent ;
    
    public DashboardData( String subn , String subc, int wab, int wpr)
    {
        this.subcode=subc;
        this.subname=subn;
        this.weekabsent=wab;
        this.weekpresent=wpr;
    }
    public String getSubcode(){return  subcode;}
    public String getSubname(){return subname;}
    public int getPresents(){return weekpresent;}
    public int getAbsents(){return weekabsent;}

    public String setSubcode(String sc){this.subcode=sc;}
    public String setSubname(String sn){this.subname=sn;}
    public int setPresents(int wp){this.weekpresent=wp;}
    public int setAbsents(int wa){this.weekabsent=wa;}


}

class DashboardAdapter extends ArrayAdapter<DashboardData>
{
    private Context dcontext;
    private ArrayList<DashboardData> sublist = new ArrayList<>();
    public DashboardAdapter(@NonNull Context context,
                               @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<DashboardData> list)
    {
        super(context, 0 , list);
        dcontext = context;
        sublist = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(dcontext).inflate(R.layout.listeve,parent,false);

        DashboardData currentNotif = sublist.get(position);



        TextView name = (TextView) listItem.findViewById(R.id.name);
        name.setText(currentNotif.getnName());


        TextView release = (TextView) listItem.findViewById(R.id.details);
        release.setText(currentNotif.getnDetails());



        return listItem;
    }
}
}

public class DashboardFragment extends Fragment{

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        Spinner drop;
        drop = (Spinner) root.findViewById(R.id.dd);
        List<String> subjects = new ArrayList<String>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.addValueEventListener(new ValueEventListener() {
            JSONObject obj;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                subjects.clear();
                for (DataSnapshot notis: dataSnapshot.getChildren())
                {
                    for( DataSnapshot sections : notis.getChildren())
                    {
                        String sub=   (String) sections.child("classcode").getValue();
                        String subn=   (String) sections.child("classname").getValue();
                        
                    }
                    
                }

//                nadapter = new NotificationAdapter(getActivity(),notifList);
//                listView.setAdapter(nadapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, subjects);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        drop.setAdapter(dataAdapter);
        drop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }


        });
        return root;
    }
}