package com.example.design.homeplantationadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AddData extends AppCompatActivity implements View.OnClickListener {
    private Button addacs;
    private Button addgarden;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        addacs=findViewById(R.id.addaccessories);
        addgarden=findViewById(R.id.addgardenimage);
        addacs.setOnClickListener(this);
        addgarden.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.addaccessories:
                    startActivity(new Intent(AddData.this,AddAcessories.class));
                break;
            case R.id.addgardenimage:
                startActivity(new Intent(AddData.this,AddGarden.class));
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.off,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logoff:
                startActivity(new Intent(AddData.this,Login_Activity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
