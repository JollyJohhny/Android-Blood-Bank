package com.example.androidbloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchDonor extends AppCompatActivity {

    Spinner spinner;
    DatabaseReference databaseReference;

    ListView listView ;
    public static ArrayList<ListType> AllProfiles;
    ListAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_donor);

        spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.BloodGroup, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Donors");

        listView = findViewById(R.id.ListId);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ShowProfiles();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }

    public void ShowProfiles(){
        AllProfiles = new ArrayList<ListType>();
        GetProfilesIntoList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getBaseContext(), DonorProfile.class);
                intent1.putExtra("USERID", AllProfiles.get(position).getId());
                startActivity(intent1);
            }
        });
    }

    public void GetProfilesIntoList(){
        AllProfiles = new ArrayList<ListType>();
        final String selectedGroup = spinner.getSelectedItem().toString();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String BloodGroup = snapshot.child("bloodGroup").getValue(String.class);
                    if(BloodGroup.equals(selectedGroup)){
                        String name = snapshot.child("name").getValue(String.class);
                        String contact = snapshot.child("contact").getValue(String.class);
                        String proId = snapshot.getKey();

                        ListType Profile = new ListType(proId,name,contact);
                        AllProfiles.add(Profile);
                    }
                }
                PopulateList();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());
            }

        });
    }

    public void PopulateList(){
        myAdapter = new ListAdapter(this, AllProfiles);
        listView.setAdapter(myAdapter);

        if(AllProfiles.size() == 0 && !spinner.getSelectedItem().toString().equals("Select Group")){
            Toast.makeText(SearchDonor.this, "No Profile with this Blood group!", Toast.LENGTH_SHORT).show();

        }
    }


}
