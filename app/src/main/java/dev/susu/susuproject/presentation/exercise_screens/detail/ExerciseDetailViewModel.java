package dev.susu.susuproject.presentation.exercise_screens.detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dev.susu.susuproject.domain.model.ExerciseModel;
import dev.susu.susuproject.domain.model.ExerciseType;
import dev.susu.susuproject.domain.repository.ExerciseRepository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.random.Random;

@HiltViewModel
public class ExerciseDetailViewModel extends ViewModel {

    private Disposable disposable;

    private MutableLiveData<ExerciseModel> _currentExerciseModel;

    private MutableLiveData<String> _errorMessage;

    private ExerciseRepository exerciseRepository;

    private String currentTitle = "";
    private String currentDesc = "";
    private ExerciseType currentType = ExerciseType.TRICEPS;
    private String currentImageUrl = "";

    @Inject
    public ExerciseDetailViewModel(
            ExerciseRepository exerciseRepository
    ) {
        this.exerciseRepository = exerciseRepository;
        _currentExerciseModel = new MutableLiveData<>();
        _errorMessage = new MutableLiveData<>();
        _currentExerciseModel.setValue(new ExerciseModel(
                Random.Default.nextInt(),
                "",
                "",
                "",
                ExerciseType.TRICEPS
        ));
    }

    public LiveData<ExerciseModel> getCurrentExerciseModel() {
        return _currentExerciseModel;
    }

    public LiveData<String> getErrorMessage() {
        return _errorMessage;
    }

    public void getExerciseModel(long id) {
        exerciseRepository.getExerciseDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(@NonNull ExerciseModel exerciseModel) {
                        currentTitle = exerciseModel.getTitle();
                        currentDesc = exerciseModel.getDescription();
                        currentType = exerciseModel.getType();
                        currentImageUrl = exerciseModel.getImageUrl();
                        _currentExerciseModel.setValue(exerciseModel);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ROBBIK", "getExerciseModel error " + e.getMessage());
                    }
                });
    }

    public void setExerciseTitle(String title) {
        currentTitle = title;
    }

    public void setExerciseDesc(String desc) {
        currentDesc = desc;
    }

    public void setExerciseType(ExerciseType type) {
        currentType = type;
    }

    public void setExerciseImage(String uri) {
        currentImageUrl = uri;
    }

    public void deleteExercise() {
        ExerciseModel exerciseModel = getCurrentExerciseModel().getValue();
        if (exerciseModel != null) {
            exerciseRepository.deleteExercise(exerciseModel);
        }
    }

    public void saveExercise() {
        if (currentTitle == null || currentTitle.isEmpty()) {
            _errorMessage.setValue("Название упражнения не должно быть пустым");
            return;
        }

        if (currentType == null) {
            _errorMessage.setValue("Тип упражнения не должен быть пустым");
            return;
        }

        ExerciseModel exerciseModel = getCurrentExerciseModel().getValue();

        Objects.requireNonNull(exerciseModel).setTitle(currentTitle);

        if (!currentDesc.trim().isEmpty()) {
            exerciseModel.setDescription(currentDesc);
        }

        if (!currentImageUrl.trim().isEmpty()) {
            exerciseModel.setImageUrl(currentImageUrl);
        }

        if (!currentType.equals(exerciseModel.getType())) {
            exerciseModel.setType(currentType);
        }


        exerciseRepository.saveExercise(exerciseModel);
    }

}