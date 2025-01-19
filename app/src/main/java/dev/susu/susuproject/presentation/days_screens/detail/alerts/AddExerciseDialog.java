package dev.susu.susuproject.presentation.days_screens.detail.alerts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Arrays;

import dagger.hilt.android.AndroidEntryPoint;
import dev.susu.susuproject.R;
import dev.susu.susuproject.databinding.DialogAddExerciseBinding;
import dev.susu.susuproject.domain.model.ExerciseModel;
import dev.susu.susuproject.domain.model.ExerciseType;

@AndroidEntryPoint
public class AddExerciseDialog extends BottomSheetDialogFragment {

    public interface SaveExerciseListener {
        void save(ExerciseModel exerciseModel);
    }

    private SaveExerciseListener saveExerciseListener;

    public static String SELECTED_KEY = "selected_key";

    private AddExerciseViewModel viewModel;

    private DialogAddExerciseBinding binding;

    private AddExerciseDialog(
            SaveExerciseListener saveExerciseListener
    ) {
        this.saveExerciseListener = saveExerciseListener;
    }

    public static AddExerciseDialog getNewInstance(
            SaveExerciseListener saveExerciseListener
    ) {
        return new AddExerciseDialog(saveExerciseListener);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AddExerciseViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogAddExerciseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] exerciseDescriptions = Arrays.stream(ExerciseType.values())
                .map(ExerciseType::getDescription)
                .toArray(String[]::new);

        ArrayAdapter<String> exerciseTypeAdapter = new ArrayAdapter<>(
                requireContext(),
                R.layout.spinner_item,
                exerciseDescriptions);
        binding.exerciseType.setAdapter(exerciseTypeAdapter);

        binding.exerciseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] exerciseDescriptions = viewModel.getExercisesByType(position);

                binding.exerciseName.setVisibility(View.VISIBLE);

                ArrayAdapter<String> exerciseTypeAdapter = new ArrayAdapter<>(
                        requireContext(),
                        R.layout.spinner_item,
                        exerciseDescriptions);

                binding.exerciseName.setAdapter(exerciseTypeAdapter);

                binding.exerciseName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        viewModel.setExercise(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.exerciseAdd.setOnClickListener((v) -> {
            ExerciseModel model = viewModel.getCurrentExerciseModel();
            if (model != null && saveExerciseListener != null) {
                saveExerciseListener.save(model);
                dismiss();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
