package com.example.design.homeplantationadmin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class UpdataSeedddata extends AppCompatActivity implements View.OnClickListener {
    private String key;
    private static final int PICK_IMG_CODE = 100;
    private ImageButton indoor_img;
    private ImageButton choose_image;
    private EditText indoor_name;
    private EditText indoor_decription;
    private EditText indoor_price;
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
        setContentView(R.layout.activity_updata_seedddata);
        key=getIntent().getStringExtra("datakey");
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        indoor_img=findViewById(R.id.updateseed_image);
        choose_image=findViewById(R.id.updateseed_Imagebutton_chooseimage);
        indoor_name=findViewById(R.id.updateseed_name_edittext);
        indoor_decription=findViewById(R.id.updateseeddecription_edittext);
        indoor_price=findViewById(R.id.updateseed_price_edittext);
        updateiteam=findViewById(R.id.updateseed_button);
        updateiteam.setOnClickListener(this);
        choose_image.setOnClickListener(this);
        databaseReference.child("SeedForPlant").child(firebaseAuth.getCurrentUser().getUid())
                .child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data_dec=dataSnapshot.child("seed_decription").getValue().toString();
                String data_name=dataSnapshot.child("seed_name").getValue().toString();
                String data_price=dataSnapshot.child("seed_price").getValue().toString();
                String data_image=dataSnapshot.child("seed_url").getValue().toString();

                indoor_decription.setText(data_dec);

                indoor_name.setText(data_name);
                indoor_price.setText(data_price);


                Glide.with(UpdataSeedddata.this).load(data_image).into(indoor_img);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.updateseed_Imagebutton_chooseimage:
                ChooseImage();
                break;
            case R.id.updateseed_button:
                updateseedplant();
                break;

        }
    }
    private void updateseedplant() {
        final String indoorname=indoor_name.getText().toString();
        final String description=indoor_decription.getText().toString();
        final String  indoorprice=indoor_price.getText().toString();
        if(TextUtils.isEmpty(indoorname))
        {
            Toast.makeText(this, "Enter name of Indoor Plant !", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(description))
        {
            Toast.makeText(this, "Enter Indoor Plant  description!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(indoorprice))
        {
            Toast.makeText(this, "Enter price of Indoor Plant!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(myuri))
        {
            Toast.makeText(this, "Enter pic of Indoor Plant pic!", Toast.LENGTH_SHORT).show();
            return;
        }
        databaseReference.child("Admin_User").child(firebaseAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nurrsery=dataSnapshot.child("user_name").getValue().toString();

                SeedModel seedModel= new SeedModel(indoorname,description,myuri,nurrsery,indoorprice);
                databaseReference.child("SeedForPlant").child(firebaseAuth.getCurrentUser().getUid()).push().setValue(seedModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UpdataSeedddata.this, "done", Toast.LENGTH_SHORT).show();
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void ChooseImage() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            return;
        }
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select Picture"),PICK_IMG_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==PICK_IMG_CODE && resultCode==RESULT_OK && data != null && data.getData()!=null )
        {
            selectimge=data.getData();
            indoor_img.setImageURI(selectimge);
            uploadImage();
        }
    }

    private void uploadImage() {
        final  StorageReference reference=storageReference.child("Image/"+ UUID.randomUUID().toString());
        reference.putFile(selectimge).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(UpdataSeedddata.this, "yes", Toast.LENGTH_SHORT).show();
                String path=reference.getPath();
//                        Toast.makeText(Userpicandotherdata.this, ""+path, Toast.LENGTH_SHORT).show();
                StorageReference storageRef =
                        FirebaseStorage.getInstance().getReference();
                storageRef.child(path).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        myuri= uri.toString();
                        Toast.makeText(UpdataSeedddata.this, ""+myuri, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdataSeedddata.this, "no", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

