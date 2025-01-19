package dev.susu.susuproject.presentation.days_screens.list;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dev.susu.susuproject.domain.model.DayModel;
import dev.susu.susuproject.domain.repository.DayRepository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class DaysListViewModel extends ViewModel {

    private final DayRepository dayRepository;

    private final MutableLiveData<List<DayModel>> currentDayModels;

    @Inject
    DaysListViewModel(
            DayRepository dayRepository
    ) {
        this.dayRepository = dayRepository;
        this.currentDayModels = new MutableLiveData<>();
    }

    public void initDayModels() {
        dayRepository.getDays()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<>() {
                    @Override
                    public void onNext(@NonNull List<DayModel> dayModels) {
                        currentDayModels.setValue(dayModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ROBBIK", "dayRepository.getDays() onError " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("ROBBIK", "dayRepository.getDays() onComplete");
                    }
                });
    }

    public MutableLiveData<List<DayModel>> getCurrentDayModels() {
        initDayModels();
        return currentDayModels;
    }

    public int getPositionDayById(int id) {
        List<DayModel> list = currentDayModels.getValue();
        if (list == null) return -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}