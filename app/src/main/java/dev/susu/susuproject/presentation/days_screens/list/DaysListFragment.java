package dev.susu.susuproject.presentation.days_screens.list;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.hilt.android.AndroidEntryPoint;
import dev.susu.susuproject.R;
import dev.susu.susuproject.databinding.FragmentDaysListBinding;
import dev.susu.susuproject.presentation.BottomNavigationVisible;
import dev.susu.susuproject.presentation.days_screens.detail.DayDetailFragment;
import dev.susu.susuproject.presentation.days_screens.list.adapter.DaysListAdapter;

@AndroidEntryPoint
public class DaysListFragment extends Fragment {

    private DaysListViewModel viewModel;

    private DaysListAdapter daysListAdapter;

    @Nullable
    private FragmentDaysListBinding binding;

    private BottomNavigationVisible bottomNavigationVisible;

    private NavController navController;

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
        viewModel = new ViewModelProvider(this).get(DaysListViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentDaysListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUi();

        NavHostFragment navHostFragment =
                (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container_view);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        } else {
            Log.d("ROBBIK", "navHostFragment in DayListFragment = null");
        }

        viewModel.getCurrentDayModels().observe(getViewLifecycleOwner(), dayModels -> {
            daysListAdapter.submitList(dayModels);
            if (binding != null) {
                binding.workoutCount.setText("Всего тренировок - " + dayModels.size());

                if (dayModels.isEmpty()) {
                    binding.emptyDayText.setVisibility(View.VISIBLE);
                } else {
                    binding.emptyDayText.setVisibility(View.GONE);
                }
            }
        });

        requireActivity().getSupportFragmentManager().setFragmentResultListener(
                DayDetailFragment.Companion.getDayIdKey(),
                getViewLifecycleOwner(),
                (requestKey, result) -> {
                    int id = result.getInt(DayDetailFragment.Companion.getDayIdKey());
                    if (id != 0) {
                        int position = viewModel.getPositionDayById(id);
                        Log.d("ROBBIK", "positiopn = " + position);
                        if (position != -1) {
                            daysListAdapter.notifyItemChanged(viewModel.getPositionDayById(position));
                        }
                    }
                });
    }

    private void setupUi() {
        assert binding != null;
        bottomNavigationVisible.showBottomSheet();

        daysListAdapter = new DaysListAdapter(dayModel -> {
            if (navController != null) {
                Bundle params = new Bundle();
                params.putInt(DayDetailFragment.Companion.getDayIdKey(), dayModel.getId());
                navController.navigate(R.id.action_daysListFragment_to_dayDetailFragment, params);
            }
        });
        binding.workoutDays.setAdapter(daysListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}