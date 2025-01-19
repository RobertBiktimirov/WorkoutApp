package dev.susu.susuproject.data.db.converters;

import android.util.Log;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import dev.susu.susuproject.domain.model.ExerciseApproachModel;

public class ExerciseApproachConverter {

    public static String fromExerciseApproachArray(ExerciseApproachModel[] exercises) {
        Gson gson = new Gson();
        String result = gson.toJson(exercises);
        Log.d("ROBBIK", "ExerciseApproachConverter result = " + result);
        return result;
    }

    public static ExerciseApproachModel[] toExerciseApproachArray(String data) {
        if (data == null) {
            return null;
        }

        Gson gson = new Gson();
        Type listType = new TypeToken<ExerciseApproachModel[]>() {
        }.getType();
        return gson.fromJson(data, listType);
    }
}
