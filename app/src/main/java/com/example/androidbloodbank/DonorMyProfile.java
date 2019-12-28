package com.example.androidbloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DonorMyProfile extends AppCompatActivity {

    TextView txtGender,txtBloodGroup,txtEmail;
    EditText txtName,txtContactNumber,txtAddress;

    String userID;
    DatabaseReference databaseReference;
    ImageView ProfileImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_my_profile);



        txtName = findViewById(R.id.editText37);
        txtGender = findViewById(R.id.editText34);
        txtBloodGroup = findViewById(R.id.editText31);
        txtEmail = findViewById(R.id.editText32);
        txtContactNumber = findViewById(R.id.editText36);
        txtAddress = findViewById(R.id.editText33);

        ProfileImage = findViewById(R.id.imageView4);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        userID = currentFirebaseUser.getUid();

        FirebaseDatabase.getInstance().getReference("Donors/"+FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("image")){
                    Picasso.get().load(dataSnapshot.child("image").getValue(String.class)).into(ProfileImage);

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
        Intent i = new Intent(this, MedicalHistoryCheck.class);
        startActivity(i);
    }

    public  void SaveChanges(View v){
        databaseReference = FirebaseDatabase.getInstance().getReference("Donors");

        try {
            databaseReference.child(userID).child("name").setValue(txtName.getText().toString());
            databaseReference.child(userID).child("address").setValue(txtAddress.getText().toString());
            databaseReference.child(userID).child("contact").setValue(txtContactNumber.getText().toString());

            Toast.makeText(DonorMyProfile.this, "Profile Updated!", Toast.LENGTH_SHORT).show();

            finish();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
