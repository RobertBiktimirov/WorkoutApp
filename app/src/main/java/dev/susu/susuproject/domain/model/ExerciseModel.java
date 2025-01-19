package dev.susu.susuproject.domain.model;

public class ExerciseModel {

    private long id;
    private String title;
    private String description;
    private String imageUrl;

    private ExerciseType type;

    public ExerciseModel(long id, String title, String description, String imageUrl, ExerciseType type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ExerciseType getType() {
        return type;
    }

    public String getTypeDesc() {
        return type.getDescription();
    }

    public void setType(ExerciseType type) {
        this.type = type;
    }
}

