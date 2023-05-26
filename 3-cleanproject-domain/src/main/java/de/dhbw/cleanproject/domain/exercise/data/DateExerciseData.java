package de.dhbw.cleanproject.domain.exercise.data;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
@Builder
public class DateExerciseData {

    private final String name;
    private final Integer sets;
    private final Integer reps;
    private final Double weight;

    private final LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateExerciseData that = (DateExerciseData) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(sets, that.sets) &&
                Objects.equals(reps, that.reps) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sets, reps, weight, date);
    }
}
