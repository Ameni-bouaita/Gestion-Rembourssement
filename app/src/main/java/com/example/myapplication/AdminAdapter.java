package com.example.myapplication;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.MyViewHolder> {

    private List<remboursement> itemList;

    public AdminAdapter(List<remboursement> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin, parent, false);
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
        // Charger et afficher l'image
       /* Glide.with(holder.itemView.getContext())
                .load(Uri.parse(currentItem.imageUri))  // Assurez-vous d'avoir une URI valide ici
                .into(holder.imageView);*/

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        TextView nameTextView, quantiteTextView, prixTextView, designationTextView, etatRemboursementTextView;
        Button accept, decline;


        public MyViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameproduct);
            quantiteTextView = itemView.findViewById(R.id.quantiteproduct);
            prixTextView = itemView.findViewById(R.id.prixproduct);
            designationTextView = itemView.findViewById(R.id.designationproduct);
            etatRemboursementTextView = itemView.findViewById(R.id.etatremboursement);
            accept = itemView.findViewById(R.id.accept);
            decline = itemView.findViewById(R.id.decline);
            /*imageView = itemView.findViewById(R.id.imageView);*/


            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onAcceptButtonClick(getAdapterPosition());
                }
            });

            decline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onDeclineButtonClick(getAdapterPosition());
                }
            });
        }

        private void onAcceptButtonClick(int position) {
            // Implement the logic to update the 'etat' to 'accepted' in the database
            updateRemboursementEtat(position, "accepted");
        }

        private void onDeclineButtonClick(int position) {
            // Implement the logic to update the 'etat' to 'declined' in the database
            updateRemboursementEtat(position, "declined");
        }

        private void updateRemboursementEtat(int position, String newEtat) {
            remboursement currentItem = itemList.get(position);
            currentItem.etat = newEtat;

            // Get the reference to the outer class
            admin activity = (admin) itemView.getContext();

            // Update the 'etat' in the database
            new admin.UpdateRemboursementEtatTask(activity, currentItem).execute();
        }}}
