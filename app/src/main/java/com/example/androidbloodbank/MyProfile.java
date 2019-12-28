package com.example.androidbloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.View;

public class MyProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
    }

    public void onMedicalHistoryClick(View view)
    {
        Intent i = new Intent(this, MedicalHistory.class);
        startActivity(i);
    }
}
