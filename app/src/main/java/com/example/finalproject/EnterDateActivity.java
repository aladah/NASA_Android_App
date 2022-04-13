package com.example.finalproject;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalproject.databinding.ActivityEnterDateBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.Date;

public class EnterDateActivity extends DrawerBaseActivity {

    ActivityEnterDateBinding activityEnterDateBinding;

    private TextView greeting;
    SharedPreferences prefs = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting common nav drawer and toolbar
        activityEnterDateBinding = ActivityEnterDateBinding.inflate(getLayoutInflater());
        allocateActivityTitle("IOTD Date Selector");
        setContentView(activityEnterDateBinding.getRoot());
        //set title of nav drawer to activity name
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headView = navigationView.getHeaderView(0);
        ((TextView) headView.findViewById(R.id.activityTitle)).setText(R.string.date_picker_page);
        ((TextView) headView.findViewById(R.id.activityVersion)).setText(R.string.date_picker_version);

        Button dateSelectButton = findViewById(R.id.selectDate);

        //get name from shared preferences
        prefs = getSharedPreferences("FileName", Context.MODE_PRIVATE);
        String savedString = prefs.getString("TypedText", " ");
        String full = getResources().getString(R.string.date_picker_button_text, savedString);
        dateSelectButton.setText(full);

        //Create date picking widget
        android.widget.DatePicker calendar;
        calendar = findViewById(R.id.datePicker);

        //set Max date to current day
        calendar.setMaxDate(new Date().getTime());

        dateSelectButton.setOnClickListener(click -> {
            String dateSelected = calendar.getYear() + "-" + (calendar.getMonth() + 1) + "-" + calendar.getDayOfMonth();

            Intent sendDateInformation = new Intent(getBaseContext(), ShowImageActivity.class);
            sendDateInformation.putExtra("Date", dateSelected);
            startActivity(sendDateInformation);
        });
    }
}