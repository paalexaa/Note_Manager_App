package com.example.myapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.Database.NotesDatabase
import com.example.myapplication.Model.Notes
import com.example.myapplication.Repository.NotesRepository

class NotesViewModel(application: Application): AndroidViewModel(application) {

    val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes) {
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    fun getFriendsNotes(): LiveData<List<Notes>> = repository.getFriendsNotes()

    fun getWorkNotes(): LiveData<List<Notes>> = repository.getWorkNotes()

    fun getPersonalNotes(): LiveData<List<Notes>> = repository.getPersonalNotes()

    fun getHomeNotes(): LiveData<List<Notes>> = repository.getHomeNotes()

    fun getFamilyNotes(): LiveData<List<Notes>> = repository.getFamilyNotes()

    fun deleteNotes(id: Int) {
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        repository.updateNotes(notes)
    }
}