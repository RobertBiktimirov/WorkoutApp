package dev.susu.susuproject.data.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import dev.susu.susuproject.data.db.converters.ExerciseTypeConverter;
import dev.susu.susuproject.domain.model.ExerciseType;

@Entity(tableName = "exercise_entity")
public class ExerciseEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exercise_id")
    public long id = 0L;

    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "image_url")
    public String imageUrl;

    @ColumnInfo(name = "exercise_type")
    @TypeConverters(ExerciseTypeConverter.class)
    public ExerciseType exerciseType;

    public ExerciseEntity(
            String title,
            String description,
            String imageUrl,
            ExerciseType exerciseType
    ) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.exerciseType = exerciseType;
    }
}
