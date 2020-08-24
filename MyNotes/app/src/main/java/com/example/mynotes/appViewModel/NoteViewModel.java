package com.example.mynotes.appViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mynotes.db.Note;
import com.example.mynotes.db.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository mNoteRepository;
    private LiveData<List<Note>> noteList;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mNoteRepository = new NoteRepository(application);
        noteList = mNoteRepository.getAllNote();
    }

    public void insertNote(Note note){
        mNoteRepository.insertNote(note);
    }

    public void updateNote(Note note){
        mNoteRepository.updateNote(note);
    }

    public void deleteNote(Note note){
        mNoteRepository.deleteNote(note);
    }

    public LiveData<List<Note>> getAllNote(){
        return noteList;
    }
}
