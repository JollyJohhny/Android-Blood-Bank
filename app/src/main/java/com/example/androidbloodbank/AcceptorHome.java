package com.example.androidbloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AcceptorHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptor_home);
        Toast.makeText(AcceptorHome.this, "Welcome Acceptor!", Toast.LENGTH_SHORT).show();
    }

    public void Logout(View view)
    {
        FirebaseAuth.getInstance().signOut(); //End user session
        startActivity(new Intent(this, HomePage.class)); //Go back to home page
        finish();
    }

    public void BloodBankDonor(View view)
    {
        Intent i = new Intent(this, BloodBankAcceptor.class);
        startActivity(i);
    }

    public void AcceptorProfile(View view)
    {
        Intent i = new Intent(this, AcceptorMyProfile.class);
        startActivity(i);
    }

    public void SearchDonors(View view)
    {
        Intent i = new Intent(this, SearchDonor.class);
        startActivity(i);
    }
}
