package com.example.myapplication.Repository

import androidx.lifecycle.LiveData
import com.example.myapplication.Dao.NotesDao
import com.example.myapplication.Model.Notes

class NotesRepository(val dao: NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>> = dao.getNotes()

    fun getFriendsNotes(): LiveData<List<Notes>> = dao.getFriendsNotes()

    fun getWorkNotes(): LiveData<List<Notes>> = dao.getWorkNotes()

    fun getPersonalNotes(): LiveData<List<Notes>> = dao.getPersonalNotes()

    fun getHomeNotes(): LiveData<List<Notes>> = dao.getHomeNotes()

    fun getFamilyNotes(): LiveData<List<Notes>> = dao.getFamilyNotes()

    fun insertNotes(notes: Notes) {
        dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int) {
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        dao.updateNotes(notes)
    }
}