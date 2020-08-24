package com.example.mynotes.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    public void addNote(Note note);

    @Query("SELECT * FROM notes ORDER BY id DESC")
    public LiveData<List<Note> > getAllNotes();

    @Update
    public void updateNote(Note note);

    @Delete
    public void deleteNote(Note note);
}
