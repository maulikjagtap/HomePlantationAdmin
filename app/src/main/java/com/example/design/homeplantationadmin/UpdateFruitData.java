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

public class UpdateFruitData extends AppCompatActivity implements View.OnClickListener {
    private String key;
    private static final int PICK_IMG_CODE = 100;
    private ImageButton fruit_img;
    private ImageButton choose_image;
    private EditText fruit_name;
    private EditText fruit_decription;
    private EditText fruit_price;
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
        setContentView(R.layout.activity_update_fruit_data);
        key=getIntent().getStringExtra("datakey");
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        fruit_img=findViewById(R.id.updatefruit_image);
        choose_image=findViewById(R.id.updatefruit_Imagebutton_chooseimage);
        fruit_name=findViewById(R.id.updatefruit_name_edittext);
        fruit_decription=findViewById(R.id.updatefruitdecription_edittext);
        fruit_price=findViewById(R.id.updatefruit_price_edittext);
        updateiteam=findViewById(R.id.updatefruit_button);
        updateiteam.setOnClickListener(this);
        choose_image.setOnClickListener(this);
        databaseReference.child("FruitPlant").child(firebaseAuth.getCurrentUser().getUid())
                .child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data_dec=dataSnapshot.child("fruit_decription").getValue().toString();
                String data_name=dataSnapshot.child("fruit_name").getValue().toString();
                String data_price=dataSnapshot.child("fruit_price").getValue().toString();
                String data_image=dataSnapshot.child("fruit_url").getValue().toString();

                fruit_decription.setText(data_dec);

                fruit_name.setText(data_name);
                fruit_price.setText(data_price);


                Glide.with(UpdateFruitData.this).load(data_image).into(fruit_img);

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
            case  R.id.updatefruit_Imagebutton_chooseimage:
                ChooseImage();
                break;
            case R.id.updatefruit_button:
                updatefruit();
                break;
        }
    }

    private void updatefruit() {
        final String fruitname=fruit_name.getText().toString();
        final String description=fruit_decription.getText().toString();
        final String  fruitprice=fruit_price.getText().toString();
        if(TextUtils.isEmpty(fruitname))
        {
            Toast.makeText(this, "Enter name of vegetable plant!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(description))
        {
            Toast.makeText(this, "Enter vegetable plant description!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(fruitprice))
        {
            Toast.makeText(this, "Enter price of vegetable price!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(myuri))
        {
            Toast.makeText(this, "Enter pic of vegetable plant pic!", Toast.LENGTH_SHORT).show();
            return;
        }
        databaseReference.child("Admin_User").child(firebaseAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nurrsery=dataSnapshot.child("user_name").getValue().toString();

                FruitModel fruitModel=new FruitModel(fruitname,description,myuri,nurrsery,fruitprice);
                databaseReference.child("FruitPlant").child(firebaseAuth.getCurrentUser().getUid()).child(key).setValue(fruitModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                       startActivity(new Intent(UpdateFruitData.this,TabHome.class));
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
            fruit_img.setImageURI(selectimge);
            uploadImage();
        }
    }

    private void uploadImage() {
        final  StorageReference reference=storageReference.child("Image/"+ UUID.randomUUID().toString());
        reference.putFile(selectimge).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(UpdateFruitData.this, "yes", Toast.LENGTH_SHORT).show();
                String path=reference.getPath();
//                        Toast.makeText(Userpicandotherdata.this, ""+path, Toast.LENGTH_SHORT).show();
                StorageReference storageRef =
                        FirebaseStorage.getInstance().getReference();
                storageRef.child(path).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        myuri= uri.toString();
                        Toast.makeText(UpdateFruitData.this, ""+myuri, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateFruitData.this, "no", Toast.LENGTH_SHORT).show();
            }
        });
    }
}