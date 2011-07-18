/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.tmc.data;

import fi.helsinki.cs.tmc.data.Exercise;
import fi.helsinki.cs.tmc.data.ExerciseCollection;
import fi.helsinki.cs.tmc.data.Course;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ttkoivis
 */
public class ExerciseCollectionTest {
    
    private ExerciseCollection exercises;
    
    public ExerciseCollectionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        Exercise exercise;
        Date date = new Date();
        
        exercises = new ExerciseCollection(new Course());
        for(int i=0; i < 1000; i++) {
            exercise = new Exercise();
            exercise.setName("Exercise " + i);
            exercise.setDownloadAddress("http://192.168.0.0:50000/download");
            exercise.setReturnAddress("http://192.168.0.0:51000/upload");
            exercise.setDeadline(date);
            exercises.add(exercise);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of searchExercise method, of class ExerciseCollection.
     */
    @Test
    public void testSearchExercise() {
        System.out.println("searchExercise");
        Exercise result;
        result = exercises.getExerciseByName(null);
        assertNull(result);
        result = exercises.getExerciseByName("Exercise 100");
        assertEquals("Exercise 100", result.getName());
        result = exercises.getExerciseByName("Exercise 200");
        assertEquals("Exercise 200", result.getName());
        result = exercises.getExerciseByName("Exercise 300");
        assertEquals("Exercise 300", result.getName());
        result = exercises.getExerciseByName("Exercise 400");
        assertEquals("Exercise 400", result.getName());
        result = exercises.getExerciseByName("Exercise 500");
        assertEquals("Exercise 500", result.getName());
        result = exercises.getExerciseByName("Exercise 600");
        assertEquals("Exercise 600", result.getName());
        result = exercises.getExerciseByName("Exercise 700");
        assertEquals("Exercise 700", result.getName());
        result = exercises.getExerciseByName("Exercise 800");
        assertEquals("Exercise 800", result.toString());
        result = exercises.getExerciseByName("Exercise 900");
        assertEquals("Exercise 900", result.toString());
        result = exercises.getExerciseByName("Exercise 1000");
        assertNull(result);
        
        Date date = new Date();
        result = exercises.getExerciseByName("Exercise 1");
        Boolean deadlineEnded = result.isDeadlineEnded(date);
        assertTrue(deadlineEnded);
        Date date2 = result.getDeadline();
        deadlineEnded = result.isDeadlineEnded(date2);
        assertFalse(deadlineEnded);
        
        result = exercises.getExerciseByName("Exercise 1");
        String address = result.getDownloadAddress();
        assertEquals("http://192.168.0.0:50000/download", address);
        address = result.getReturnAddress();
        assertEquals("http://192.168.0.0:51000/upload", address);

    }
}
