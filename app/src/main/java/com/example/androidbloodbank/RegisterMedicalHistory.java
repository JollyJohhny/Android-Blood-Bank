package com.example.androidbloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterMedicalHistory extends AppCompatActivity{

    EditText allergy,fitness,disease;
    String UserId;

    DatabaseReference databaseReference;

    Location current_location;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_medical_history);

        allergy = findViewById(R.id.editText21);
        fitness = findViewById(R.id.editText23);
        disease = findViewById(R.id.editText24);

        Intent intent = getIntent();
        UserId= intent.getStringExtra("USERID");


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


    }

    public void Submit(View v){
        databaseReference = FirebaseDatabase.getInstance().getReference("MedicalHistory");
        MedicalType report = new MedicalType(UserId,allergy.getText().toString(),fitness.getText().toString(),disease.getText().toString());
        String reportId = databaseReference.push().getKey();
        databaseReference.child(reportId).setValue(report);
        Toast.makeText(RegisterMedicalHistory.this, "Medical record submitted!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, HomePage.class);
        startActivity(i);
    }

    public void GetLocation(View v){
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE );
        boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(statusOfGPS == true){
            fetchLastLocation();
        }
        else{
            Toast.makeText(RegisterMedicalHistory.this, "GPS is off, turn it on please and then click!", Toast.LENGTH_SHORT).show();

        }

    }

    private void fetchLastLocation() {
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    current_location = location;
                    databaseReference = FirebaseDatabase.getInstance().getReference("Locations");
                    String loc_id = databaseReference.push().getKey();
                    LocationType loc = new LocationType(UserId,location);
                    databaseReference.child(loc_id).setValue(loc);
                    Toast.makeText(RegisterMedicalHistory.this, "Location Saved!", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(),current_location.getLatitude()+""+current_location.getLongitude(),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


}
