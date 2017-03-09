import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Karen on 3/1/17.
 */
public class TestAdmin {
    private IAdmin admin;
    private IStudent student;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.student = new Student();
    }


    /** CREATE CLASS
     * Create class {@code className} with capacity {@code capacity}
     * and assign instructor {@code instructorName}
     * The className/year pair must be unique and no instructor can be
     * assigned to more than two courses in a year.
     *
     * @param className Name of the class to be created
     * @param year Calendar year in which the course is to be taught, cannot be in the past
     * @param instructorName Name of instructor to be assigned,
     * @param capacity Maximum capacity of this class > 0
     */


    /* CREATE CLASS
    * SAMPLE TEST CASES:
    *  Check for duplicated class
    *  Check for duplicated year
    *  Check for duplicated class and year
    *  X Check that all year are 2017 and forward
    *  X Check for duplicated instructors (Cannot teach more than 2 courses per year)
    *  X Check for capacity: no negative and zero capacities
    * */

    @Test
    public void testClassExists() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        assertTrue(this.admin.classExists("ECS_Class", 2017));
    }

    @Test
    public void testClassFuture() {
        this.admin.createClass("ECS_Class", 2018, "ProfInstructor", 25);
        assertTrue(this.admin.classExists("ECS_Class", 2018));
    } //Check if class is in the future

    @Test
    public void testClassPast() {
        this.admin.createClass("ECS_Class", 2016, "ProfInstructor", 25);
        assertFalse(this.admin.classExists("ECS_Class", 2016));
    } //Class cannot be from the past


    @Test
    public void testDuplicateCourses1() {
        this.admin.createClass("CourseA", 2017, "ProfDevanbu", 25);
        this.admin.createClass("CourseB", 2017, "ProfDevanbu", 15);
        this.admin.createClass("CourseC", 2017, "ProfDevanbu", 10);
        this.admin.createClass("CourseD", 2017, "ProfDevanbu", 30);

        // assertFalse(this.admin.getClassInstructor("CourseA", 2017));
        // assertFalse(this.admin.getClassInstructor("CourseB", 2017));
        // assertFalse(this.admin.getClassInstructor("CourseC", 2017));
        // assertFalse(this.admin.getClassInstructor("CourseD", 2017));

        assertFalse(this.admin.classExists("CourseA", 2017));

    } //No duplicated instructors (Instructors cannot teach more than 2 courses per year)


    @Test
    public void testDuplicateCourses2() {
        this.admin.createClass("ECS_Class", 2017, "Tikki", 25);
        this.admin.createClass("ECS_Class", 2017, "Plagg", 25);

        assertFalse(this.admin.getClassInstructor("ECS_Class", 2017).equals(this.admin.getClassInstructor("ECS_Class", 2017)));
    } //Check whether the same class has two different instructors



    //********************************//

    /** CHANGE CAPACITY
     * Adjust the capacity (maximum number of students) of class {@code className} to new capacity {@code capacity}
     *
     * @param className Name of the class for which capacity should be changed
     * @param year Year in which this class is taught
     * @param capacity New capacity of this class, must be at least equal to the number of students enrolled
     */

    /* CHANGE CAPACITY
    * SAMPLE TEST CASES:
    *  Check that all year are 2017 and forward
    *  X Check for capacity: no negative and zero capacities
    *  Check that capacity is at least equal to number of enrolled students
    *  Check for whether changing capacity is less than registered students
    * */


    @Test
    public void testChangeCapacityNeg() {
        this.admin.createClass("Test", 2017, "ProfInstructor", -1);
        assertFalse(this.admin.classExists("Test", 2017));
    } //Capacity cannot be negative

    @Test
    public void testChangeCapacityEmpty() {
        this.admin.createClass("Test", 2017, "ProfInstructor", 0); //zero capacity
        assertFalse(this.admin.classExists("Test", 2017));
    } //Capacity must be a positive number (cannot be zero)


    @Test
    public void testCapacityStudents() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 3);
        this.admin.changeCapacity("ECS_Class", 2017, 5);

        this.student.registerForClass("Adrien", "ECS_Class", 2017);
        this.student.registerForClass("Marinette", "ECS_Class", 2017);
        this.student.registerForClass("Alya", "ECS_Class", 2017);


        assertTrue((this.admin.getClassCapacity("ECS_Class", 2017) == 5));

    } //Check that capacity is at least equal to number of enrolled students

    @Test
    public void testCapacityAtLeast() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 3);
        this.admin.changeCapacity("ECS_Class", 2017, 5);

        assertTrue((this.admin.getClassCapacity("ECS_Class", 2017) == 5));

    }


    @Test
    public void testCapacityAtLeastStudent() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 4);
        this.admin.changeCapacity("ECS_Class", 2017, 3);

        this.student.registerForClass("Adrien", "ECS_Class", 2017);
        this.student.registerForClass("Marinette", "ECS_Class", 2017);
        this.student.registerForClass("Alya", "ECS_Class", 2017);
        this.student.registerForClass("Nino", "ECS_Class", 2017);

        assertFalse((this.admin.getClassCapacity("ECS_Class", 2017) == 3));
    } //Check for whether changing capacity is less than registered students & negative

    @Test
    public void testCapacityLess() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 3);
        this.admin.changeCapacity("ECS_Class", 2017, 2);

        assertFalse((this.admin.getClassCapacity("ECS_Class", 2017) == 2));
    }

    @Test
    public void testCapacityMore() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 3);
        this.admin.changeCapacity("ECS_Class", 2017, 10);

        assertNotNull(this.admin.getClassCapacity("ECS_Class", 2017));
    }


    @Test
    public void testCapacityNeg() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 3);
        this.admin.changeCapacity("ECS_Class", 2017, -1);


        assertFalse((this.admin.getClassCapacity("ECS_Class", 2017) == -1));
    }


    @Test
    public void testCapacitySame() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        this.admin.changeCapacity("ECS_Class", 2017, 25);

        assertTrue((this.admin.getClassCapacity("ECS_Class", 2017) == 25));
    } //Check for whether changing capacity can stay same

}
