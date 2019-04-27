package com.example.design.homeplantationadmin;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UpdateFlowerData extends AppCompatActivity implements View.OnClickListener {
    private String key;
    private static final int PICK_IMG_CODE = 100;
    private ImageButton flower_img;
    private ImageButton choose_image;
    private EditText flower_name;
    private EditText flower_decription;
    private EditText flower_price;
    private Button updateiteam;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private Uri selectimge;
    private String myuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_flower_data);
        key=getIntent().getStringExtra("datakey");
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        flower_img=findViewById(R.id.updateflower_image);
        choose_image=findViewById(R.id.updateflower_Imagebutton_chooseimage);
        flower_name=findViewById(R.id.updateflower_name_edittext);
        flower_decription=findViewById(R.id.updateflowerdecription_edittext);
        flower_price=findViewById(R.id.updateflower_price_edittext);
        updateiteam=findViewById(R.id.updateflower_button);
        updateiteam.setOnClickListener(this);
        choose_image.setOnClickListener(this);
        databaseReference.child("Flower").child(firebaseAuth.getCurrentUser().getUid())
                .child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data_dec=dataSnapshot.child("flower_decription").getValue().toString();
                String data_name=dataSnapshot.child("flower_name").getValue().toString();
                String data_price=dataSnapshot.child("plant_price").getValue().toString();
                String data_image=dataSnapshot.child("flower_url").getValue().toString();

                flower_decription.setText(data_dec);

                flower_name.setText(data_name);
                flower_price.setText(data_price);


                Glide.with(UpdateFlowerData.this).load(data_image).into(flower_img);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.updateflower_Imagebutton_chooseimage:
                break;
            case R.id.updatefruit_button:
                break;
        }


    }
}
