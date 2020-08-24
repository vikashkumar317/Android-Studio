package com.example.mynotes.ui.fragments;

import android.view.MenuItem;

public interface MenuBarItemListener {
    void editNote();

    void selectAll(MenuItem edit);

    void deleteNotes();

    void clearActionMode();
}
