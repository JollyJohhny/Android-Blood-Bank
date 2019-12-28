package com.example.androidbloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DonorHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_home);
        Toast.makeText(DonorHome.this, "Welcome Donor!", Toast.LENGTH_SHORT).show();

    }

    public void Logout(View view)
    {
        FirebaseAuth.getInstance().signOut(); //End user session
        startActivity(new Intent(DonorHome.this, HomePage.class)); //Go back to home page
        finish();
    }

    public void BloodBankAcceptor(View view)
    {
        Intent i = new Intent(this, BloodBankDoner.class);
        startActivity(i);
    }

    public void DonorProfile(View view)
    {
        Intent i = new Intent(this, DonorMyProfile.class);
        startActivity(i);
    }

    public void SearchAcceptors(View view)
    {
        Intent i = new Intent(this, SearchAcceptor.class);
        startActivity(i);
    }


}
