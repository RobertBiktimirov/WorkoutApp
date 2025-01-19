package dev.susu.susuproject.presentation.days_screens.detail.alerts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import dagger.hilt.android.AndroidEntryPoint;
import dev.susu.susuproject.databinding.DialogAddApproachBinding;
import dev.susu.susuproject.domain.model.ApproachModel;

@AndroidEntryPoint
public class ApproachAddDialogFragment extends DialogFragment {

    public interface SaveApproachListener {
        void save(ApproachModel approachModel);
    }

    private SaveApproachListener saveApproachListener;

    private DialogAddApproachBinding binding;


    private ApproachAddDialogFragment(
            SaveApproachListener saveApproachListener
    ) {
        this.saveApproachListener = saveApproachListener;
    }

    public static ApproachAddDialogFragment getNewInstance(
            SaveApproachListener saveApproachListener
    ) {
        return new ApproachAddDialogFragment(saveApproachListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogAddApproachBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.approachAdd.setOnClickListener((v) -> {
            if (binding.count.getText().length() == 0 || binding.weight.getText().length() == 0) {
                Toast.makeText(requireContext(), "Не все поля заполнены", Toast.LENGTH_SHORT).show();
            } else {
                Float weight = Float.parseFloat(binding.weight.getText().toString());
                int count = Integer.parseInt(binding.count.getText().toString());
                if (saveApproachListener != null) {
                    saveApproachListener.save(new ApproachModel(weight, count));
                    dismiss();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
