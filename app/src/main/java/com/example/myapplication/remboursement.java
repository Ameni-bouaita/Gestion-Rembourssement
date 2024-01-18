package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "remboursement")
public class remboursement {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nom")
    public String nom;

    @ColumnInfo(name = "prix")
    public int prix;

    @ColumnInfo(name = "quantite")
    public int quantite;

    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "image_uri")
    public static String imageUri;

    @ColumnInfo(name = "etat")
    public String etat;

    public remboursement() {
    }

    @Override
    public String toString() {
        return "remboursement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", quantite=" + quantite +
                ", description='" + description + '\'' +
                ", imageUri='" + imageUri + '\'' +
                ", etat='" + etat + '\'' +
                '}';
    }
}
