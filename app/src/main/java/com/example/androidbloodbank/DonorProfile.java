package com.example.androidbloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DonorProfile extends AppCompatActivity {

    TextView txtGender,txtBloodGroup,txtEmail,txtName,txtContactNumber,txtAddress;

    String userID;
    DatabaseReference databaseReference;

    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile);

        Intent intent = getIntent();
        userID= intent.getStringExtra("USERID");

        FirebaseDatabase.getInstance().getReference("Donors/"+ userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("image")){
                    Picasso.get().load(dataSnapshot.child("image").getValue(String.class)).into((ImageView) findViewById(R.id.imageView4));

                }

                txtName = findViewById(R.id.editText37);
                txtGender = findViewById(R.id.editText34);
                txtBloodGroup = findViewById(R.id.editText31);
                txtEmail = findViewById(R.id.editText32);
                txtContactNumber = findViewById(R.id.editText36);
                txtAddress = findViewById(R.id.editText33);

                String name = dataSnapshot.child("name").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String address = dataSnapshot.child("address").getValue(String.class);
                String blood = dataSnapshot.child("bloodGroup").getValue(String.class);
                String num = dataSnapshot.child("contact").getValue(String.class);
                String gender = dataSnapshot.child("gender").getValue(String.class);
                txtName.setText(name);
                txtEmail.setText(email);
                txtAddress.setText(address);
                txtBloodGroup.setText(blood);
                txtContactNumber.setText(num);
                txtGender.setText(gender);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void MedicalHistoryCheck(View v){
        Intent intent1 = new Intent(getBaseContext(), ViewMedicalHistory.class);
        intent1.putExtra("USERID", userID);
        startActivity(intent1);
    }

    public void Call(View v){
        MakePhoneCall(txtContactNumber.getText().toString());
    }

    private void MakePhoneCall(String number) {
        if(number.length() > 0)
        {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }
            else{
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }

    public void CheckLocation(View v){
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE );
        boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(statusOfGPS == true){
            Intent intent = new Intent(getBaseContext(), CurrentLocation.class);
            intent.putExtra("USERID", userID);
            startActivity(intent);
        }
        else{
            Toast.makeText(DonorProfile.this, "GPS is off, turn it on please!", Toast.LENGTH_SHORT).show();

        }

    }

}
