package com.example.androidbloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MedicalHistoryCheck extends AppCompatActivity {

    TextView allergy,fitness,disease;
    String UserId;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history_check);

        allergy = findViewById(R.id.editText21);
        fitness = findViewById(R.id.editText23);
        disease = findViewById(R.id.editText24);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        UserId = currentFirebaseUser.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("MedicalHistory");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uid = snapshot.child("userId").getValue(String.class);
                    if(uid.equals(UserId)){
                        String all = snapshot.child("allergy").getValue(String.class);
                        String dis = snapshot.child("disease").getValue(String.class);
                        String fit = snapshot.child("fitness").getValue(String.class);
                        allergy.setText("Allergy: " + all);
                        fitness.setText("Finess: " +fit);
                        disease.setText("Disease: "+dis);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());
            }

        });
    }
}
