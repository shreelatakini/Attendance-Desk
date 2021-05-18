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

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends ArrayAdapter<NotificationData> {

    private Context nContext;
    private List<NotificationData> nList = new ArrayList<>();

    public NotificationAdapter(@NonNull Context context,
                               @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<NotificationData> list) {
        super(context, 0, list);
        nContext = context;
        nList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(nContext).inflate(R.layout.listeve, parent, false);

        NotificationData currentNotif = nList.get(position);

        ImageView image = (ImageView) listItem.findViewById(R.id.icon);
        image.setImageResource(currentNotif.getnIcon());

        TextView name = (TextView) listItem.findViewById(R.id.title);
        name.setText(currentNotif.getnName());

        TextView release = (TextView) listItem.findViewById(R.id.details);
        release.setText(currentNotif.getnDetails());

        return listItem;
    }
}
