package dev.susu.susuproject.presentation.exercise_screens.list.adapter_utils;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public interface AdapterDelegate {

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(RecyclerView.ViewHolder holder, AdapterItem item, int position);

    boolean isOfViewType(AdapterItem item);


}
