package com.example.androidbloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BloodBankDoner extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    int APositive=0,ANegative=0,BPositve=0,BNegative=0,ABPositive=0,ABNegative=0,OPositive=0;
    TextView txtAPos,txtANeg,txtBPos,txtBNeg,txtABPos,txtABNeg,txtOPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_doner);

        txtAPos = findViewById(R.id.textView33);
        txtANeg = findViewById(R.id.textView34);
        txtBPos = findViewById(R.id.textView35);
        txtBNeg = findViewById(R.id.textView36);
        txtABPos = findViewById(R.id.textView37);
        txtABNeg = findViewById(R.id.textView38);
        txtOPos = findViewById(R.id.textView2);

        databaseReference = FirebaseDatabase.getInstance().getReference("Donors");

        GetCounts();
    }

    public void GetCounts(){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String bloodGroup = snapshot.child("bloodGroup").getValue(String.class);
                    if (bloodGroup.equals("A+")) {
                        APositive++;
                    }
                    if (bloodGroup.equals("A-")) {
                        ANegative++;
                    }
                    if (bloodGroup.equals("B+")) {
                        BPositve++;
                    }
                    if (bloodGroup.equals("B-")) {
                        BNegative++;
                    }
                    if (bloodGroup.equals("AB+")) {
                        ABPositive++;
                    }
                    if (bloodGroup.equals("AB-")) {
                        ABNegative++;
                    }
                    if (bloodGroup == "O+") {
                        OPositive++;
                    }


                }
                ShowCounts();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());
            }

        });
    }

    public void ShowCounts(){
        txtOPos.setText(Integer.toString(OPositive));
        txtAPos.setText(Integer.toString(APositive));
        txtANeg.setText(Integer.toString(ANegative));
        txtBPos.setText(Integer.toString(BPositve));
        txtBNeg.setText(Integer.toString(BNegative));
        txtABPos.setText(Integer.toString(ABPositive));
        txtABNeg.setText(Integer.toString(ABNegative));

    }
}
