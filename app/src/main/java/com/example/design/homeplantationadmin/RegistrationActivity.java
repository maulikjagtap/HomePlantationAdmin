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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PICK_IMG_CODE = 100;
    private ImageButton user_profile;
    private ImageButton choose_image;
    private EditText user_name;
    private EditText user_email;
    private EditText user_password;
    private EditText user_number;
    private Button sigup;
    private Button login;
    private Button forgot_pasword;
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
        setContentView(R.layout.activity_registration);
//        firebase
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        user_profile=findViewById(R.id.RegistratonScreen_Imagebutton_profileimage);
        choose_image=findViewById(R.id.RegistratonScreen_Imagebutton_chooseimage);
        user_name=findViewById(R.id.RegistratonScreen_username_edittext);
        user_email=findViewById(R.id.RegistratonScreen_email_edittext);
        user_password=findViewById(R.id.RegistratonScreen_pass_edittext);
        user_number=findViewById(R.id.RegistratonScreen_phone_edittext);
        login=findViewById(R.id.RegistratonScreen_signin_button);
        sigup=findViewById(R.id.RegistratonScreen_signup_button);
        forgot_pasword=findViewById(R.id.RegistratonScreen_forgot_password_button);

        choose_image.setOnClickListener(this);
        login.setOnClickListener(this);
        sigup.setOnClickListener(this);
        forgot_pasword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.RegistratonScreen_Imagebutton_chooseimage:
                ChooseImage();
                break;
            case R.id.RegistratonScreen_signup_button:
                Userlogup();
                 break;
            case R.id.RegistratonScreen_forgot_password_button:
                break;
            case R.id.RegistratonScreen_signin_button:
                break;
        }

    }
    private void Userlogup() {
        final String username=user_name.getText().toString();
        final String useremail=user_email.getText().toString();
        final String userpass=user_password.getText().toString();
        final String usernumber=user_number.getText().toString();
        if(TextUtils.isEmpty(useremail))
        {
            Toast.makeText(this, "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userpass))
        {
            Toast.makeText(this, "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "Enter user name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(myuri))
        {
            Toast.makeText(this, "Choose Image", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userpass.length()<6)
        {
            Toast.makeText(this, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(usernumber))
        {
            Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (usernumber.length()<10)
        {
            Toast.makeText(this, "Phone number is not less then 10 digit!", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(useremail,userpass).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(!task.isSuccessful())
                {
                    Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {

                    Toast.makeText(RegistrationActivity.this, "yes", Toast.LENGTH_SHORT).show();

                    User user=new User(username,useremail,usernumber,myuri);
                    Log.e("user",""+user );
                    databaseReference.child("Admin_User").child(firebaseAuth.getCurrentUser().getUid())
                            .setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent loginactvity=new Intent(RegistrationActivity.this,Login_Activity.class);
                            startActivity(loginactvity);
                            finish();
                        }
                    });

                }
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
            user_profile.setImageURI(selectimge);
            uploadImage();
        }
    }

    private void uploadImage() {
        final  StorageReference reference=storageReference.child("Image/"+ UUID.randomUUID().toString());
        reference.putFile(selectimge).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(RegistrationActivity.this, "yes", Toast.LENGTH_SHORT).show();
                String path=reference.getPath();
//                        Toast.makeText(Userpicandotherdata.this, ""+path, Toast.LENGTH_SHORT).show();
                StorageReference storageRef =
                        FirebaseStorage.getInstance().getReference();
                storageRef.child(path).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        myuri= uri.toString();
                        Toast.makeText(RegistrationActivity.this, ""+myuri, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistrationActivity.this, "no", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
