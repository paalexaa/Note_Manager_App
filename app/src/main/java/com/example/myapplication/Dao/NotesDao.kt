package com.example.myapplication.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.Model.Notes

@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=1")
    fun getFriendsNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=2")
    fun getWorkNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=3")
    fun getPersonalNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=4")
    fun getHomeNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=5")
    fun getFamilyNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes)
}