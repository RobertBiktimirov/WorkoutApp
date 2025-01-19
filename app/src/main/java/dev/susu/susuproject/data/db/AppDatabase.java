package dev.susu.susuproject.data.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import dev.susu.susuproject.data.db.dao.DayDao;
import dev.susu.susuproject.data.db.dao.ExerciseDao;
import dev.susu.susuproject.data.db.entities.DayEntity;
import dev.susu.susuproject.data.db.entities.ExerciseEntity;
import dev.susu.susuproject.domain.executor.Executor;

@Database(entities = {ExerciseEntity.class, DayEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExerciseDao exerciseDao();

    public abstract DayDao dayDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "WorkoutDatabase.db")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executor.ioThread(() -> {
                            getInstance(context)
                                    .exerciseDao()
                                    .insertExercise(ExerciseDefaultList.getDefaultExerciseList().toArray(new ExerciseEntity[0]));
                        });
                    }
                })
                .build();
    }
}
