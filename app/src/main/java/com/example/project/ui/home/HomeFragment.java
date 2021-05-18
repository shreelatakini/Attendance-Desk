package com.example.project.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.R;

import static com.example.project.R.layout.fragment_home;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button one,two,three,four;
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(fragment_home, container, false);
        one = (Button)root.findViewById(R.id.b1st);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent one= new Intent(HomeFragment.this.getActivity() , firstyear.class);
                startActivity(one);
            }
        });
        two =(Button)root.findViewById(R.id.b2nd);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent two = new Intent(HomeFragment.this.getActivity(),secondyear.class);
                startActivity(two);
            }
        });
        three =(Button)root.findViewById(R.id.b3rd);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent three = new Intent(HomeFragment.this.getActivity(),thirdyear.class);
                startActivity(three);
            }
        });
        four =(Button)root.findViewById(R.id.b4th);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent four = new Intent(HomeFragment.this.getActivity(),fourthyear.class);
                startActivity(four);
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }


}