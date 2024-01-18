package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface remboursementDAO {
    @Query("SELECT * FROM remboursement")
    List<remboursement> getAllRemboursements();

    @Insert
    void insertRemboursement(remboursement r);

    @Delete
    void deleteRemboursement(remboursement r);

    @Update
    void updateRemboursementEtat(remboursement r);
}
