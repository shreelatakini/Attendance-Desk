package com.example.project.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.project.R;

package com.example.project.ui.notifications;

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
import com.example.project.ui.notifications.NotificationData;

import java.util.ArrayList;
import java.util.List;


 class NotificationData2 {

    // Store the id of the  movie poster
    private int nIcon;
    // Store the name of the movie
    private String nName;
    // Store the release date of the movie
    private String nDetails;
    private long nKey;
    // Constructor that is used to create an instance of the NotificationData object
    public NotificationData2(int cnIcon, String cnName, String cnDetails ) {
        this.nIcon = cnIcon;
        this.nName = cnName;
        this.nDetails = cnDetails;
        this.nKey=0;
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
    public void setnKey(long k){ this.nKey =k;}
    public long getnKey(){ return this.nKey; }

}



class NotificationAdapter2 extends ArrayAdapter<NotificationData2> {

    private Context nContext;
    private ArrayList<NotificationData2> notiflist = new ArrayList<>();

    public NotificationAdapter2(@NonNull Context context,
                               @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<NotificationData2> list) {
        super(context, 0 , list);
        nContext = context;
        notiflist = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(nContext).inflate(R.layout.listeve,parent,false);

        NotificationData2 currentNotif = notiflist.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.icon);
        image.setImageResource(currentNotif.getnIcon());

        TextView name = (TextView) listItem.findViewById(R.id.name);
        name.setText(currentNotif.getnName());


        TextView release = (TextView) listItem.findViewById(R.id.details);
        release.setText(currentNotif.getnDetails());

        return listItem;
    }
}


public class statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        getSupportActionBar().setTitle("Subject statistics");

        String namet = getIntent().getExtras().getString("name");
        String codet = getIntent().getExtras().getString("code");
        String desct = getIntent().getExtras().getString("desc");
        String abset = getIntent().getExtras().getString("abse");


        TextView tname ;

        tname = (TextView)this.findViewById(R.id.namee);
        tname.setText(namet);

        tname = (TextView)this.findViewById(R.id.descrr);
        tname.setText(desct);

        tname = (TextView)this.findViewById(R.id.codee);
        tname.setText(codet);

        tname = (TextView)this.findViewById(R.id.absenteess);
        tname.setText(abset);


    }
}