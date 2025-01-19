package dev.susu.susuproject.domain.model;

public enum ExerciseType {
    CHEST("Грудные мышцы"),
    BACK("Спина"),
    LEGS("Ноги"),
    SHOULDERS("Плечи"),
    BICEPS("Бицепсы"),
    TRICEPS("Трицепсы"),
    ABS("Пресс"),
    CARDIO("Кардио"),
    OTHER("Прочие");

    private final String description;

    ExerciseType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static int getPosition(ExerciseType type) {
        ExerciseType[] values = ExerciseType.values();

        for (int i = 0; i < values.length - 1; i++) {
            if (values[i].equals(type)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Неизвестный type: " + type.toString());
    }

    public static ExerciseType getExerciseTypeByPosition(int position) {
        return ExerciseType.values()[position];
    }

}
