package dev.susu.susuproject.domain.model;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DayModel {

    private int id;
    private String header;
    private long date;

    private ExerciseApproachModel[] exercises;

    public DayModel(int id, String header, long date, ExerciseApproachModel[] exercises) {
        this.id = id;
        this.header = header;
        this.date = date;
        this.exercises = exercises;
    }

    public int getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public long getDate() {
        return date;
    }

    public ExerciseApproachModel[] getExercises() {
        return exercises;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setExercises(ExerciseApproachModel[] exercises) {
        this.exercises = exercises;
    }

    public String getFormattedDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date dateObject = new Date(date);
        return format.format(dateObject);
    }

    public String getExercisesForItemList() {
        StringBuilder builder = new StringBuilder();
        for (ExerciseApproachModel exerciseModel : exercises) {
            builder.append("\n- ").append(exerciseModel.getExerciseModel().getTitle());
        }
        return builder.toString();
    }
}

