package com.example.mynotes.ui.callBacks;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mynotes.R;
import com.example.mynotes.ui.adapter.NoteAdapter;
import com.example.mynotes.ui.fragments.MenuBarItemListener;

public class NotesActionModeCallback implements ActionMode.Callback {

    private MenuItem edit;
    private NoteAdapter mNoteAdapter;
    private ActionMode mActionMode;
    private MenuBarItemListener listener;

    public NotesActionModeCallback(NoteAdapter noteAdapter, ActionMode actionMode, MenuBarItemListener menuBarItemListener) {
        this.mNoteAdapter = noteAdapter;
        this.mActionMode = actionMode;
        this.listener = menuBarItemListener;
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        actionMode.getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        edit = menu.findItem(R.id.edit);
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.edit:
                listener.editNote();
                actionMode.finish();
                return true;

            case R.id.delete:
                listener.deleteNotes();
                actionMode.finish();
                return true;

            case R.id.selectAll:
                listener.selectAll(edit);
                return true;

            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        listener.clearActionMode();
        mNoteAdapter.clearSelections();
    }

    public void showEditMenu(int count){
        if(edit == null) return;
        if(count > 1)
            edit.setVisible(false);
        else
            edit.setVisible(true);
    }

}
