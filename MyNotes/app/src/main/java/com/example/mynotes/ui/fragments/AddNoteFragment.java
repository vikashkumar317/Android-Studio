package com.example.mynotes.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.mynotes.R;
import com.example.mynotes.appViewModel.NoteViewModel;
import com.example.mynotes.db.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddNoteFragment extends Fragment {
    private NoteViewModel mNoteViewModel;
    private FloatingActionButton saveButton;
    private EditText titleET, noteET;
    private Note currentNote = null;
    private InputMethodManager inputMethodManager;
    private NavDirections action;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_note, container, false);
        titleET = view.findViewById(R.id.titleET);
        noteET = view.findViewById(R.id.notesET);
        saveButton = view.findViewById(R.id.saveFAButton);

        mNoteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
        inputMethodManager = (InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentNote = AddNoteFragmentArgs.fromBundle(getArguments()).getNote();
        if(currentNote != null){
            titleET.setText(currentNote.getTitle());
            noteET.setText(currentNote.getNotes());
        }

        titleET.requestFocus();
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote(view);
            }
        });
    }

    private void saveNote(View view){
        String noteTitle = titleET.getText().toString();
        String noteBody = noteET.getText().toString();

        if(noteTitle.trim().isEmpty() || noteTitle.isEmpty()){
            titleET.setError("Title shouldn't be empty");
            titleET.requestFocus();
            return;
        }

        if(noteBody.trim().isEmpty() || noteBody.isEmpty()){
            noteET.setError("Note Body shouldn't be empty");
            noteET.requestFocus();
            return;
        }

        Note note = new Note(noteTitle, noteBody);
        if(currentNote != null){
            note.setId(currentNote.getId());
            mNoteViewModel.updateNote(note);
            Toast.makeText(getContext(), "Notes Updated", Toast.LENGTH_SHORT).show();
        }
        else{
            mNoteViewModel.insertNote(note);
            Toast.makeText(getContext(), "Notes Added", Toast.LENGTH_SHORT).show();
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        action = AddNoteFragmentDirections.actionSaveNote();
        Navigation.findNavController(view).navigate(action);
    }
}