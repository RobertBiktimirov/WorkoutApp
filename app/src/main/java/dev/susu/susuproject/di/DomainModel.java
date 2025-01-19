package dev.susu.susuproject.di;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.components.SingletonComponent;
import dev.susu.susuproject.data.repository.DayRepositoryImpl;
import dev.susu.susuproject.data.repository.ExerciseRepositoryImpl;
import dev.susu.susuproject.domain.repository.DayRepository;
import dev.susu.susuproject.domain.repository.ExerciseRepository;

@Module
@InstallIn(SingletonComponent.class)
public abstract class DomainModel {

    @Binds
    public abstract ExerciseRepository bindExerciseRepository(ExerciseRepositoryImpl exerciseRepositoryImpl);

    @Binds
    public abstract DayRepository bindDayRepository(DayRepositoryImpl impl);
}
