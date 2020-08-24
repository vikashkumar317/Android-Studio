package com.example.mynotes.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mynotes.appData.Constants.*;

import java.util.List;

public class NoteRepository {
    private NoteDao mNoteDao;
    private LiveData<List<Note> > noteList;

    public NoteRepository(Application application) {
        NoteDataBase mNoteDataBase = NoteDataBase.getInstance(application);
        mNoteDao = mNoteDataBase.getNoteDao();
        noteList = mNoteDao.getAllNotes();
    }

    public void insertNote(Note note){
        new DataBaseTask(mNoteDao, DatabaseOperation.INSERT_NOTE).execute(note);
    }

    public void updateNote(Note note){
        new DataBaseTask(mNoteDao, DatabaseOperation.UPDATE_NOTE).execute(note);
    }

    public void deleteNote(Note note){
        new DataBaseTask(mNoteDao, DatabaseOperation.DELETE_NOTE).execute(note);
    }

    public LiveData<List<Note> > getAllNote(){
        return noteList;
    }

    private static class DataBaseTask extends AsyncTask<Note, Void, Void> {
        private NoteDao mNoteDao;
        private final int type;

        public DataBaseTask(NoteDao noteDao, int type) {
            this.mNoteDao = noteDao;
            this.type = type;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            switch (type){
                case DatabaseOperation.INSERT_NOTE :
                    mNoteDao.addNote(notes[0]);
                    break;

                case DatabaseOperation.DELETE_NOTE :
                    mNoteDao.deleteNote(notes[0]);
                    break;

                case DatabaseOperation.UPDATE_NOTE :
                    mNoteDao.updateNote(notes[0]);
                    break;

                default:
                    break;
            }
            return null;
        }
    }

}