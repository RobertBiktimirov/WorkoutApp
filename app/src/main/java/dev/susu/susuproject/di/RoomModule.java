package dev.susu.susuproject.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import dev.susu.susuproject.data.db.AppDatabase;
import dev.susu.susuproject.data.db.dao.DayDao;
import dev.susu.susuproject.data.db.dao.ExerciseDao;

@Module
@InstallIn(SingletonComponent.class)
public class RoomModule {

    @Provides
    public static AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return AppDatabase.getInstance(context);
    }

    @Provides
    public static ExerciseDao provideExerciseDao(AppDatabase appDatabase) {
        return appDatabase.exerciseDao();
    }

    @Provides
    public static DayDao provideDayDao(AppDatabase appDatabase) {
        return appDatabase.dayDao();
    }

}
