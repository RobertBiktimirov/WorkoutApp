package dev.susu.susuproject.presentation.settings_screen;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.hilt.android.AndroidEntryPoint;
import dev.susu.susuproject.R;
import dev.susu.susuproject.databinding.FragmentSettingsBinding;
import dev.susu.susuproject.presentation.BottomNavigationVisible;
import dev.susu.susuproject.presentation.settings_screen.change_theme_alert.ChangeThemeDialogFragment;

@AndroidEntryPoint
public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    private SettingsViewModel viewModel;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
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
        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationVisible.showBottomSheet();

        binding.changeTheme.setOnClickListener(v -> {
            DialogFragment changeThemeDialog = new ChangeThemeDialogFragment();
            changeThemeDialog.show(getChildFragmentManager(), null);
        });
    }
}