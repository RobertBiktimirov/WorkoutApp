package dev.susu.susuproject.presentation.days_screens.detail.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import dev.susu.susuproject.databinding.DayDetailItemBinding;
import dev.susu.susuproject.domain.model.ExerciseApproachModel;
import dev.susu.susuproject.domain.model.ExerciseModel;

public class AddExerciseAdapter extends ListAdapter<ExerciseApproachModel, AddExerciseAdapter.AddExerciseViewHolder> {

    public interface AddExerciseClickHandler {
        void addApproachOnClick(ExerciseModel exerciseModel);
    }

    private AddExerciseClickHandler addExerciseClickHandler;

    public AddExerciseAdapter(
            AddExerciseClickHandler addExerciseClickHandler
    ) {
        super(DIFF_CALLBACK);
        this.addExerciseClickHandler = addExerciseClickHandler;
    }


    private static final DiffUtil.ItemCallback<ExerciseApproachModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull ExerciseApproachModel oldItem, @NonNull ExerciseApproachModel newItem) {
            return oldItem.equals(newItem);
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull ExerciseApproachModel oldItem, @NonNull ExerciseApproachModel newItem) {
            return oldItem.getExerciseModel().equals(newItem.getExerciseModel());
        }
    };

    @NonNull
    @Override
    public AddExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DayDetailItemBinding binding = DayDetailItemBinding.inflate(inflater);
        AddExerciseViewHolder viewHolder = new AddExerciseViewHolder(binding);

        binding.addApproach.setOnClickListener((v) -> {
            if (addExerciseClickHandler != null) {
                addExerciseClickHandler.addApproachOnClick(getItem(viewHolder.getAdapterPosition()).getExerciseModel());
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddExerciseViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class AddExerciseViewHolder extends RecyclerView.ViewHolder {

        private DayDetailItemBinding binding;

        public AddExerciseViewHolder(@NonNull DayDetailItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(@NonNull ExerciseApproachModel exerciseApproachModel) {
            binding.exerciseName.setText(exerciseApproachModel.getExerciseModel().getTitle());
            binding.approaches.setText(exerciseApproachModel.getSmallViewApproach());
            if (exerciseApproachModel.getSmallViewApproach().isEmpty()) {
                binding.approaches.setVisibility(View.GONE);
            } else {
                binding.approaches.setVisibility(View.VISIBLE);
            }
        }
    }

}
