package dev.susu.susuproject.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import dev.susu.susuproject.data.db.entities.DayEntity;
import dev.susu.susuproject.domain.model.ExerciseApproachModel;

@Dao
public interface DayDao {

    @Query("select * from day_entity order by date desc")
    public List<DayEntity> getDays();

    @Query("select * from day_entity where day_id = :id")
    public DayEntity getDayDetail(int id);

    @Insert
    public void saveDay(DayEntity... dayEntities);

    @Query("delete from day_entity where day_id = :id")
    void delete(int id);

    @Query("update day_entity set header = :header, date = :date, exercise_approaches = :exerciseApproachModels where day_id = :id")
    void update(int id, String header, long date, String exerciseApproachModels);
}
