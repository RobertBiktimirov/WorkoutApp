package dev.susu.susuproject.presentation.exercise_screens.list.adapter_utils;

public interface AdapterItem {
    Object content();

    long id();

    boolean compareToOther(AdapterItem other);
}
