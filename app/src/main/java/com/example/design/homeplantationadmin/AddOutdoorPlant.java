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

public class AddOutdoorPlant extends AppCompatActivity implements View.OnClickListener {
    private static final int PICK_IMG_CODE = 100;
    private ImageButton outdoor_img;
    private ImageButton choose_image;
    private EditText outdoor_name;
    private EditText outdoor_decription;
    private EditText outdoor_price;
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
        setContentView(R.layout.activity_add_outdoor_plant);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        outdoor_img=findViewById(R.id.addoutdoordplant_image);
        choose_image=findViewById(R.id.addoutdoordplant_Imagebutton_chooseimage);
        outdoor_name=findViewById(R.id.addoutdoordplant_name_edittext);
        outdoor_decription=findViewById(R.id.addoutdoordplant_decription_edittext);
        outdoor_price=findViewById(R.id.addoutdoordplant_price_edittext);
        additeam=findViewById(R.id.addoutdoordplant_button);
        additeam.setOnClickListener(this);
        choose_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.addoutdoordplant_Imagebutton_chooseimage:
                ChooseImage();
                break;
            case R.id.addoutdoordplant_button:
                addOutdoorPlant();
                break;

        }

    }

    private void addOutdoorPlant() {
        final String outdoorname=outdoor_name.getText().toString();
        final String description=outdoor_decription.getText().toString();
        final String  outdoorprice=outdoor_price.getText().toString();
        if(TextUtils.isEmpty(outdoorname))
        {
            Toast.makeText(this, "Enter name of Indoor Plant !", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(description))
        {
            Toast.makeText(this, "Enter Indoor Plant  description!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(outdoorprice))
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

                OutdoorModel outdoorModel= new OutdoorModel(outdoorname,description,myuri,nurrsery,outdoorprice);
                databaseReference.child("OutdoorPlant").child(firebaseAuth.getCurrentUser().getUid()).push().setValue(outdoorModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddOutdoorPlant.this, "done", Toast.LENGTH_SHORT).show();
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
            outdoor_img.setImageURI(selectimge);
            uploadImage();
        }
    }

    private void uploadImage() {
        final  StorageReference reference=storageReference.child("Image/"+ UUID.randomUUID().toString());
        reference.putFile(selectimge).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddOutdoorPlant.this, "yes", Toast.LENGTH_SHORT).show();
                String path=reference.getPath();
//                        Toast.makeText(Userpicandotherdata.this, ""+path, Toast.LENGTH_SHORT).show();
                StorageReference storageRef =
                        FirebaseStorage.getInstance().getReference();
                storageRef.child(path).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        myuri= uri.toString();
                        Toast.makeText(AddOutdoorPlant.this, ""+myuri, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddOutdoorPlant.this, "no", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
