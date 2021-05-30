package com.example.project.ui.dashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.R;
import com.example.project.ui.notifications.NotificationAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



class DashboardData 
{
    private String subdescr;
    private String subcode ;
    private String subname;
    private int weekpresent;
    private int weekabsent ;
    private long subtime;
    
    public DashboardData( String subn , String subc, String subd, int wab, int wpr, long stm)
    {
        this.subcode=subc;
        this.subname=subn;
        this.subdescr=subd;
        this.weekabsent=wab;
        this.weekpresent=wpr;
        this.subtime=stm;

    }
    public String getSubcode(){return  subcode;}
    public String getSubname(){return subname;}
    public String getSubdescr(){return subdescr;}
    public int getPresents(){return weekpresent;}
    public int getAbsents(){return weekabsent;}
    public long getSubtime(){return subtime;}

    public void setSubcode(String sc){this.subcode=sc;}
    public void setSubname(String sn){this.subname=sn;}
    public void setSubdescr(String sd){this.subdescr=sd;}
    public void setPresents(int wp){this.weekpresent=wp;}
    public void setAbsents(int wa){this.weekabsent=wa;}
    public void setSubtime(int stm){this.subtime=stm;}


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
            listItem = LayoutInflater.from(dcontext).inflate(R.layout.listdashsteve,parent,false);

        DashboardData cursub = sublist.get(position);

        TextView t1 = (TextView)listItem.findViewById(R.id.code);
        TextView t2 = (TextView)listItem.findViewById(R.id.name);
        TextView t3 = (TextView)listItem.findViewById(R.id.descr);

        t1.setText(cursub.getSubcode());
        t2.setText(cursub.getSubname());
        t3.setText(cursub.getSubdescr());

        return listItem;
    }
}

public class DashboardFragment extends Fragment{
    private ListView listView;
    private DashboardAdapter dadapter;
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
       ArrayList<DashboardData> subjects = new ArrayList<>();
      listView = (ListView)root.findViewById(R.id.listV);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.addValueEventListener(new ValueEventListener() {
            JSONObject obj;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                subjects.clear();
                for (DataSnapshot yearss: dataSnapshot.getChildren())
                {
                    for( DataSnapshot sections : yearss.getChildren())
                    {
                        String key=sections.getKey();
                        Log.i("key1",key);
                        String subc=   (String) String.valueOf(sections.child("classcode").getValue());
                        Log.i("code",subc);
                        String subn=   (String) String.valueOf(sections.child("classname").getValue());
                        String subd=   (String) String.valueOf(sections.child("classdescr").getValue());
                        long subt= (long)sections.child("classtime").getValue();
                        int wpr=0;
                        int wab=0;
                        for( DataSnapshot student : sections.child("students").getChildren())
                        {
                            wpr+=1;
                            if(Integer.parseInt(String.valueOf(student.child("counter").getValue()))>0)
                            {
                                wab+=Integer.parseInt(String.valueOf(student.child("counter").getValue()));
                            }
                        }
                        wpr=(wpr*7)-wab;

                        DashboardData d1=new DashboardData(
                                subn,subc,subd,wab,wpr,subt
                        );
                        subjects.add(d1);

                    }
                    
                }

                dadapter = new DashboardAdapter(getActivity(),subjects);
                listView.setAdapter(dadapter);

           }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });



        return root;
    }
}