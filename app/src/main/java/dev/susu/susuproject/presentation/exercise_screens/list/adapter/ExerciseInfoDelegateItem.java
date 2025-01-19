package dev.susu.susuproject.presentation.exercise_screens.list.adapter;

import dev.susu.susuproject.domain.model.ExerciseModel;
import dev.susu.susuproject.presentation.exercise_screens.list.adapter_utils.AdapterItem;

public class ExerciseInfoDelegateItem implements AdapterItem {

    private ExerciseModel exerciseModel;

    public ExerciseInfoDelegateItem(ExerciseModel exerciseModel) {
        this.exerciseModel = exerciseModel;
    }

    @Override
    public Object content() {
        return exerciseModel;
    }

    @Override
    public long id() {
        return exerciseModel.getId();
    }

    @Override
    public boolean compareToOther(AdapterItem other) {
        if (other instanceof ExerciseInfoDelegateItem) {
            return content().equals(other.content());
        }
        return false;
    }
}
