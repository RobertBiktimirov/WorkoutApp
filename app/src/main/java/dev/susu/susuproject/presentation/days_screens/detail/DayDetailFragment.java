package dev.susu.susuproject.presentation.days_screens.detail;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.hilt.android.AndroidEntryPoint;
import dev.susu.susuproject.R;
import dev.susu.susuproject.databinding.DialogAddApproachBinding;
import dev.susu.susuproject.databinding.FragmentDayDetailBinding;
import dev.susu.susuproject.domain.model.ApproachModel;
import dev.susu.susuproject.domain.model.DayModel;
import dev.susu.susuproject.domain.model.ExerciseApproachModel;
import dev.susu.susuproject.domain.model.ExerciseModel;
import dev.susu.susuproject.presentation.BottomNavigationVisible;
import dev.susu.susuproject.presentation.days_screens.detail.adapter.AddExerciseAdapter;
import dev.susu.susuproject.presentation.days_screens.detail.alerts.AddExerciseDialog;
import dev.susu.susuproject.presentation.days_screens.detail.alerts.ApproachAddDialogFragment;
import dev.susu.susuproject.presentation.exercise_screens.detail.ExerciseDetailFragment;

@AndroidEntryPoint
public class DayDetailFragment extends Fragment {

    public static class Companion {
        private final static String DAY_ID_KEY = "day_id";

        private final static String UPDATE_KEY = "update key";
        private final static String DELETE_KEY = "delete key";

        public static String getDayIdKey() {
            return DAY_ID_KEY;
        }
    }

    private DayDetailViewModel viewModel;

    private FragmentDayDetailBinding binding;

    private NavController navController;

    public static DayDetailFragment newInstance() {
        return new DayDetailFragment();
    }

    private AddExerciseAdapter addExerciseAdapter;

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
        viewModel = new ViewModelProvider(this).get(DayDetailViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDayDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationVisible.hideBottomSheet();

        if (getArguments() != null) {
            int dayId = getArguments().getInt(Companion.getDayIdKey(), -1);
            viewModel.getDayModel(dayId);
            binding.delete.setVisibility(View.VISIBLE);

            binding.delete.setOnClickListener((v) -> {
                viewModel.deleteDay();
                popBackStack();
            });
        } else {
            binding.delete.setVisibility(View.GONE);
        }


        addExerciseAdapter = new AddExerciseAdapter(exerciseModel -> {
            ApproachAddDialogFragment approachAddDialogFragment = ApproachAddDialogFragment.getNewInstance(approachModel -> viewModel.addApproach(exerciseModel, approachModel));
            approachAddDialogFragment.show(getChildFragmentManager(), null);
        });

        binding.exercises.setAdapter(addExerciseAdapter);

        viewModel.getListExerciseApproach().observe(getViewLifecycleOwner(), exerciseApproachModels -> {
            addExerciseAdapter.submitList(exerciseApproachModels);
            addExerciseAdapter.notifyItemChanged(exerciseApproachModels.size() - 1);
        });

        viewModel.getHeaderDay().observe(getViewLifecycleOwner(), header -> {
            binding.headerTitle.setText(header);
        });

        NavHostFragment navHostFragment =
                (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container_view);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        } else {
            Log.d("ROBBIK", "navHostFragment in DayDetailFragment = null");
        }

        binding.addExercise.setOnClickListener((v) -> {
            BottomSheetDialogFragment addExerciseBottomSheet = AddExerciseDialog
                    .getNewInstance(exerciseModel -> viewModel.addExercise(exerciseModel));
            addExerciseBottomSheet.show(getChildFragmentManager(), null);
        });

        binding.closeIcon.setOnClickListener((v) -> popBackStack());

        binding.save.setOnClickListener((v) -> {
            viewModel.saveDay();
            popBackStack();
        });

        binding.headerTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setCurrentHeader(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    private void popBackStack() {
        int id = viewModel.getCurrentDayId();
        Bundle params = new Bundle();
        params.putInt(Companion.getDayIdKey(), id);
        requireActivity().getSupportFragmentManager().setFragmentResult(Companion.getDayIdKey(), params);

        if (navController != null) {
            navController.popBackStack();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}