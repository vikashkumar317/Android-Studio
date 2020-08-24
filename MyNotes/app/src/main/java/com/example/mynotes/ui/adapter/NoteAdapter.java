package com.example.mynotes.ui.adapter;

import android.util.SparseBooleanArray;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.R;
import com.example.mynotes.db.Note;
import com.example.mynotes.ui.fragments.ClickAdapterListener;
import com.example.mynotes.ui.fragments.HomeFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> mNote = new ArrayList<>();
    private HomeFragmentDirections.ActionAddNote action;
    private ClickAdapterListener listener;
    private SparseBooleanArray selectedItems;

    public NoteAdapter(ClickAdapterListener clickAdapterListener) {
        this.listener = clickAdapterListener;
        selectedItems = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        final Note current = mNote.get(position);
        holder.title.setText(current.getTitle());
        holder.body.setText(current.getNotes());

        holder.itemView.setActivated(selectedItems.get(position, false));
        applyClickEvents(holder, position);
    }

    private void applyClickEvents(final NoteViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getSelectedItemCount() > 0){
                    listener.enableActionMode(position);
                }
                else{
                    action = HomeFragmentDirections.actionAddNote();
                    action.setNote(mNote.get(position));
                    Navigation.findNavController(view).navigate(action);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.enableActionMode(position);
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNote.size();
    }

    public void setNote(List<Note> notes){
        this.mNote = notes;
        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        }
        else{
            selectedItems.put(position, true);
        }
        notifyItemChanged(position);
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public void selectAll() {
        for (int i = 0; i < getItemCount(); i++)
            selectedItems.put(i, true);
        notifyDataSetChanged();
    }

    public List<Note> getSelectedItems() {
        List<Note> items = new ArrayList(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(mNote.get(selectedItems.keyAt(i)));
        }
        return items;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        public final TextView title, body;
        final NoteAdapter mAdapter;

        public NoteViewHolder(@NonNull View itemView, NoteAdapter noteAdapter) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTV);
            body = itemView.findViewById(R.id.noteTV);
            this.mAdapter = noteAdapter;
        }
    }

}
