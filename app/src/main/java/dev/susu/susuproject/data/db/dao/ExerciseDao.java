package dev.susu.susuproject.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import dev.susu.susuproject.data.db.entities.ExerciseEntity;
import dev.susu.susuproject.domain.model.ExerciseType;

@Dao
public interface ExerciseDao {

    @Query("select * from exercise_entity")
    List<ExerciseEntity> getAllExerciseList();

    @Query("select * from exercise_entity where exercise_type = :exerciseType")
    List<ExerciseEntity> getExerciseListByType(ExerciseType exerciseType);

    @Query("select * from exercise_entity where exercise_id = :id")
    ExerciseEntity getExerciseDetail(long id);

    @Insert
    void insertExercise(ExerciseEntity... exerciseEntity);

    @Query("delete from exercise_entity where exercise_id = :id")
    void deleteExercise(long id);

    @Query("update exercise_entity set title = :title, description = :desc, image_url = :imageUrl, exercise_type = :type where exercise_id = :id")
    void updateExercise(long id, String title, String desc, String imageUrl, ExerciseType type);
}
