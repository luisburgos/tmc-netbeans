package fi.helsinki.cs.tmc.data;

import fi.helsinki.cs.tmc.core.domain.Exercise;

import java.util.List;

@Deprecated
public class ExerciseListUtils {
    /**
     * Returns the exercise with the given name or null if not found.
     */
    @Deprecated
    public static Exercise getExerciseByName(List<Exercise> exercises, String exerciseName) {
        if (exerciseName == null) {
            return null;
        }

        for (Exercise exercise : exercises) {
            if (exercise.getName().equals(exerciseName)) {
                return exercise;
            }
        }

        return null;
    }

    @Deprecated
    public static void setCourseNameForEach(List<Exercise> exercises, String courseName) {
        for (Exercise ex : exercises) {
            ex.setCourseName(courseName);
        }
    }
}
