package dev.susu.susuproject.data.mapper;

import javax.inject.Inject;

import dev.susu.susuproject.data.db.entities.ExerciseEntity;
import dev.susu.susuproject.domain.model.ExerciseModel;

public class ExerciseMapper {

    @Inject
    public ExerciseMapper() {
    }

    public ExerciseModel entityToModel(ExerciseEntity exerciseEntity) {
        return new ExerciseModel(
                exerciseEntity.id,
                exerciseEntity.title,
                exerciseEntity.description,
                exerciseEntity.imageUrl,
                exerciseEntity.exerciseType
        );
    }

    public ExerciseEntity modelToEntity(ExerciseModel exerciseModel) {
        return new ExerciseEntity(
                exerciseModel.getTitle(),
                exerciseModel.getDescription(),
                exerciseModel.getImageUrl(),
                exerciseModel.getType()
        );
    }
}
