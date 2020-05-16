package com.example.corona.view.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.corona.R;

public class HomeFragment extends Fragment {

    String countryName;
    int countryRecovered;
    int countryConfirmed;
    int countryDeaths;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Bundle bundle = this.getArguments();

        countryName = bundle.getString("name");
        countryConfirmed = bundle.getInt("confirmed");
        countryRecovered = bundle.getInt("recovered");
        countryDeaths = bundle.getInt("deaths");

        TextView name = view.findViewById(R.id.tvCountryName);
        TextView totalConfirmed = view.findViewById(R.id.tvTotalConfirmed);
        TextView totalRecovered = view.findViewById(R.id.tvTotalRecovered);
        TextView totalDeaths = view.findViewById(R.id.tvTotalDeaths);

        name.setText(countryName);
        System.out.println(countryDeaths);
        totalConfirmed.setText(String.valueOf(countryConfirmed));
        totalRecovered.setText(String.valueOf(countryRecovered));
        totalDeaths.setText(String.valueOf(countryDeaths));

        return view;
    }
}
