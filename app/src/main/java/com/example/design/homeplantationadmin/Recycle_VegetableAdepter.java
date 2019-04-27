package com.example.design.homeplantationadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

class Recycle_VegetableAdepter extends RecyclerView.Adapter<Recycle_VegetableAdepter.VegHolder> {
    private TabHome context;
    private ArrayList<VegetableModel> vegetableModelArrayList;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    public Recycle_VegetableAdepter(TabHome tabHome, ArrayList<VegetableModel> vegetableModelArrayList) {
        this.context=tabHome;
        this.vegetableModelArrayList=vegetableModelArrayList;
    }

    @NonNull
    @Override
    public VegHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View  view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fruit_row_card,viewGroup,false);
        return  new VegHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VegHolder vegHolder, int i) {
        VegetableModel vegetableModel=vegetableModelArrayList.get(i);
        firebaseAuth= FirebaseAuth.getInstance();

        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        Glide.with(context).load(vegetableModel.getVegetable_url()).into(vegHolder.imageView);
        final Query query= databaseReference.child("VegetablePlant").child(firebaseAuth.getCurrentUser().getUid()).orderByChild("vegetable_name").equalTo(vegetableModel.getVegetable_name());
        vegHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot :dataSnapshot.getChildren())
                        {
                            Toast.makeText(context.getContext(), ""+dataSnapshot.getKey(), Toast.LENGTH_SHORT).show(); dataSnapshot.getKey();
                            snapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        vegHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot datakey : dataSnapshot.getChildren())
                        {
                            String key =datakey.getKey();
                            Intent updatedata=new Intent(context.getContext(),UpdateVegetableData.class);
                            updatedata.putExtra("datakey",key);
                            context.startActivity(updatedata);


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return vegetableModelArrayList.size();
    }

    public class VegHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private Button delete;
        private  Button update;
        public VegHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.tabhomescreen_plantimage);
            delete=itemView.findViewById(R.id.rv_button_delete);
            update=itemView.findViewById(R.id.rv_button_update);
        }
    }
}
