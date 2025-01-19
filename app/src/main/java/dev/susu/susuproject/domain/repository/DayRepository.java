package dev.susu.susuproject.domain.repository;

import java.util.List;

import dev.susu.susuproject.domain.model.DayModel;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface DayRepository {
    Observable<List<DayModel>> getDays();

    void saveDay(DayModel dayModel);

    void deleteDay(int id);

    Single<DayModel> getDayDetail(int id);
}
