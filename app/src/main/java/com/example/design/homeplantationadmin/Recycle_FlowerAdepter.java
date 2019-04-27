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

class Recycle_FlowerAdepter extends  RecyclerView.Adapter<Recycle_FlowerAdepter.FlowerHolder>
{
    private  TabHome context;
    private FirebaseAuth  firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private  ArrayList<FlowerModel> flowerModelArrayList;

    public Recycle_FlowerAdepter(TabHome context, ArrayList<FlowerModel> flowerModelArrayList) {
        this.context=context;
        this.flowerModelArrayList=flowerModelArrayList;
    }

    @NonNull
    @Override
    public FlowerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fruit_row_card,viewGroup,false);
        return new FlowerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlowerHolder flowerHolder, int i) {
        FlowerModel flowerModel=flowerModelArrayList.get(i);
        firebaseAuth=FirebaseAuth.getInstance();
        final String name=flowerModel.getFlower_name();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        Glide.with(context).load(flowerModel.getFlower_url()).into(flowerHolder.imageView);
        final Query query= databaseReference.child("Flower").child(firebaseAuth.getCurrentUser().getUid()).orderByChild("flower_name").equalTo(flowerModel.getFlower_name());
        flowerHolder.delete.setOnClickListener(new View.OnClickListener() {
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
        flowerHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot datakey : dataSnapshot.getChildren())
                        {
                            String key =datakey.getKey();
                            Intent updatedata=new Intent(context.getContext(),UpdateFlowerData.class);
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
        return flowerModelArrayList.size();
    }

    public class FlowerHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private Button delete;
        private Button update;
        public FlowerHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.tabhomescreen_plantimage);
            delete=itemView.findViewById(R.id.rv_button_delete);
            update=itemView.findViewById(R.id.rv_button_update);
        }
    }
}
