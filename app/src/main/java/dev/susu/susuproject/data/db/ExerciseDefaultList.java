package dev.susu.susuproject.data.db;

import java.util.ArrayList;
import java.util.List;

import dev.susu.susuproject.data.db.entities.ExerciseEntity;
import dev.susu.susuproject.domain.model.ExerciseType;

public class ExerciseDefaultList {

    public static List<ExerciseEntity> getDefaultExerciseList() {
        List<ExerciseEntity> exercises = new ArrayList<>();

        // Упражнения для грудных мышц
        exercises.add(new ExerciseEntity("Жим штанги на скамье (горизонтально)", "Классическое упражнение для грудных мышц.", "", ExerciseType.CHEST));
        exercises.add(new ExerciseEntity("Жим штанги на скамье (наклонно)", "Упражнение для верхней части груди.", "", ExerciseType.CHEST));
        exercises.add(new ExerciseEntity("Жим штанги на скамье (вниз)", "Упражнение для нижней части груди.", "", ExerciseType.CHEST));
        exercises.add(new ExerciseEntity("Жим гантелей на скамье (горизонтально)", "Изоляция грудных мышц.", "", ExerciseType.CHEST));
        exercises.add(new ExerciseEntity("Жим гантелей на скамье (наклонно)", "Упражнение для верхней части груди.", "", ExerciseType.CHEST));
        exercises.add(new ExerciseEntity("Разводка гантелей на скамье (горизонтально)", "Укрепляет грудные мышцы.", "", ExerciseType.CHEST));
        exercises.add(new ExerciseEntity("Разводка гантелей на скамье (наклонно)", "Изолированное упражнение для груди.", "", ExerciseType.CHEST));
        exercises.add(new ExerciseEntity("Отжимания от пола (широким хватом)", "Работа на грудные мышцы и трицепсы.", "", ExerciseType.CHEST));
        exercises.add(new ExerciseEntity("Отжимания от пола (узким хватом)", "Для трицепсов и внутренней части груди.", "", ExerciseType.CHEST));
        exercises.add(new ExerciseEntity("Отжимания на брусьях", "Общее упражнение для мышц груди и трицепсов.", "", ExerciseType.CHEST));
        exercises.add(new ExerciseEntity("Пулловер с гантелей или со штангой", "Развивает грудные и спинные мышцы.", "", ExerciseType.CHEST));

        // Упражнения для спины
        exercises.add(new ExerciseEntity("Тяга штанги в наклоне", "Развивает широчайшие мышцы спины.", "", ExerciseType.BACK));
        exercises.add(new ExerciseEntity("Тяга верхнего блока к груди", "Эффективно развивает спину.", "", ExerciseType.BACK));
        exercises.add(new ExerciseEntity("Тяга горизонтального блока", "Изоляция мышц спины.", "", ExerciseType.BACK));
        exercises.add(new ExerciseEntity("Тяга гантели одной рукой", "Для изолированной тренировки широчайших.", "", ExerciseType.BACK));
        exercises.add(new ExerciseEntity("Становая тяга (классическая)", "Общее силовое упражнение для спины и ног.", "", ExerciseType.BACK));
        exercises.add(new ExerciseEntity("Становая тяга (на прямых ногах)", "Работа на заднюю поверхность бедер и спину.", "", ExerciseType.BACK));
        exercises.add(new ExerciseEntity("Подтягивания (широким хватом)", "Работа на широчайшие и бицепсы.", "", ExerciseType.BACK));
        exercises.add(new ExerciseEntity("Подтягивания (узким хватом)", "Работа на спину.", "", ExerciseType.BACK));
        exercises.add(new ExerciseEntity("Гиперэкстензия", "Укрепление разгибателей спины.", "", ExerciseType.BACK));
        exercises.add(new ExerciseEntity("Румынская тяга", "Работа на задние бедра и спину.", "", ExerciseType.BACK));

        // Упражнения для ног
        exercises.add(new ExerciseEntity("Приседания со штангой (классические)", "Основное упражнение для ног.", "", ExerciseType.LEGS));
        exercises.add(new ExerciseEntity("Жим ногами в тренажере", "Изолированное упражнение для ног.", "", ExerciseType.LEGS));
        exercises.add(new ExerciseEntity("Выпады (с гантелями)", "Укрепление квадрицепсов.", "", ExerciseType.LEGS));
        exercises.add(new ExerciseEntity("Выпады (со штангой)", "Эффективное упражнение для ног.", "", ExerciseType.LEGS));
        exercises.add(new ExerciseEntity("Становая тяга на прямых ногах", "Работа на заднюю поверхность бедер.", "", ExerciseType.LEGS));
        exercises.add(new ExerciseEntity("Подъемы на носки (стоя)", "Упражнение для икр.", "", ExerciseType.LEGS));
        exercises.add(new ExerciseEntity("Подъемы на носки (сидя)", "Экстра работа на икроножные мышцы.", "", ExerciseType.LEGS));
        exercises.add(new ExerciseEntity("Разгибание ног в тренажере", "Изолированная работа на квадрицепсы.", "", ExerciseType.LEGS));
        exercises.add(new ExerciseEntity("Сгибание ног в тренажере", "Работа на заднюю поверхность бедер.", "", ExerciseType.LEGS));
        exercises.add(new ExerciseEntity("Бульгарские сплиты", "Тренировка координации и баланса.", "", ExerciseType.LEGS));
        exercises.add(new ExerciseEntity("Махи ногами", "Работа на ягодицы и бедра.", "", ExerciseType.LEGS));

        // Упражнения для плеч
        exercises.add(new ExerciseEntity("Жим штанги (сидя)", "Развивает плечевые мышцы.", "", ExerciseType.SHOULDERS));
        exercises.add(new ExerciseEntity("Жим штанги (стоя)", "Общее упражнение для плеч.", "", ExerciseType.SHOULDERS));
        exercises.add(new ExerciseEntity("Жим гантелей (сидя)", "Работа на дельты.", "", ExerciseType.SHOULDERS));
        exercises.add(new ExerciseEntity("Жим гантелей (стоя)", "Развитие плечевого пояса.", "", ExerciseType.SHOULDERS));
        exercises.add(new ExerciseEntity("Подъемы гантелей в стороны", "Изоляция дельт.", "", ExerciseType.SHOULDERS));
        exercises.add(new ExerciseEntity("Подъемы гантелей вперед", "Работа на передние дельты.", "", ExerciseType.SHOULDERS));
        exercises.add(new ExerciseEntity("Тяга штанги к подбородку", "Общее упражнение для плеч.", "", ExerciseType.SHOULDERS));
        exercises.add(new ExerciseEntity("Arnold press", "Интенсивная тренировка для дельт.", "", ExerciseType.SHOULDERS));
        exercises.add(new ExerciseEntity("Разводка гантелей в стороны", "Работа для задних дельтов.", "", ExerciseType.SHOULDERS));
        exercises.add(new ExerciseEntity("Обратная разводка", "Укрепляет задние дельты.", "", ExerciseType.SHOULDERS));

        // Упражнения для бицепсов
        exercises.add(new ExerciseEntity("Сгибание рук со штангой (обратным хватом)", "Работа на бицепсы.", "", ExerciseType.BICEPS));
        exercises.add(new ExerciseEntity("Сгибание рук с гантелями (классическое)", "Основное упражнение для бицепсов.", "", ExerciseType.BICEPS));
        exercises.add(new ExerciseEntity("Сгибание рук с гантелями (молотковое)", "Работа на внешние отделы бицепсов.", "", ExerciseType.BICEPS));
        exercises.add(new ExerciseEntity("Сгибание рук на скамье Скотта", "Изолированная работа на бицепсы.", "", ExerciseType.BICEPS));
        exercises.add(new ExerciseEntity("Сгибание рук с тяжелым весом", "Упражнение для максимальной нагрузки.", "", ExerciseType.BICEPS));
        exercises.add(new ExerciseEntity("Сгибание рук на верхнем блоке", "Работа на бицепсы с тонусом.", "", ExerciseType.BICEPS));

        // Упражнения для трицепсов
        exercises.add(new ExerciseEntity("Французский жим (с штангой)", "Эффективное упражнение для трицепсов.", "", ExerciseType.TRICEPS));
        exercises.add(new ExerciseEntity("Французский жим (с гантелями)", "Изоляция трицепсов.", "", ExerciseType.TRICEPS));
        exercises.add(new ExerciseEntity("Разгибание рук на блоке (вверх)", "Работа на трицепсы.", "", ExerciseType.TRICEPS));
        exercises.add(new ExerciseEntity("Разгибание рук на блоке (вниз)", "Эффективная изоляция на трицепсы.", "", ExerciseType.TRICEPS));
        exercises.add(new ExerciseEntity("Отжимания на брусьях (узким хватом)", "Упражнение для трицепсов и груди.", "", ExerciseType.TRICEPS));
        exercises.add(new ExerciseEntity("Жим узким хватом", "Работа на трицепсы.", "", ExerciseType.TRICEPS));
        exercises.add(new ExerciseEntity("Разгибание руки в наклоне", "Изоляция трицепсов.", "", ExerciseType.TRICEPS));
        exercises.add(new ExerciseEntity("Отжимания от пола (в узком хвате)", "Работа на трицепсы.", "", ExerciseType.TRICEPS));

        // Упражнения для пресса
        exercises.add(new ExerciseEntity("Скручивания (обычные)", "Классическое упражнение для мышц пресса.", "", ExerciseType.ABS));
        exercises.add(new ExerciseEntity("Скручивания (на наклонной скамье)", "Работа на верхнюю часть пресса.", "", ExerciseType.ABS));
        exercises.add(new ExerciseEntity("Подъемы ног", "Эффективная изоляция для нижней части пресса.", "", ExerciseType.ABS));
        exercises.add(new ExerciseEntity("Планка (прямой)", "Упражнение для укрепления кора.", "", ExerciseType.ABS));
        exercises.add(new ExerciseEntity("Планка (боковой)", "Работа на стабилизаторы.", "", ExerciseType.ABS));
        exercises.add(new ExerciseEntity("Русские повороты с весом", "Упражнение для косых мышц живота.", "", ExerciseType.ABS));
        exercises.add(new ExerciseEntity("Велосипед", "Скручивания в разных положениях.", "", ExerciseType.ABS));
        exercises.add(new ExerciseEntity("Подъем туловища на тренажере для пресса", "Изолированная работа на пресс.", "", ExerciseType.ABS));

        // Упражнение для кардио
        exercises.add(new ExerciseEntity("Бег на беговой дорожке", "Эффективное кардио для сердечно-сосудистой системы.", "", ExerciseType.CARDIO));
        exercises.add(new ExerciseEntity("Скакалка", "Отличное кардио и координационное упражнение.", "", ExerciseType.CARDIO));
        exercises.add(new ExerciseEntity("Велотренажер", "Кардио-тренировка для ног и сердечно-сосудистой системы.", "", ExerciseType.CARDIO));
        exercises.add(new ExerciseEntity("Эллиптический тренажер", "Мягкое кардио, не нагружающее суставы.", "", ExerciseType.CARDIO));
        exercises.add(new ExerciseEntity("Гребной тренажер", "Полное тело, кардио и силовая тренировка.", "", ExerciseType.CARDIO));
        exercises.add(new ExerciseEntity("Высокие колени", "Интенсивное кардио, развивающее скорость.", "", ExerciseType.CARDIO));


        // Прочие упражнения
        exercises.add(new ExerciseEntity("Силовые тренировки", "Общая категория, включая разные техники.", "image_url_71", ExerciseType.OTHER));
        exercises.add(new ExerciseEntity("Йога", "Упражнения для растяжки и релаксации.", "image_url_72", ExerciseType.OTHER));
        exercises.add(new ExerciseEntity("Пилатес", "Фокус на укреплении кора и гибкости.", "image_url_73", ExerciseType.OTHER));
        exercises.add(new ExerciseEntity("Стретчинг", "Гибкость и восстановление.", "image_url_74", ExerciseType.OTHER));
        exercises.add(new ExerciseEntity("Танцы", "Веселая форма кардио, тренирующая координацию.", "image_url_75", ExerciseType.OTHER));


        return exercises;
    }
}