package me.elmajni.sqliteroomappperson;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface DAO {
    // Insert query
    @Insert(onConflict =REPLACE)
    void insert(Personne mainData);
    // Delete query

    @Delete
    void reset(List<Personne> mainData);

    // Update query
    @Query("UPDATE PERSONNE SET name= :name where ID=:ID")
    void update(int ID, String name);

    // Get all data query
    @Query("SELECT * FROM PERSONNE")
    List<Personne> getAll();

    @Delete
    void delete(Personne personne);

    @Query("select * from PERSONNE where id = :id")
    Personne loadPersonneById(int id);
}
