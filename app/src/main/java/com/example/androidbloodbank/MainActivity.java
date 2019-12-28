package com.example.androidbloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSearchDonorClick(View view)
    {
        Intent i = new Intent(this, SearchDonor.class);
        startActivity(i);
    }

    public void onBloodBankClick(View view)
    {
        Intent i = new Intent(this, BloodBank.class);
        startActivity(i);
    }

    public void onMyProfileClick(View view)
    {
        Intent i = new Intent(this, MyProfile.class);
        startActivity(i);
    }

    public void onSearchAcceptorClick(View view)
    {
        Intent i = new Intent(this, SearchAcceptor.class);
        startActivity(i);
    }

}
