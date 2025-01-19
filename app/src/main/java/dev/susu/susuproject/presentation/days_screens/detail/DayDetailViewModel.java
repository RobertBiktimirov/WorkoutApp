package dev.susu.susuproject.presentation.days_screens.detail;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dev.susu.susuproject.domain.model.ApproachModel;
import dev.susu.susuproject.domain.model.DayModel;
import dev.susu.susuproject.domain.model.ExerciseApproachModel;
import dev.susu.susuproject.domain.model.ExerciseModel;
import dev.susu.susuproject.domain.repository.DayRepository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class DayDetailViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<ExerciseApproachModel>> listExerciseApproach;
    private final MutableLiveData<String> headerDay;

    private final DayRepository dayRepository;

    private Disposable disposable;

    private String currentHeader = "";

    private DayModel dayModel;


    @Inject
    public DayDetailViewModel(
            DayRepository dayRepository
    ) {
        this.listExerciseApproach = new MutableLiveData<>(new ArrayList<>());
        this.headerDay = new MutableLiveData<>("");
        this.dayRepository = dayRepository;
    }

    public void addExercise(ExerciseModel exerciseModel) {
        Log.d("ROBBIK", exerciseModel.getTitle() + " " + exerciseModel.getDescription());
        if (listExerciseApproach != null) {
            ArrayList<ExerciseApproachModel> currentList = listExerciseApproach.getValue();
            if (currentList == null) {
                currentList = new ArrayList<>();
            }
            currentList.add(new ExerciseApproachModel(exerciseModel, new ArrayList<>()));

            listExerciseApproach.setValue(currentList);
        }
    }

    public LiveData<ArrayList<ExerciseApproachModel>> getListExerciseApproach() {
        return listExerciseApproach;
    }

    public void addApproach(ExerciseModel exerciseModel, ApproachModel approachModel) {
        Log.d("ROBBIK", "exercise " + exerciseModel.getTitle() + " count" + approachModel.getCount() + "weight " + approachModel.weight);

        if (listExerciseApproach != null) {
            ArrayList<ExerciseApproachModel> currentList = listExerciseApproach.getValue();
            assert currentList != null;
            for (ExerciseApproachModel exerciseApproachModel : currentList) {
                if (exerciseApproachModel.getExerciseModel().getId() == exerciseModel.getId()) {
                    exerciseApproachModel.addApproach(approachModel);
                    break;
                }
            }
            listExerciseApproach.setValue(currentList);
        }
    }

    public void getDayModel(int id) {
        dayRepository.getDayDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(@NonNull DayModel dayModel) {
                        setCurrentHeader(dayModel.getHeader());
                        setDay(dayModel);
                        headerDay.setValue(dayModel.getHeader());
                        listExerciseApproach.setValue(new ArrayList<>(Arrays.asList(dayModel.getExercises())));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ROBBIK", "dayRepository.getDayDetail(id) error " + e.getMessage());
                    }
                });
    }

    private void setDay(@NonNull DayModel dayModel) {
        this.dayModel = dayModel;
    }

    public void deleteDay() {
        if (dayModel != null) {
            dayRepository.deleteDay(dayModel.getId());
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable = null;
    }

    public int getCurrentDayId() {
        return dayModel.getId();
    }

    public String getCurrentHeader() {
        return currentHeader;
    }

    public void setCurrentHeader(String currentHeader) {
        this.currentHeader = currentHeader;
    }

    public MutableLiveData<String> getHeaderDay() {
        return headerDay;
    }

    public void saveDay() {
        if (this.dayModel == null) {
            dayModel = new DayModel(
                    0,
                    getCurrentHeader(),
                    Calendar.getInstance().getTimeInMillis(),
                    Objects.requireNonNull(listExerciseApproach.getValue()).toArray(new ExerciseApproachModel[0]));
        }
        if (!getCurrentHeader().isEmpty() && headerDay.getValue() != null && !headerDay.getValue().equals(getCurrentHeader())) {
            dayModel.setHeader(getCurrentHeader());
        }

        dayModel.setExercises(Objects.requireNonNull(listExerciseApproach.getValue()).toArray(new ExerciseApproachModel[0]));
        dayRepository.saveDay(dayModel);
    }
}