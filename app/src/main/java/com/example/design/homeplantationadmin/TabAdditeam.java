package com.example.design.homeplantationadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public  class TabAdditeam  extends Fragment implements View.OnClickListener {

    private View view;
    private CardView flower;
    private CardView vegetable;
    private CardView fruit;
    private CardView seed;
    private CardView outdoor;
    private CardView indoor;
    private CardView hybrid;
    private CardView special;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.tabadditeamscreen,container,false);
        vegetable=view.findViewById(R.id.additeam_vegetable);
        fruit=view.findViewById(R.id.additeam_fruit);
        seed=view.findViewById(R.id.additeam_seeds);
        outdoor=view.findViewById(R.id.additeam_outdoor);
        indoor=view.findViewById(R.id.additeam_indoor);
        flower=view.findViewById(R.id.additeam_flower);
        hybrid=view.findViewById(R.id.additeam_hybrid);
        special=view.findViewById(R.id.additeam_special);
        flower.setOnClickListener(this);
        fruit.setOnClickListener(this);
        vegetable.setOnClickListener(this);
        seed.setOnClickListener(this);
        outdoor.setOnClickListener(this);
        indoor.setOnClickListener(this);
        hybrid.setOnClickListener(this);
        special.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.additeam_flower:
                Intent flower=new Intent(v.getContext(),Addflower.class);
                startActivity(flower);

                break;
            case R.id.additeam_vegetable:
                Toast.makeText(v.getContext(), "hiii", Toast.LENGTH_SHORT).show();
                Intent vegetable=new Intent(v.getContext(),Addvegetable.class);
                startActivity(vegetable);
                break;
            case R.id.additeam_fruit:
                Intent fruit=new Intent(v.getContext(),AddFruitsPlant.class);
                startActivity(fruit);
                break;
            case R.id.additeam_seeds:
                Intent seed=new Intent(v.getContext(),AddSeedofPlant.class);
                startActivity(seed);
                break;
            case R.id.additeam_outdoor:
                Intent outdoor=new Intent(v.getContext(),AddOutdoorPlant.class);
                startActivity(outdoor);
                break;
            case R.id.additeam_indoor:
                Intent indoor=new Intent(v.getContext(),AddIndoorPlant.class);
                startActivity(indoor);
                break;
            case R.id.additeam_hybrid:
                Intent hybrid=new Intent(v.getContext(),AddHybridPlnat.class);
                startActivity(hybrid);
                break;
            case R.id.additeam_special:
                Intent special=new Intent(v.getContext(),AddSpecialPlant.class);
                startActivity(special);
                break;

        }

    }
}
