package dev.susu.susuproject.presentation.exercise_screens.list;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;
import dev.susu.susuproject.R;
import dev.susu.susuproject.databinding.FragmentExerciseListBinding;
import dev.susu.susuproject.domain.model.ExerciseModel;
import dev.susu.susuproject.presentation.BottomNavigationVisible;
import dev.susu.susuproject.presentation.exercise_screens.detail.ExerciseDetailFragment;
import dev.susu.susuproject.presentation.exercise_screens.list.adapter.ExerciseInfoDelegate;
import dev.susu.susuproject.presentation.exercise_screens.list.adapter.ExerciseInfoDelegateItem;
import dev.susu.susuproject.presentation.exercise_screens.list.adapter.ExerciseTypeDelegate;
import dev.susu.susuproject.presentation.exercise_screens.list.adapter_utils.AdapterItem;
import dev.susu.susuproject.presentation.exercise_screens.list.adapter_utils.OutlineAdapter;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

@AndroidEntryPoint
public class ExerciseListFragment extends Fragment {

    FragmentExerciseListBinding binding;

    private ExerciseListViewModel viewModel;

    public static ExerciseListFragment newInstance() {
        return new ExerciseListFragment();
    }

    private BottomNavigationVisible bottomNavigationVisible;

    private OutlineAdapter adapter;

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
        viewModel = new ViewModelProvider(this).get(ExerciseListViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentExerciseListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationVisible.showBottomSheet();

        NavHostFragment navHostFragment =
                (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container_view);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        } else {
            Log.d("ROBBIK", "navHostFragment in ExerciseListFragment = null");
        }
        adapter = new OutlineAdapter();

        adapter.addDelegate(new ExerciseInfoDelegate(this::openExerciseDetail));

        adapter.addDelegate(new ExerciseTypeDelegate());
        binding.exerciseList.setAdapter(adapter);

        viewModel.getExercisesData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new DisposableObserver<>() {
                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull ArrayList<AdapterItem> adapterItems) {
                                adapter.submitList(adapterItems);
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                Log.d("ROBBIK", "viewModel.getExercisesData() error " + e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                Log.d("ROBBIK", "viewModel.getExercisesData() onComplete");
                            }
                        }
                );
    }

    public void openExerciseDetail(ExerciseModel exerciseModel) {
        if (navController != null) {
            Bundle params = new Bundle();
            params.putLong(ExerciseDetailFragment.Companion.getExerciseIdKey(), exerciseModel.getId());
            navController.navigate(R.id.action_exerciseListFragment_to_exerciseDetailFragment, params);
        }
    }
}