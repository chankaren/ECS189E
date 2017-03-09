import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Karen on 3/1/17.
 */
public class TestStudent {
    private IAdmin admin;
    private IInstructor instructor;
    private IStudent student;


    @Before
    public void setup() {
        this.admin = new Admin();
        this.instructor = new Instructor();
        this.student = new Student();
    }

    /*
     * Register student {@code studentName} for class {@code className} in year {@code year},
     * provided this class exists and has not met its enrolment capacity.
     *
     * @param className The name of the class to register for
     * @param year The year in which the class is taught
     */

    /* REGISTER FOR CLASS
    * SAMPLE TEST CASES:
    * Check if the class exists and is not full
    * Check whether student can register for existing class
    * Check whether student can register for nonexistent class
    * Check whether student registered for class in the past
    * Check whether student can register for full class
    * Check whether student can register for empty class
    */

    @Test
    public void testRegistered() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 30);
        this.student.registerForClass("Alya", "ECS_Class", 2017);
        assertTrue(this.student.isRegisteredFor("Alya", "ECS_Class", 2017));
    } //Check if student registered for class

    @Test
    public void testPrevYearReg() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 30);
        this.student.registerForClass("Alya", "ECS_Class", 2015);
        assertFalse(this.student.isRegisteredFor("Alya", "ECS_Class", 2015));
    } //Check if student registered for class in the past

    @Test
    public void testNotRegistered() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 30);
        this.student.dropClass("Chloe", "ECS_Class", 2017);
        assertFalse(this.student.isRegisteredFor("Chloe", "ECS_Class", 2017));
    } //Check whether student can register for nonexistent class

    @Test
    public void testRegisterFull() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 2);

        this.student.registerForClass("Adrien", "ECS_Class", 2017);
        this.student.registerForClass("Marinette", "ECS_Class", 2017);
        this.student.registerForClass("Alya", "ECS_Class", 2017);

        assertFalse(this.student.isRegisteredFor("Alya", "ECS_Class", 2017));
    } //Check whether student can register for full class



    //**************************



    /*
     * Drop class {@code className} in year {@code year} for student {@code studentName},
     * provided the student is registered and the class has not ended.
     *
     * @param studentName Name of the student to drop class for
     * @param className Name of the class to drop
     * @param year Year in which class is taught
     */


    /* DROP CLASS
    * SAMPLE TEST CASES:
    * X Check if student is registered for the class can drop
    * X Check if student is not registered can drop
    * Check if the class is in 2017
    * X Check if student can drop class that has not ended
    * X Check if student can drop class that ended in past year
    */


    @Test
    public void testStudentRegistered() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 30);
        this.student.registerForClass("Alya", "ECS_Class", 2017);
        this.student.dropClass("Alya", "ECS_Class", 2017);
        assertFalse(this.student.isRegisteredFor("Alya", "ECS_Class", 2017));
    } // Check if student is registered for the class can drop


    @Test
    public void testStudentNotRegistered() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 30);
        this.student.dropClass("Chloe", "ECS_Class", 2017);
        assertFalse(this.student.isRegisteredFor("Chloe", "ECS_Class", 2017));
    } // Check if student is not registered can drop



    @Test
    public void testDropDuring() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 30);
        this.student.registerForClass("Adrien", "ECS_Class", 2017);
        this.student.dropClass("Adrien", "ECS_Class", 2017);
        assertFalse(this.student.isRegisteredFor("Adrien", "ECS_Class", 2017));
    }


    @Test
    public void testDropYear() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 30);
        this.student.registerForClass("Adrien", "ECS_Class", 2017);
        this.student.dropClass("Adrien", "ECS_Class", 2018);
        assertFalse(this.student.isRegisteredFor("Adrien", "ECS_Class", 2018));
    } //Cannot drop class in the future

    @Test
    public void testDropPrev() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 30);
        this.student.registerForClass("Adrien", "ECS_Class", 2017);
        this.student.dropClass("Adrien", "ECS_Class", 2016);
        assertFalse(this.student.isRegisteredFor("Adrien", "ECS_Class", 2016));
    } //Cannot drop class from the past


    //**************************


    /*
     * Submit {@code studentName}'s homework solution {@code answerString} for homework {@code homeworkName} of class {@code className},
     * provided homework exists, student is registered and the class is taught in the current year
     *
     * @param studentName Name of the student
     * @param homeworkName Name of the homework
     * @param answerString Solution to the homework
     * @param className Name of class
     * @param year Year in which class is taught
     *
     */

    /* SUBMIT HOMEWORK
    * SAMPLE TEST CASES:
    * X Check if homework exists
    * X Check if student submitted homework
    * Check if student submit incorrect homework
    * Check if student submitted correct homework
    * Check if student submitted for current course
    * Check if student submitted for different course
    * Check if student submitted is a registered student
    */




    @Test
    public void testHomeworkExists() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 30);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        assertTrue(this.instructor.homeworkExists("ECS_Class", 2017, "Hero App"));
    } //Check if homework exists

    @Test
    public void testSubmitHomework() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 30);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        this.student.registerForClass("Adrien", "ECS_Class", 2017);
        this.student.submitHomework("Adrien", "Hero App", "AppSol", "ECS_Class", 2017);
        assertTrue(this.student.hasSubmitted("Adrien", "Hero App", "ECS_Class", 2017));
    } //Check if student submitted homework


    @Test
    public void testSubmitWrongHomework() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 30);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        this.student.registerForClass("Adrien", "ECS_Class", 2017);
        this.student.submitHomework("Adrien", "Villain App", "AppSol", "ECS_Class", 2017);
        assertFalse(this.student.hasSubmitted("Adrien", "Villain App", "ECS_Class", 2017));
    } //Check if student submitted incorrect homework

    @Test
    public void testSubmitHomeworkPastCourse() {
        this.admin.createClass("ECS_Class", 2015, "ProfInstructor", 30);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2015, "Hero App", "App to track villains");
        this.student.registerForClass("Adrien", "ECS_Class", 2015);
        this.student.submitHomework("Adrien", "Villain App", "AppSol", "ECS_Class", 2015);
        assertFalse(this.student.hasSubmitted("Adrien", "Villain App", "ECS_Class", 2015));
    } //Check if student submitted incorrect homework from previous year


    @Test
    public void testSubmitHomeworkFuture() {
        this.admin.createClass("ECS_Class", 2020, "ProfInstructor", 30);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2020, "Hero App", "App to track villains");
        this.student.registerForClass("Adrien", "ECS_Class", 2020);
        this.student.submitHomework("Adrien", "Villain App", "AppSol", "ECS_Class", 2020);
        assertFalse(this.student.hasSubmitted("Adrien", "Villain App", "ECS_Class", 2020));
    } //Check if student submitted incorrect homework from previous year


    @Test
    public void testSubmissionCurrent () {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 30);
        this.student.registerForClass("Adrien", "ECS_Class", 2017);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        this.student.submitHomework("Adrien", "Hero App", "AppSol", "ECS_Class", 2017);

        assertTrue(this.student.hasSubmitted("Adrien", "Hero App", "ECS_Class", 2017));
    } //Check if student submitted for current course


    @Test
    public void testSubmitDifferent() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 30);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");

        this.student.registerForClass("Adrien", "ECS_Class", 2017);
        this.student.submitHomework("Adrien", "Hero App", "AppSol", "ECS_Class", 2017);
        assertFalse(this.student.hasSubmitted("Adrien", "Hero App", "PSC_Class", 2017));
    } // Check if student submitted for different course

    @Test
    public void testSubmitNotPresent() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 30);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        //Student is not registered
        this.student.submitHomework("Adrien", "Hero App", "AppSol", "ECS_Class", 2017);
        assertFalse(this.student.hasSubmitted("Adrien", "Hero App", "ECS_Class", 2017));
    } // Check if student submitted is a registered student
}