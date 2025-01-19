package dev.susu.susuproject.presentation.days_screens.list.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import dev.susu.susuproject.databinding.ListItemDayBinding;
import dev.susu.susuproject.domain.model.DayModel;

public class DaysListAdapter extends ListAdapter<DayModel, DaysListAdapter.DayViewHolder> {

    public interface DayOnClickListener {
        public void onClick(DayModel dayModel);
    }

    private DayOnClickListener dayOnClickListener;

    public DaysListAdapter(
            DayOnClickListener dayOnClickListener
    ) {
        super(DIFF_CALLBACK);
        this.dayOnClickListener = dayOnClickListener;
    }

    private static final DiffUtil.ItemCallback<DayModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull DayModel oldItem, @NonNull DayModel newItem) {
            return oldItem.getHeader().equals(oldItem.getHeader());
        }

        @Override
        public boolean areContentsTheSame(@NonNull DayModel oldItem, @NonNull DayModel newItem) {
            return oldItem.getId() == newItem.getId();
        }
    };

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemDayBinding binding = ListItemDayBinding.inflate(inflater, parent, false);
        DayViewHolder viewHolder = new DayViewHolder(binding);
        binding.getRoot().setOnClickListener((v) -> dayOnClickListener.onClick(getItem(viewHolder.getAdapterPosition())));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        DayModel currentDay = getItem(position);
        holder.bind(currentDay);
    }

    class DayViewHolder extends RecyclerView.ViewHolder {

        private final ListItemDayBinding binding;

        public DayViewHolder(@NonNull ListItemDayBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(DayModel day) {
            binding.headerTextView.setText(day.getHeader());
            binding.dateTextView.setText(day.getFormattedDate());
            binding.exercisesTextView.setText(day.getExercisesForItemList());
        }
    }
}

