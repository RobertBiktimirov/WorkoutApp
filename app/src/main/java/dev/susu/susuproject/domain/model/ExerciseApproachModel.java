package dev.susu.susuproject.domain.model;

import java.util.ArrayList;
import java.util.List;

public class ExerciseApproachModel {

    private ExerciseModel exerciseModel;

    private ArrayList<ApproachModel> approachModels;

    public ExerciseApproachModel(ExerciseModel exerciseModel, ArrayList<ApproachModel> approachModels) {
        this.approachModels = approachModels;
        this.exerciseModel = exerciseModel;
    }

    public String getSmallViewApproach() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < approachModels.size(); i++) {
            ApproachModel model = approachModels.get(i);
            String weight = String.valueOf(model.weight).replace(".0", "");
            result.append(weight).append("x").append(model.count);
            if (i < approachModels.size() - 1) {
                result.append(",\n");
            }
        }

        return result.toString();
    }

    public void addApproach(ApproachModel approachModel) {
        approachModels.add(approachModel);
    }

    public ExerciseModel getExerciseModel() {
        return exerciseModel;
    }

    public List<ApproachModel> getApproachModels() {
        return approachModels;
    }

    public void setApproachModels(ArrayList<ApproachModel> approachModels) {
        this.approachModels = approachModels;
    }

    public void setExerciseModel(ExerciseModel exerciseModel) {
        this.exerciseModel = exerciseModel;
    }
}
