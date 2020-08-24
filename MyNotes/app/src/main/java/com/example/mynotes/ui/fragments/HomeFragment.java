package com.example.mynotes.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.R;
import com.example.mynotes.appViewModel.NoteViewModel;
import com.example.mynotes.db.Note;
import com.example.mynotes.ui.adapter.NoteAdapter;
import com.example.mynotes.ui.callBacks.NotesActionModeCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment implements ClickAdapterListener, MenuBarItemListener {

    private NoteViewModel mNoteViewModel;
    private FloatingActionButton button;
    private RecyclerView mRecyclerView;
    private NoteAdapter mNoteAdapter;
    private HomeFragmentDirections.ActionAddNote action;
    private ActionMode mActionMode;
    private NotesActionModeCallback mNotesActionModeCallback;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = view.findViewById(R.id.notesRV);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        mNoteAdapter = new NoteAdapter(this);
        mRecyclerView.setAdapter(mNoteAdapter);

        mNoteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
        mNoteViewModel.getAllNote().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                mNoteAdapter.setNote(notes);
            }
        });

        button = view.findViewById(R.id.addFAButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = HomeFragmentDirections.actionAddNote();
                Navigation.findNavController(view).navigate(action);
            }
        });

        this.view = view;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNotesActionModeCallback = new NotesActionModeCallback(mNoteAdapter, mActionMode, this);
        hideSoftKeyboard(view);
    }

    @Override
    public void editNote() {
        Note current = mNoteAdapter.getSelectedItems().get(0);
        action = HomeFragmentDirections.actionAddNote();
        action.setNote(current);
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void selectAll(MenuItem edit) {
        mNoteAdapter.selectAll();
        int count = mNoteAdapter.getSelectedItemCount();

        if (count == 0) {
            mActionMode.finish();
            mActionMode = null;
        } else {
            mNotesActionModeCallback.showEditMenu(count);
            mActionMode.setTitle(String.valueOf(count));
            mActionMode.invalidate();
        }
    }

    @Override
    public void deleteNotes() {
        List<Note> selectedItemPositions = mNoteAdapter.getSelectedItems();
        for(Note note: selectedItemPositions){
            mNoteViewModel.deleteNote(note);
        }
        mNoteAdapter.notifyDataSetChanged();
        mActionMode = null;
        Toast.makeText(getContext(), "Notes Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearActionMode() {
        mActionMode = null;
    }

    @Override
    public void enableActionMode(int position) {
        if(mActionMode == null){
            mActionMode = getActivity().startActionMode(mNotesActionModeCallback);
        }
        toggleSelection(position);
    }

    private void toggleSelection(int position) {
        mNoteAdapter.toggleSelection(position);
        int count = mNoteAdapter.getSelectedItemCount();
        if (count == 0) {
            mActionMode.finish();
            mActionMode = null;
        } else {
            mNotesActionModeCallback.showEditMenu(count);
            mActionMode.setTitle(String.valueOf(count));
            mActionMode.invalidate();
        }
    }

    private void hideSoftKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(imm != null)
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}