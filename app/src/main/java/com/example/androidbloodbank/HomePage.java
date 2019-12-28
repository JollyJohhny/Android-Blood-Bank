package com.example.androidbloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void RegisterDonor(View view)
    {
        Intent i = new Intent(this, RegisterDonor.class);
        startActivity(i);
    }

    public void RegisterAcceptor(View view)
    {
        Intent i = new Intent(this, RegisterAcceptor.class);
        startActivity(i);
    }

    public void LoginDonor(View view)
    {
        Intent i = new Intent(this, LoginDonor.class);
        startActivity(i);
    }

    public void LoginAcceptor(View view)
    {
        Intent i = new Intent(this, LoginAcceptor.class);
        startActivity(i);
    }
}
