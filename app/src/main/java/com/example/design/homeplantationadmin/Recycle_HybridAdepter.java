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

class Recycle_HybridAdepter extends RecyclerView.Adapter<Recycle_HybridAdepter.SubHolde> {
    private TabHome context;
    private ArrayList<HybridModel> hybridModelArrayList;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public Recycle_HybridAdepter(TabHome context, ArrayList<HybridModel> hybridModelArrayList) {
        this.context=context;
        this.hybridModelArrayList=hybridModelArrayList;
    }

    @NonNull
    @Override
    public SubHolde onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fruit_row_card,viewGroup,false);
        return new SubHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubHolde subHolde, int i) {
        HybridModel hybridModel=hybridModelArrayList.get(i);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        Glide.with(context).load(hybridModel.getHybrid_url()).into(subHolde.imageView);
        final Query query= databaseReference.child("HybridPlant").child(firebaseAuth.getCurrentUser().getUid()).orderByChild("hybrid_name").equalTo(hybridModel.getHybrid_name());
        subHolde.delete.setOnClickListener(new View.OnClickListener() {
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

        subHolde.upadate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot datakey : dataSnapshot.getChildren())
                        {
                            String key =datakey.getKey();
                            Intent updatedata=new Intent(context.getContext(),UpdataHybridplantdata.class);
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
        return hybridModelArrayList.size();
    }

    public class SubHolde extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private Button delete;
        private Button upadate;
        public SubHolde(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.tabhomescreen_plantimage);
            delete=itemView.findViewById(R.id.rv_button_delete);
            upadate=itemView.findViewById(R.id.rv_button_update);
        }
    }
}
