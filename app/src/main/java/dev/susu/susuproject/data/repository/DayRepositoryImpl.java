package dev.susu.susuproject.data.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dev.susu.susuproject.data.db.converters.ExerciseApproachConverter;
import dev.susu.susuproject.data.db.dao.DayDao;
import dev.susu.susuproject.data.db.entities.DayEntity;
import dev.susu.susuproject.data.mapper.DayMapper;
import dev.susu.susuproject.domain.executor.Executor;
import dev.susu.susuproject.domain.model.DayModel;
import dev.susu.susuproject.domain.model.ExerciseModel;
import dev.susu.susuproject.domain.repository.DayRepository;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;

public class DayRepositoryImpl implements DayRepository {

    private final DayDao dayDao;

    private final DayMapper dayMapper;

    @Inject
    public DayRepositoryImpl(
            DayDao dayDao,
            DayMapper dayMapper
    ) {
        this.dayDao = dayDao;
        this.dayMapper = dayMapper;
    }

    @Override
    public Observable<List<DayModel>> getDays() {
        return Observable.create(emitter -> {
            try {
                List<DayModel> exerciseModelList = dayDao.getDays().stream()
                        .map(dayMapper::toModel)
                        .collect(Collectors.toList());
                emitter.onNext(exerciseModelList);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

    @Override
    public void saveDay(DayModel dayModel) {
        Executor.ioThread(() -> {
            if (dayDao.getDayDetail(dayModel.getId()) != null) {
                dayDao.update(
                        dayModel.getId(),
                        dayModel.getHeader(),
                        dayModel.getDate(),
                        ExerciseApproachConverter.fromExerciseApproachArray(dayModel.getExercises()));
            } else {
                dayDao.saveDay(dayMapper.toEntity(dayModel));
            }
        });
    }

    @Override
    public void deleteDay(int id) {
        Executor.ioThread(() -> {
            dayDao.delete(id);
        });
    }

    @Override
    public Single<DayModel> getDayDetail(int id) {
        return Single.fromCallable(() -> dayMapper.toModel(dayDao.getDayDetail(id)));
    }
}
