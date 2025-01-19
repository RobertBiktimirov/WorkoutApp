package dev.susu.susuproject.data.db.converters

import androidx.room.TypeConverter
import dev.susu.susuproject.domain.model.ExerciseType

class ExerciseTypeConverter {
    @TypeConverter
    fun toExerciseType(value: String) = enumValues<ExerciseType>()

    @TypeConverter
    fun fromExerciseType(exerciseType: ExerciseType) = exerciseType.name
}