package me.elmajni.sqliteroom;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonneDAO {
   @Query("SELECT * FROM personne")
    List<Personne> getAll();
   @Query("SELECT * FROM personne WHERE id IN (:personneIds)")
   List<Personne> loadAllByIds(int[] personneIds);
   @Query("SELECT * FROM personne WHERE name LIKE :name LIMIT 1") Personne findByName(String name);
    @Insert
    void insert(Personne personne);
    @Delete
    void delete(Personne personne);
}
