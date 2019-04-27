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

public class Addflower extends AppCompatActivity implements View.OnClickListener {
    private static final int PICK_IMG_CODE = 100;
    private ImageButton flower_img;
    private ImageButton choose_image;
    private EditText flower_name;
    private EditText flower_decription;
    private EditText flower_price;
    private Button additeam;
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
        setContentView(R.layout.activity_addflower);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        flower_img=findViewById(R.id.addflower_image);
        choose_image=findViewById(R.id.addfolower_Imagebutton_chooseimage);
        flower_name=findViewById(R.id.addflower_name_edittext);
        flower_decription=findViewById(R.id.addflowerdecription_edittext);
        flower_price=findViewById(R.id.addflower_price_edittext);
        additeam=findViewById(R.id.addflower_button);
        additeam.setOnClickListener(this);
        choose_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.addfolower_Imagebutton_chooseimage:
                ChooseImage();
                break;
            case R.id.addflower_button:
                addflower();
                break;

        }

    }

    private void addflower() {
        final String flowername=flower_name.getText().toString();
        final String description=flower_decription.getText().toString();
        final String  flowerprice=flower_price.getText().toString();
        if(TextUtils.isEmpty(flowername))
        {
            Toast.makeText(this, "Enter name of flower!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(description))
        {
            Toast.makeText(this, "Enter flower description!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(flowerprice))
        {
            Toast.makeText(this, "Enter price of flower!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(myuri))
        {
            Toast.makeText(this, "Enter pic of flower!", Toast.LENGTH_SHORT).show();
            return;
        }
        databaseReference.child("Admin_User").child(firebaseAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nurrsery=dataSnapshot.child("user_name").getValue().toString();

                FlowerModel flowerModel=new FlowerModel(flowername,description,myuri,nurrsery,flowerprice);
                databaseReference.child("Flower").child(firebaseAuth.getCurrentUser().getUid()).push().setValue(flowerModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Addflower.this, "done", Toast.LENGTH_SHORT).show();
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
            flower_img.setImageURI(selectimge);
            uploadImage();
        }
    }

    private void uploadImage() {
        final  StorageReference reference=storageReference.child("Image/"+ UUID.randomUUID().toString());
        reference.putFile(selectimge).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Addflower.this, "yes", Toast.LENGTH_SHORT).show();
                String path=reference.getPath();
//                        Toast.makeText(Userpicandotherdata.this, ""+path, Toast.LENGTH_SHORT).show();
                StorageReference storageRef =
                        FirebaseStorage.getInstance().getReference();
                storageRef.child(path).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        myuri= uri.toString();
                        Toast.makeText(Addflower.this, ""+myuri, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Addflower.this, "no", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
