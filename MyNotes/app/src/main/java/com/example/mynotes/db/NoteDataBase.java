package com.example.mynotes.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDataBase extends RoomDatabase {
    private static NoteDataBase noteDataBase = null;

    public abstract NoteDao getNoteDao();

    public static synchronized NoteDataBase getInstance(Context context){
        if (noteDataBase == null) {
            noteDataBase = buildDatabaseInstance(context);
        }
        return noteDataBase;
    }

    private static NoteDataBase buildDatabaseInstance(Context context) {
        return Room
                .databaseBuilder(context, NoteDataBase.class, "noteDatabase")
                .fallbackToDestructiveMigration()
                .addCallback(roomCallback)
                .build();
    }

    public void cleanUp(){
        noteDataBase = null;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(noteDataBase).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao mNoteDao;

        public PopulateDbAsyncTask(NoteDataBase noteDataBase) {
            mNoteDao = noteDataBase.getNoteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mNoteDao.addNote(new Note("Title", "Sample Note"));
            return null;
        }
    }
}
