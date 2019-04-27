package com.example.design.homeplantationadmin;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import java.util.zip.Inflater;

class Recycle_FruitAdepter extends  RecyclerView.Adapter<Recycle_FruitAdepter.FruitHolder> {
    private TabHome context;
    private FirebaseAuth  firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private  ArrayList<FruitModel> fruitModelArrayList;

    public Recycle_FruitAdepter(TabHome context, ArrayList<FruitModel> fruitModelArrayList) {
        this.context=context;
        this.fruitModelArrayList=fruitModelArrayList;
    }

    @NonNull
    @Override
    public FruitHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fruit_row_card,viewGroup,false);
        return new FruitHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FruitHolder fruitHolder, final int i) {
        final FruitModel fruitModel=fruitModelArrayList.get(i);
       final   int position= i;


        firebaseAuth=FirebaseAuth.getInstance();
        final String name=fruitModel.getFruit_name();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        Glide.with(context).load(fruitModel.getFruit_url()).into(fruitHolder.imageView);
        final Query query= databaseReference.child("FruitPlant").child(firebaseAuth.getCurrentUser().getUid()).orderByChild("fruit_name").equalTo(fruitModel.getFruit_name());
            fruitHolder.delete.setOnClickListener(new View.OnClickListener() {
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

            fruitHolder.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot datakey : dataSnapshot.getChildren())
                            {
                                String key =datakey.getKey();
                                Intent updatedata=new Intent(context.getContext(),UpdateFruitData.class);
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
        return fruitModelArrayList.size();
    }

    public  class FruitHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private Button delete;
        private Button update;
        public FruitHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.tabhomescreen_plantimage);
            delete=itemView.findViewById(R.id.rv_button_delete);
            update=itemView.findViewById(R.id.rv_button_update);

        }
    }


}
