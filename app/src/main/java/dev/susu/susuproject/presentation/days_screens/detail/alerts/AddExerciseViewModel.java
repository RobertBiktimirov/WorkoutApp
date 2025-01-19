package dev.susu.susuproject.presentation.days_screens.detail.alerts;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dev.susu.susuproject.domain.model.ExerciseModel;
import dev.susu.susuproject.domain.model.ExerciseType;
import dev.susu.susuproject.domain.repository.ExerciseRepository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class AddExerciseViewModel extends ViewModel {

    private static final String[] NO_EXERCISE = {};

    private ExerciseRepository exerciseRepository;
    private ExerciseModel currentExerciseModel;
    private Map<ExerciseType, List<ExerciseModel>> currentExerciseModelMap;

    private ExerciseType exerciseType;

    @Inject
    public AddExerciseViewModel(
            ExerciseRepository exerciseRepository
    ) {
        this.exerciseRepository = exerciseRepository;
        currentExerciseModelMap = new LinkedHashMap<>();
        initExerciseModelMap();
    }

    public void initExerciseModelMap() {
        getAdapterItemsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<>() {

                    @Override
                    public void onNext(@NonNull Map<ExerciseType, List<ExerciseModel>> exerciseTypeListMap) {
                        currentExerciseModelMap = exerciseTypeListMap;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ROBBIK", "initExerciseModelMap onError " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("ROBBIK", "initExerciseModelMap onComplete");
                    }
                });
    }

    private Observable<Map<ExerciseType, List<ExerciseModel>>> getAdapterItemsObservable() {
        return exerciseRepository
                .getExerciseAllList()
                .map(list -> list.stream().collect(Collectors.groupingBy(ExerciseModel::getType)));
    }

    @SuppressLint("BrokenIterator")
    public String[] getExercisesByType(int position) {
        ExerciseType exerciseType = ExerciseType.getExerciseTypeByPosition(position);
        this.exerciseType = exerciseType;
        List<ExerciseModel> exerciseModelList = currentExerciseModelMap.get(exerciseType);
        if (exerciseModelList == null) return NO_EXERCISE;

        return exerciseModelList
                .stream()
                .map(ExerciseModel::getTitle)
                .toArray(String[]::new);
    }

    public void setExercise(int position) {
        List<ExerciseModel> exerciseModelList = currentExerciseModelMap.get(exerciseType);
        if (exerciseModelList != null) {
            currentExerciseModel = exerciseModelList.get(position);
        }
    }

    public ExerciseModel getCurrentExerciseModel() {
        return currentExerciseModel;
    }
}
