package dev.susu.susuproject.data.mapper;

import java.util.ArrayList;

import javax.inject.Inject;

import dev.susu.susuproject.data.db.converters.ExerciseApproachConverter;
import dev.susu.susuproject.data.db.entities.DayEntity;
import dev.susu.susuproject.domain.model.DayModel;
import dev.susu.susuproject.domain.model.ExerciseApproachModel;

public class DayMapper {

    @Inject
    public DayMapper() {
    }

    public DayModel toModel(DayEntity entity) {
        return new DayModel(
                entity.id,
                entity.header,
                entity.date,
                ExerciseApproachConverter.toExerciseApproachArray(entity.exerciseApproaches)
        );
    }

    public DayEntity toEntity(DayModel dayModel) {
        return new DayEntity(
                dayModel.getHeader(),
                dayModel.getDate(),
                ExerciseApproachConverter.fromExerciseApproachArray(dayModel.getExercises())
        );
    }

}
