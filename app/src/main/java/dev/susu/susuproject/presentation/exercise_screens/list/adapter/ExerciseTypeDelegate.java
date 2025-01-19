package dev.susu.susuproject.presentation.exercise_screens.list.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dev.susu.susuproject.databinding.ListItemTypeExirciseBinding;
import dev.susu.susuproject.presentation.exercise_screens.list.adapter_utils.AdapterDelegate;
import dev.susu.susuproject.presentation.exercise_screens.list.adapter_utils.AdapterItem;

public class ExerciseTypeDelegate implements AdapterDelegate {

    class ExerciseTypeViewHolder extends RecyclerView.ViewHolder {

        private ListItemTypeExirciseBinding binding;

        public ExerciseTypeViewHolder(@NonNull ListItemTypeExirciseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(String type) {
            binding.name.setText(type);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemTypeExirciseBinding binding = ListItemTypeExirciseBinding.inflate(inflater, parent, false);
        return new ExerciseTypeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, AdapterItem item, int position) {
        if (item instanceof ExerciseTypeDelegateItem) {
            ExerciseTypeViewHolder exerciseTypeViewHolder = (ExerciseTypeViewHolder) holder;
            ExerciseTypeDelegateItem exerciseTypeDelegateItem = (ExerciseTypeDelegateItem) item;
            exerciseTypeViewHolder.bind((String) exerciseTypeDelegateItem.content());
        }
    }

    @Override
    public boolean isOfViewType(AdapterItem item) {
        return item instanceof ExerciseTypeDelegateItem;
    }
}
