package dev.susu.susuproject.presentation.exercise_screens.list.adapter;

import dev.susu.susuproject.presentation.exercise_screens.list.adapter_utils.AdapterItem;

public class ExerciseTypeDelegateItem implements AdapterItem {

    private String value;


    public ExerciseTypeDelegateItem(String type) {
        this.value = type;
    }


    @Override
    public Object content() {
        return value;
    }

    @Override
    public long id() {
        return value.hashCode();
    }

    @Override
    public boolean compareToOther(AdapterItem other) {
        if (other instanceof ExerciseTypeDelegateItem) {
            return content().equals(other.content());
        }
        return false;
    }
}
