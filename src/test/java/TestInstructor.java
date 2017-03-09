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
public class TestInstructor {
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
     * Add homework to class {@code className}, taught in year {@code year}, with title {@code homeworkName}, provided this instructor has been assigned to this class.
     *
     * @param instructorName The name of the instructor who assigns the homework
     * @param className Class for which the homework should be added
     * @param year Year of class
     * @param homeworkName Name of the homework assignment to be added
     * @param homeworkDescription Description of homework
     */


    /* ADD HOMEWORK
    * SAMPLE TEST CASES:
    *  X Check if homework was created
    *  X Check if homework will be created
    *  X Check for name of instructor is person who assigned grade
    *  X Check if the homework was added to the right class
    *  X Check if the homework was added to a nonexistent class
    *  Check name of homework to grade
    *  Check name of student to grade
    *  Check percentage grade of student
    * */


    /*   @Test
       public void testGradeName() {
           this.instructor.addHomework("Test", "ECS_Class", 2016, "ProfInstructor", "App");
           assertFalse(this.admin.classExists("Test", 2016));
       } //Name of instructor is person who assigned grade

   */
    @Test
    public void testHomeworkExists() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        assertTrue(this.instructor.homeworkExists("ECS_Class", 2017, "Hero App"));
    } //Check if homework was created


    @Test
    public void testHomeworkDoesNotExists() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        assertFalse(this.instructor.homeworkExists("ECS_Class", 2017, "Hero App"));
    } //Check if homework was not created


    @Test
    public void testHomeworkWillExist() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2018, "Hero App", "App to track villains");
        assertFalse(this.instructor.homeworkExists("ECS_Class", 2018, "Hero App"));
    } //Check if homework will be created


    @Test
    public void testHomeworktoClass() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        assertFalse(this.instructor.homeworkExists("PSC_Class", 2017, "Hero App"));
    } //Check if the homework was added to the right class

    @Test
    public void testHomeworktoNoClass() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        this.instructor.addHomework("ProfInstructor", "PSC_Class", 2017, "Villain App", "App to be a villains");
        //Instructor is adding homework to the wrong class
        assertFalse(this.instructor.homeworkExists("PSC_Class", 2017, "Villain App"));
    } //Check if the homework was added to a nonexistent class




    //************************//

    /*
     * Assign grade {@code grade} to student {@code studentName} for homework {@code homeworkName} in class {@code className},
     * provided this instructor has been assigned to this class, the homework has been assigned and the student has submitted this homework.
     *
     * @param instructorName The name of the instructor who assigns the grade to the homework
     * @param className Name of the class to assign grade for
     * @param year Year in which said class is taught
     * @param homeworkName Name of homework to grade
     * @param studentName Name of student to grade
     * @param grade Percentage grade of student
     */


    /* ASSIGN GRADE
    * SAMPLE TEST CASES:
    *  X Check if homework name exists
    *  Check if grade is a positive integer (cannot be negative)
    *  Check if student submitted
    *  Check if student did not submit
    *  Check if homework name exists or match
    *  Check if grade is the correct grade or null if N/A
    *  Check if student submitted homework that did not exist
    *  Check if student receives a grade
    *  Check if grade assigned was in previous year
    *  Check if student submitted a different solution to homework set
    *  Check if instructor gives wrong grade to a student
    */




    @Test
    public void testHomework() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 30);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        assertTrue(this.instructor.homeworkExists("ECS_Class", 2017, "Hero App"));
    } //Check whether homework name exists


    @Test
    public void testHomeworkIsReal() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 30);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        assertFalse(this.instructor.homeworkExists("ECS_Class", 2017, "Assignment1"));
    }

    @Test
    public void testStudentSubmit() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        this.student.registerForClass("Adrien", "ECS_Class", 2017);

        //Student did not submit
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        this.instructor.assignGrade("ProfInstructor", "ECS_Class", 2017, "Hero App", "Adrien", 100);

        assertNull(this.instructor.getGrade("ECS_Class", 2017, "Hero App", "Adrien")); //getter function include null if n/a; cannot do assertFalse/True bc not boolean
    } //Check if student receive grade for no submission

    @Test
    public void testReceiveGrade() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        this.student.registerForClass("Adrien", "ECS_Class", 2017);
        this.student.submitHomework("Adrien", "Hero App", "ProgramAnswer", "ECS_Class", 2017);
        this.instructor.assignGrade("ProfInstructor", "ECS_Class", 2017, "Hero App", "Adrien", 95);

        assertNotNull(this.instructor.getGrade("ECS_Class", 2017, "Hero App", "Adrien"));

    } //Check if student receives grade



    @Test
    public void testSubmitNA() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        //There is no homework added to submit
        this.student.registerForClass("Adrien", "ECS_Class", 2017);
        this.student.submitHomework("Adrien", "Hero App", "ProgramAnswer", "ECS_Class", 2017);
        this.instructor.assignGrade("ProfInstructor", "ECS_Class", 2017, "Hero App", "Adrien", 95);

        assertNotNull(this.instructor.getGrade("ECS_Class", 2017, "Hero App", "Adrien"));

    } //Check if student submitted homework that did not exist


    @Test
    public void testSubmitWrong() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        this.student.registerForClass("Adrien", "ECS_Class", 2017);
        this.instructor.assignGrade("ProfInstructor", "ECS_Class", 2017, "Hero App", "Adrien", 95);

        assertNotNull(this.instructor.getGrade("ECS_Class", 2017, "Hero App", "Adrien"));
    } //Check if student submitted no assignment


    @Test
    public void testSubmitWrongHomework() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        this.student.registerForClass("Adrien", "ECS_Class", 2017);

        this.student.submitHomework("Adrien", "Villain App", "ProgramAnswer", "ECS_Class", 2017);
        this.instructor.assignGrade("ProfInstructor", "ECS_Class", 2017, "Hero App", "Adrien", 95);

        assertNotNull(this.instructor.getGrade("ECS_Class", 2017, "Hero App", "Adrien"));
    } //Check if student submitted wrong assignment



    @Test
    public void testGradetoWrongClass() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        this.student.registerForClass("Adrien", "ECS_Class", 2017);

        this.student.submitHomework("Adrien", "Hero App", "ProgramAnswer", "ECS_Class", 2017);
        this.instructor.assignGrade("ProfInstructor", "ECS_Class", 2017, "Hero App", "Adrien", 95);

        assertNotNull(this.instructor.getGrade("PSC_Class", 2017, "Hero App", "Adrien"));
    } //Check if instructor assign grade to wrong class

    @Test
    public void testGradetoWrongStudent() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        this.student.registerForClass("Adrien", "ECS_Class", 2017);

        this.student.submitHomework("Adrien", "Hero App", "ProgramAnswer", "ECS_Class", 2017);
        this.instructor.assignGrade("ProfInstructor", "ECS_Class", 2017, "Hero App", "Adrien", 95);

        assertNotNull(this.instructor.getGrade("ECS_Class", 2017, "Hero App", "Marinette"));
    } //Check if instructor assign grade to wrong student

    @Test
    public void testGradetoWrongYear() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        this.student.registerForClass("Adrien", "ECS_Class", 2017);

        this.student.submitHomework("Adrien", "Hero App", "ProgramAnswer", "ECS_Class", 2017);
        this.instructor.assignGrade("ProfInstructor", "ECS_Class", 2017, "Hero App", "Adrien", 95);

        assertNotNull(this.instructor.getGrade("ECS_Class", 2015, "Hero App", "Marinette"));
    } //Check if instructor assign grade to previous year


    @Test
    public void testAssignStudentGrade() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        this.student.registerForClass("Adrien", "ECS_Class", 2017);
        this.student.submitHomework("Adrien", "Hero App", "ProgramAnswer", "ECS_Class", 2017);
        this.instructor.assignGrade("ProfInstructor", "ECS_Class", 2017, "Hero App", "Adrien", 100);
        assertNull(this.instructor.getGrade("ECS_Class", 2017, "Hero App", "Adrien"));

    } //Check if grade is a positive integer (cannot be negative)


    @Test
    public void testAssignStudentNegGrade() {
        this.admin.createClass("ECS_Class", 2017, "ProfInstructor", 25);
        this.instructor.addHomework("ProfInstructor", "ECS_Class", 2017, "Hero App", "App to track villains");
        this.student.registerForClass("Adrien", "ECS_Class", 2017);
        this.student.submitHomework("Adrien", "Hero App", "ProgramAnswer", "ECS_Class", 2017);
        this.instructor.assignGrade("ProfInstructor", "ECS_Class", 2017, "Hero App", "Adrien", -50);

        assertFalse((this.instructor.getGrade("ECS_Class", 2017, "Hero App", "Adrien") >= 0));
    } //Check if grade is a negative integer



}