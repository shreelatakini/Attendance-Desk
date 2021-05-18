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

import java.util.ArrayList;
import java.util.List;
import com.example.project.R;

public class MovieAdapter extends ArrayAdapter<NotificationData> {

    private Context mContext;
    private List<NotificationData> moviesList = new ArrayList<>();

    public MovieAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<NotificationData> list) {
        super(context, 0 , list);
        mContext = context;
        moviesList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.listeve,parent,false);

        NotificationData currentMovie = moviesList.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.icon);
        image.setImageResource(currentMovie.getnIcon());

        TextView name = (TextView) listItem.findViewById(R.id.name);
        name.setText(currentMovie.getnName());

        TextView release = (TextView) listItem.findViewById(R.id.details);
        release.setText(currentMovie.getnDetails());

        return listItem;
    }
}
