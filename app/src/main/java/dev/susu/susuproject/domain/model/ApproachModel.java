package dev.susu.susuproject.domain.model;

public class ApproachModel {
    public float weight;
    public int count;

    public ApproachModel(
            Float weight,
            int count
    ) {
        this.weight = weight;
        this.count = count;
    }

    public float getWeight() {
        return weight;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
