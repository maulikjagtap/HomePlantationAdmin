package com.example.design.homeplantationadmin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TabHome extends Fragment {
    private View view;
    private RecyclerView fruit;
    private RecyclerView flower;
    private RecyclerView indoor;
    private RecyclerView outdoor;
    private RecyclerView vegetable;
    private RecyclerView hybrid;
    private RecyclerView special;
    private RecyclerView seed;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.tabhomescreen,container,false);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        fruit=view.findViewById(R.id.tabhomescreen_fruit_recycle);
        flower=view.findViewById(R.id.tabhomescreen_flower_recycle);
        indoor=view.findViewById(R.id.tabhomescreen_indoord_recycle);
        outdoor=view.findViewById(R.id.tabhomescreen_outdoor_recycle);
        vegetable=view.findViewById(R.id.tabhomescreen_vegetable_recycle);
        seed=view.findViewById(R.id.tabhomescreen_seed_recycle);
        hybrid=view.findViewById(R.id.tabhomescreen_hybrid_recycle);
//        special=view.findViewById(R.id.tabhomescreen_special_recycle);

        final ArrayList<FruitModel> fruitModelArrayList =new ArrayList<>();
        final ArrayList<FlowerModel> flowerModelArrayList =new ArrayList<>();
        final ArrayList<IndoorModel> indoorModelArrayList =new ArrayList<>();
        final ArrayList<OutdoorModel> outdoorModelArrayList =new ArrayList<>();
        final ArrayList<VegetableModel> vegetableModelArrayList =new ArrayList<>();
        final ArrayList<SeedModel> seedModelArrayList =new ArrayList<>();
        final ArrayList<HybridModel> hybridModelArrayList =new ArrayList<>();
        final ArrayList<SpecialplantModel> specialplantModelArrayList =new ArrayList<>();

        databaseReference.child("FruitPlant").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fruitModelArrayList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {

                    FruitModel fruitModel= snapshot.getValue(FruitModel.class);
                    fruitModelArrayList.add(fruitModel);
                }
                Recycle_FruitAdepter recycle_fruitAdepter = new Recycle_FruitAdepter(TabHome.this,fruitModelArrayList);
                    fruit.setAdapter(recycle_fruitAdepter);
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(view.getContext());
                ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
                fruit.setLayoutManager(layoutManager);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseReference.child("Flower")
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                flowerModelArrayList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {

                    FlowerModel flowerModel= snapshot.getValue(FlowerModel.class);
                    flowerModelArrayList.add(flowerModel);
                }
                Recycle_FlowerAdepter recycle_flowerAdepter = new Recycle_FlowerAdepter(TabHome.this,flowerModelArrayList);
                flower.setAdapter(recycle_flowerAdepter);
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(view.getContext());
                ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
                flower.setLayoutManager(layoutManager);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("IndoorPlant")
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        indoorModelArrayList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren())
                        {

                            IndoorModel indoorModel= snapshot.getValue(IndoorModel.class);
                            indoorModelArrayList.add(indoorModel);
                        }
                        Recycle_IndoorAdepter recycle_indoorAdepter = new Recycle_IndoorAdepter(TabHome.this,indoorModelArrayList);
                        indoor.setAdapter(recycle_indoorAdepter);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(view.getContext());
                        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
                        indoor.setLayoutManager(layoutManager);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        databaseReference.child("OutdoorPlant")
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        outdoorModelArrayList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren())
                        {

                            OutdoorModel outdoorModel= snapshot.getValue(OutdoorModel.class);
                            outdoorModelArrayList.add(outdoorModel);
                        }
                        Recycle_OutdoorAdepter recycle_outdoorAdepter = new Recycle_OutdoorAdepter(TabHome.this,outdoorModelArrayList);
                        outdoor.setAdapter(recycle_outdoorAdepter);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(view.getContext());
                        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
                        outdoor.setLayoutManager(layoutManager);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        databaseReference.child("VegetablePlant")
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            vegetableModelArrayList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren())
                        {

                            VegetableModel vegetableModel= snapshot.getValue(VegetableModel.class);
                            vegetableModelArrayList.add(vegetableModel);
                        }
                        Recycle_VegetableAdepter recycle_vegetableAdepter = new Recycle_VegetableAdepter(TabHome.this,vegetableModelArrayList);
                        vegetable.setAdapter(recycle_vegetableAdepter);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(view.getContext());
                        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
                        vegetable.setLayoutManager(layoutManager);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        databaseReference.child("SeedForPlant")
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        specialplantModelArrayList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren())
                        {

                            SeedModel seedModel= snapshot.getValue(SeedModel.class);
                            seedModelArrayList.add(seedModel);
                        }
                        Recycle_SeddAdepter recycle_seddAdepter = new Recycle_SeddAdepter(TabHome.this,seedModelArrayList);
                        seed.setAdapter(recycle_seddAdepter);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(view.getContext());
                        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
                        seed.setLayoutManager(layoutManager);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        databaseReference.child("HybridPlant")
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        hybridModelArrayList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren())
                        {

                            HybridModel hybridModel= snapshot.getValue(HybridModel.class);
                            hybridModelArrayList.add(hybridModel);
                        }
                        Recycle_HybridAdepter recycle_hybridAdepter = new Recycle_HybridAdepter(TabHome.this,hybridModelArrayList);
                        hybrid.setAdapter(recycle_hybridAdepter);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(view.getContext());
                        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
                        hybrid.setLayoutManager(layoutManager);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


        return view;
    }

}
