package dev.susu.susuproject.presentation.exercise_screens.list;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dev.susu.susuproject.domain.model.ExerciseModel;
import dev.susu.susuproject.domain.repository.ExerciseRepository;
import dev.susu.susuproject.presentation.exercise_screens.list.adapter.ExerciseInfoDelegateItem;
import dev.susu.susuproject.presentation.exercise_screens.list.adapter.ExerciseTypeDelegateItem;
import dev.susu.susuproject.presentation.exercise_screens.list.adapter_utils.AdapterItem;
import io.reactivex.rxjava3.core.Observable;

@HiltViewModel
public class ExerciseListViewModel extends ViewModel {

    private final ExerciseRepository exerciseRepository;

    private Map<String, List<ExerciseModel>> exerciseModelMap;

    @Inject
    ExerciseListViewModel(
            ExerciseRepository exerciseRepository
    ) {
        this.exerciseRepository = exerciseRepository;
    }

    public Observable<ArrayList<AdapterItem>> getExercisesData() {
        return getAdapterItemsObservable();
    }

    public Observable<ArrayList<AdapterItem>> getAdapterItemsObservable() {
        return exerciseRepository.getExerciseAllList().map(list -> {
            exerciseModelMap = list.stream()
                    .collect(Collectors.groupingBy(ExerciseModel::getTypeDesc));
            ArrayList<AdapterItem> adapterItems = new ArrayList<>();
            for (var entry : exerciseModelMap.entrySet()) {
                adapterItems.add(new ExerciseTypeDelegateItem(entry.getKey()));
                for (ExerciseModel exerciseModel : entry.getValue()) {
                    adapterItems.add(new ExerciseInfoDelegateItem(exerciseModel));
                }
            }
            return adapterItems;
        });
    }
}