package dev.susu.susuproject.presentation.add_screen;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.hilt.android.AndroidEntryPoint;
import dev.susu.susuproject.R;
import dev.susu.susuproject.databinding.FragmentAddBinding;
import dev.susu.susuproject.presentation.BottomNavigationVisible;

@AndroidEntryPoint
public class AddFragment extends Fragment {

    private FragmentAddBinding binding;

    private AddViewModel viewModel;

    public static AddFragment newInstance() {
        return new AddFragment();
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
        viewModel = new ViewModelProvider(this).get(AddViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationVisible.showBottomSheet();

        NavController navController = Navigation.findNavController(view);

        binding.dayAdd.setOnClickListener(v -> {
            navController.navigate(R.id.action_addFragment_to_dayDetailFragment);
        });

        binding.exerciseAdd.setOnClickListener(v -> {
            navController.navigate(R.id.action_addFragment_to_exerciseDetailFragment);
        });

    }
}