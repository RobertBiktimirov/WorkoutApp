package dev.susu.susuproject.presentation.settings_screen.change_theme_alert;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import dev.susu.susuproject.app.theme_app.ThemeEnum;
import dev.susu.susuproject.databinding.FragmentChangeThemeBinding;

@AndroidEntryPoint
public class ChangeThemeDialogFragment extends DialogFragment {

    private FragmentChangeThemeBinding binding;

    private ChangeThemeDialogViewModel viewModel;

    public ChangeThemeDialogFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ChangeThemeDialogViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChangeThemeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getDialog() != null && getDialog().getWindow() != null && getDialog().getWindow().getAttributes() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow().setAttributes(params);
        }

        ThemeEnum themeEnum = viewModel.themeEnum;

        switch (themeEnum) {
            case SYSTEM:
                binding.systemTheme.setChecked(true);
            case DARK:
                binding.darkTheme.setChecked(true);
            case DAY:
                binding.lightTheme.setChecked(true);
        }

        binding.systemTheme.setOnClickListener(v -> {
            viewModel.changeTheme(ThemeEnum.SYSTEM);
            dismiss();
        });

        binding.lightTheme.setOnClickListener(v -> {
            viewModel.changeTheme(ThemeEnum.DAY);
            dismiss();
        });

        binding.darkTheme.setOnClickListener(v -> {
            viewModel.changeTheme(ThemeEnum.DARK);
            dismiss();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}