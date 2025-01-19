package dev.susu.susuproject.presentation.exercise_screens.detail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Arrays;

import dagger.hilt.android.AndroidEntryPoint;
import dev.susu.susuproject.R;
import dev.susu.susuproject.databinding.FragmentExerciseDetailBinding;
import dev.susu.susuproject.domain.model.ExerciseType;
import dev.susu.susuproject.presentation.BottomNavigationVisible;

@AndroidEntryPoint
public class ExerciseDetailFragment extends Fragment {

    public static class Companion {
        private final static String EXERCISE_ID_KEY = "exerciseIdKey";

        public static String getExerciseIdKey() {
            return EXERCISE_ID_KEY;
        }
    }

    private NavController navController;

    private FragmentExerciseDetailBinding binding;

    private ExerciseDetailViewModel viewModel;

    public static ExerciseDetailFragment newInstance() {
        return new ExerciseDetailFragment();
    }

    private BottomNavigationVisible bottomNavigationVisible;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof BottomNavigationVisible) {
            this.bottomNavigationVisible = (BottomNavigationVisible) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ExerciseDetailViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentExerciseDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationVisible.hideBottomSheet();

        NavHostFragment navHostFragment =
                (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container_view);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        } else {
            Log.d("ROBBIK", "navHostFragment in ExerciseDetailFragment = null");
        }

        String[] exerciseDescriptions = Arrays.stream(ExerciseType.values())
                .map(ExerciseType::getDescription)
                .toArray(String[]::new);

        ArrayAdapter<String> exerciseTypeAdapter = new ArrayAdapter<>(
                requireContext(),
                R.layout.spinner_item,
                exerciseDescriptions);
        binding.exerciseType.setAdapter(exerciseTypeAdapter);

        ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    if (uri != null) {
                        viewModel.setExerciseImage(uri.toString());
                        setExerciseImage(uri.toString());
                    }
                });

        binding.exerciseImage.setOnClickListener(v -> pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build()));

        binding.closeIcon.setOnClickListener(v -> popBackStack());

        if (getArguments() != null) {
            long exerciseId = getArguments().getLong(Companion.getExerciseIdKey(), -1);
            viewModel.getExerciseModel(exerciseId);
            binding.delete.setVisibility(View.VISIBLE);

            binding.delete.setOnClickListener((v) -> {
                viewModel.deleteExercise();
                popBackStack();
            });
        } else {
            binding.delete.setVisibility(View.GONE);
        }

        binding.save.setOnClickListener((v) -> {
            viewModel.saveExercise();
            popBackStack();
        });

        viewModel.getCurrentExerciseModel().observe(getViewLifecycleOwner(), exerciseModel -> {
            if (!exerciseModel.getImageUrl().isEmpty()) {
                setExerciseImage(exerciseModel.getImageUrl());
            }
            binding.exerciseTitle.setText(exerciseModel.getTitle());
            binding.exerciseDesc.setText(exerciseModel.getDescription());
            binding.exerciseType.setSelection(ExerciseType.getPosition(exerciseModel.getType()));
        });

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), error ->
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show());

        binding.exerciseTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setExerciseTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        binding.exerciseDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setExerciseDesc(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        binding.exerciseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.setExerciseType(ExerciseType.getExerciseTypeByPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setExerciseImage(String uri) {
        Glide.with(requireContext())
                .load(Uri.parse(uri))
                .centerCrop()
                .into(binding.exerciseImage);
    }

    private void popBackStack() {
        if (navController != null) {
            navController.popBackStack();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        bottomNavigationVisible = null;
    }
}