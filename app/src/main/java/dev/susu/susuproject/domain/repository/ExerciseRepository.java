package dev.susu.susuproject.domain.repository;

import java.util.List;

import dev.susu.susuproject.domain.model.ExerciseModel;
import dev.susu.susuproject.domain.model.ExerciseType;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface ExerciseRepository {

    Observable<List<ExerciseModel>> getExerciseAllList();

    Observable<List<ExerciseModel>> getExerciseByType(ExerciseType exerciseType);

    void saveExercise(ExerciseModel exerciseModel);

    void deleteExercise(ExerciseModel exerciseModel);

    Single<ExerciseModel> getExerciseDetail(long id);
}
