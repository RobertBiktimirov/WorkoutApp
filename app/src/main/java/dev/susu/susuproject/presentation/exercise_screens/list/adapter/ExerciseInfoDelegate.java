package dev.susu.susuproject.presentation.exercise_screens.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dev.susu.susuproject.databinding.ListItemExerciseBinding;
import dev.susu.susuproject.domain.model.ExerciseModel;
import dev.susu.susuproject.presentation.exercise_screens.list.adapter_utils.AdapterDelegate;
import dev.susu.susuproject.presentation.exercise_screens.list.adapter_utils.AdapterItem;

public class ExerciseInfoDelegate implements AdapterDelegate {

    public interface ExerciseOnClickListener {
        void onClick(ExerciseModel exerciseModel);
    }

    private ExerciseOnClickListener onClickListener;

    public ExerciseInfoDelegate(@NonNull ExerciseOnClickListener exerciseOnClickListener) {
        this.onClickListener = exerciseOnClickListener;
    }

    class ExerciseInfoViewHolder extends RecyclerView.ViewHolder {

        private ListItemExerciseBinding binding;

        public ExerciseInfoViewHolder(@NonNull ListItemExerciseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ExerciseModel exercise) {
            binding.name.setText(exercise.getTitle());
            binding.getRoot().setOnClickListener(v -> {
                onClickListener.onClick(exercise);
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemExerciseBinding binding = ListItemExerciseBinding.inflate(inflater, parent, false);
        return new ExerciseInfoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, AdapterItem item, int position) {
        if (item instanceof ExerciseInfoDelegateItem) {
            ExerciseInfoViewHolder exerciseInfoViewHolder = (ExerciseInfoViewHolder) holder;
            ExerciseInfoDelegateItem exerciseInfoDelegateItem = (ExerciseInfoDelegateItem) item;
            exerciseInfoViewHolder.bind((ExerciseModel) exerciseInfoDelegateItem.content());
        }
    }

    @Override
    public boolean isOfViewType(AdapterItem item) {
        return item instanceof ExerciseInfoDelegateItem;
    }
}
