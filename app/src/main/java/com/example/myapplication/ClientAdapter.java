package com.example.myapplication;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.MyViewHolder> {

    private List<remboursement> itemList;


    public ClientAdapter(List<remboursement> itemList) {
        this.itemList = itemList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        remboursement currentItem = itemList.get(position);

        holder.nameTextView.setText(String.valueOf(currentItem.nom));
        holder.quantiteTextView.setText(String.valueOf(currentItem.quantite));
        holder.prixTextView.setText(String.valueOf(currentItem.prix));
        holder.designationTextView.setText(String.valueOf(currentItem.description));
        holder.etatRemboursementTextView.setText(String.valueOf(currentItem.etat));
        // Utiliser Glide pour charger et afficher l'image à partir de l'URI
        Glide.with(holder.itemView.getContext())
                .load(Uri.parse(currentItem.imageUri))
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, quantiteTextView, prixTextView, designationTextView, etatRemboursementTextView;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameproduct);
            quantiteTextView = itemView.findViewById(R.id.quantiteproduct);
            prixTextView = itemView.findViewById(R.id.prixproduct);
            designationTextView = itemView.findViewById(R.id.designationproduct);
            etatRemboursementTextView = itemView.findViewById(R.id.etatremboursement);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
