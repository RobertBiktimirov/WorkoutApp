package dev.susu.susuproject.data.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dev.susu.susuproject.data.db.dao.ExerciseDao;
import dev.susu.susuproject.data.db.entities.ExerciseEntity;
import dev.susu.susuproject.data.mapper.ExerciseMapper;
import dev.susu.susuproject.domain.executor.Executor;
import dev.susu.susuproject.domain.model.ExerciseModel;
import dev.susu.susuproject.domain.model.ExerciseType;
import dev.susu.susuproject.domain.repository.ExerciseRepository;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class ExerciseRepositoryImpl implements ExerciseRepository {

    private ExerciseDao exerciseDao;

    private ExerciseMapper exerciseMapper;

    @Inject
    public ExerciseRepositoryImpl(ExerciseDao exerciseDao, ExerciseMapper exerciseMapper) {
        this.exerciseDao = exerciseDao;
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public Observable<List<ExerciseModel>> getExerciseAllList() {

        return Observable.create(emitter -> {
            try {
                List<ExerciseModel> exerciseModelList = exerciseDao.getAllExerciseList().stream()
                        .map(exerciseEntity -> exerciseMapper.entityToModel(exerciseEntity))
                        .collect(Collectors.toList());
                emitter.onNext(exerciseModelList);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

    @Override
    public Observable<List<ExerciseModel>> getExerciseByType(ExerciseType exerciseType) {
        return Observable.create(emitter -> {
            try {
                List<ExerciseModel> exerciseModelList = exerciseDao.getExerciseListByType(exerciseType).stream()
                        .map(exerciseEntity -> exerciseMapper.entityToModel(exerciseEntity))
                        .collect(Collectors.toList());
                emitter.onNext(exerciseModelList);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

    @Override
    public void saveExercise(ExerciseModel exerciseModel) {
        Executor.ioThread(() -> {
            ExerciseEntity exerciseEntity = exerciseMapper.modelToEntity(exerciseModel);

            if (exerciseDao.getExerciseDetail(exerciseModel.getId()) != null) {
                exerciseDao.updateExercise(
                        exerciseModel.getId(),
                        exerciseModel.getTitle(),
                        exerciseModel.getDescription(),
                        exerciseModel.getImageUrl(),
                        exerciseModel.getType()
                );
            } else {
                exerciseDao.insertExercise(exerciseEntity);
            }
        });
    }

    @Override
    public void deleteExercise(ExerciseModel exerciseModel) {
        Executor.ioThread(() -> {
            exerciseDao.deleteExercise(exerciseModel.getId());
        });
    }

    @Override
    public Single<ExerciseModel> getExerciseDetail(long id) {
        return Single.fromCallable(() -> {
            ExerciseEntity exerciseEntity = exerciseDao.getExerciseDetail(id);
            return exerciseMapper.entityToModel(exerciseEntity);
        });
    }
}
