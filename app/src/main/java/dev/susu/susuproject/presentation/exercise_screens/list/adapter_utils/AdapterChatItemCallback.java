package dev.susu.susuproject.presentation.exercise_screens.list.adapter_utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class AdapterChatItemCallback extends DiffUtil.ItemCallback<AdapterItem> {

    @Override
    public boolean areItemsTheSame(AdapterItem oldItem, AdapterItem newItem) {
        return oldItem.getClass() == newItem.getClass() && oldItem.id() == newItem.id();
    }

    @Override
    public boolean areContentsTheSame(AdapterItem oldItem, @NonNull AdapterItem newItem) {
        return oldItem.compareToOther(newItem);
    }
}

