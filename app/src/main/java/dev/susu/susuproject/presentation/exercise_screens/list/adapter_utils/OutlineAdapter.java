package dev.susu.susuproject.presentation.exercise_screens.list.adapter_utils;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class OutlineAdapter extends ListAdapter<AdapterItem, RecyclerView.ViewHolder> {

    private final List<AdapterDelegate> delegates = new ArrayList<>();

    public OutlineAdapter() {
        super(new AdapterChatItemCallback());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return delegates.get(viewType).onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        delegates.get(getItemViewType(position)).onBindViewHolder(holder, getItem(position), position);
    }

    public void addDelegate(AdapterDelegate delegate) {
        delegates.add(delegate);
    }

    @Override
    public int getItemViewType(int position) {
        return indexOfFirst(delegate -> delegate.isOfViewType(getCurrentList().get(position)));
    }

    private int indexOfFirst(Predicate<AdapterDelegate> predicate) {
        for (int i = 0; i < delegates.size(); i++) {
            if (predicate.test(delegates.get(i))) {
                return i;
            }
        }
        return -1;
    }
}

