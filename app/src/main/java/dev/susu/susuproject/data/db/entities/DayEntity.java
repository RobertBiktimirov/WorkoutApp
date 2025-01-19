package dev.susu.susuproject.data.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import dev.susu.susuproject.data.db.converters.ExerciseApproachConverter;
import dev.susu.susuproject.domain.model.ExerciseApproachModel;

@Entity(tableName = "day_entity")
public class DayEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "day_id")
    public int id = 0;

    @ColumnInfo(name = "header")
    public String header;

    @ColumnInfo(name = "date")
    public long date;

    @ColumnInfo(name = "exercise_approaches")
    public String exerciseApproaches;

    public DayEntity(String header, long date, String exerciseApproaches) {
        this.header = header;
        this.date = date;
        this.exerciseApproaches = exerciseApproaches;
    }
}
