/**
 * On Mac/Linux:
 *  javac -cp .:junit-cs211.jar *.java         # compile everything
 *  java -cp .:junit-cs211.jar E6tester        # run tests
 *
 * On windows replace colons with semicolons: (: with ;)
 *  javac -cp .;junit-cs211.jar *.java         # compile everything
 *  java -cp .;junit-cs211.jar E6tester        # run tests
 */
import static org.junit.Assert.*;

import java.util.Arrays;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class E6Tester {
   public static void main(String args[]){
      org.junit.runner.JUnitCore.main("E6Tester");
   }
   private final double ERR = 0.001;

   @Test(timeout=1000) public void test_course0() throws Exception
   {
      Course c = new Course("CS 211", 4);
      assertEquals("Incorrect course code", "CS 211", c.getCode());
      assertEquals("Incorrect course credits", 4, c.getCredits());
      assertEquals("Incorrect course representation", "GMU CS 211 | 4 credits", c.toString());
   }

   @Test(timeout=1000) public void test_studentexception_inherits() {
      Object o = new StudentException();
      assertTrue("StudentException should be a RuntimeException", o instanceof RuntimeException);
   }
   @Test(timeout=1000) public void test_studentexception_def() {
      StudentException e = new StudentException();
      assertNull("Incorrect default error message", e.getMessage());
   }
   @Test(timeout=1000) public void test_studentexception_msg() {
      StudentException e = new StudentException("incorrect year");
      assertEquals("Incorrect error message", "incorrect year", e.getMessage());
   }

   @Test(timeout=1000) public void test_universityexception_inherits() {
      Object o = new UniversityException();
      assertTrue("UniversityException should be an Exception", o instanceof Exception);
      assertFalse("UniversityException should not be a RuntimeException", o instanceof RuntimeException);
   }
   @Test(timeout=1000) public void test_universityexception_def() {
      UniversityException e = new UniversityException();
      assertNull("Incorrect default error message", e.getMessage());
   }
   @Test(timeout=1000) public void test_universityexception_msg() {
      UniversityException e = new UniversityException("enrollment error");
      assertEquals("Incorrect error message", "enrollment error", e.getMessage());
   }

   private void check_student_init(String name, String year)
   {
      Student s;
      if (year == null) {s = new Student(name); year = "freshman";}
      else {s = new Student(name, year);}
      assertEquals("Incorrect student name", name, s.getName());
      assertEquals("Incorrect student year", year, s.getYear());
   }

   @Test(timeout=1000) public void test_student_init0() { check_student_init("George Mason",null); }
   @Test(timeout=1000) public void test_student_init1() { check_student_init("!#@##$%","freshman"); }
   @Test(timeout=1000) public void test_student_init2() { check_student_init("Donald Duck","sophomore"); }
   @Test(timeout=1000) public void test_student_init3() { check_student_init("Mickey Mouse","junior"); }
   @Test(timeout=1000) public void test_student_init4() { check_student_init("Catwoman","senior"); }

   @Test(timeout=1000) public void test_student_addcourse() throws Exception
   {
      Student s = new Student("Superman", "senior");
      Course c1 = new Course("CS 211", 4);
      Course c2 = new Course("CS 310", 3);
      Registrar.setMinimumGrade(c1,2.0f);
      Registrar.setMinimumGrade(c2,2.8f);
      assertTrue("Cannot add course CS 211 for Superman", s.addCourse(c1, 2.0f));
      assertTrue("Cannot add course CS 310 for Superman", s.addCourse(c2, 2.9f));
   }

   @Test(timeout=1000) public void test_student_graduation() throws Exception
   {
      Student s = new Student("Batman", "junior");
      Course c1 = new Course("CS 112", 4);
      Course c2 = new Course("CS 211", 4);
      Course c3 = new Course("CS 310", 3);
      Course c4 = new Course("CS 321", 3);
      Course c5 = new Course("CS 367", 3);
      Registrar.setMinimumGrade(c1,2.0f);
      Registrar.setMinimumGrade(c2,2.0f);
      Registrar.setMinimumGrade(c3,2.0f);
      Registrar.setMinimumGrade(c4,2.0f);
      Registrar.setMinimumGrade(c5,2.0f);
      s.addCourse(c1,2.8f);
      s.addCourse(c2,3.3f);
      s.addCourse(c3,2.95f);
      s.addCourse(c4,3.21f);
      s.addCourse(c5,3.34f);
      assertEquals("Incorrect GPA calculation", 3.12f, s.graduation(), ERR);
   }

   @Test(timeout=1000) public void test_registrar_mingpa() {
      assertEquals("Incorrect minGPA value", 3.0, Registrar.minGPA, ERR);
   }

   @Test(timeout=1000) public void test_registrar_mincredits() {
      assertEquals("Incorrect minCredits value", 10, Registrar.minCredits);
   }

   @Test(timeout=1000) public void test_registrar_getminimumgrade() throws Exception {
      Course c = new Course("ECE 999", 3);
      Registrar.setMinimumGrade(c,2.76f);
      assertEquals("Incorrect minimum grade for ECE 999", 2.76f, Registrar.getMinimumGrade(c), ERR);
   }

   @Rule public ExpectedException uException = ExpectedException.none();

   @Test(timeout=1000) public void test_course_init_bad0() throws Exception
   {
      uException.expect(UniversityException.class);
      uException.expectMessage("Invalid number of credits for SWE 510");
      uException.reportMissingExceptionWithMessage("missing exception in course construction");
      new Course("SWE 510", 0);
   }


   @Rule public ExpectedException sException = ExpectedException.none();

   @Test(timeout=1000) public void test_student_init_bad0() {
      sException.expect(StudentException.class);
      sException.expectMessage("Student name is invalid");
      new Student("");
   }

   @Test(timeout=1000) public void test_student_init_bad1() {
      sException.expect(StudentException.class);
      sException.expectMessage("Student name is invalid");
      new Student(null,null);
   }

   @Test(timeout=1000) public void test_student_init_bad2() {
      sException.expect(StudentException.class);
      sException.expectMessage("Student must be one of freshman|sophomore|junior|senior");
      new Student("test", "Freshman");
   }

   @Test(timeout=1000) public void test_student_addcourse_bad0() throws Exception {
      sException.expect(StudentException.class);
      sException.expectMessage("George Washington has already taken CS 211");
      Student s = new Student("George Washington", "sophomore");
      Course c1 = new Course("CS 211", 4);
      Registrar.setMinimumGrade(c1,3.0f);
      s.addCourse(c1, 3.1f);
      s.addCourse(c1, 3.5f);
   }

   @Test(timeout=1000) public void test_student_addcourse_bad1() throws Exception {
      uException.expect(UniversityException.class);
      uException.expectMessage("CS 211 requires a grade greater than or equal to 3.0");
      uException.reportMissingExceptionWithMessage("missing exception in addCourse method");
      Student s = new Student("George Mason", "sophomore");
      Course c1 = new Course("CS 211", 4);
      Registrar.setMinimumGrade(c1,3.0f);
      s.addCourse(c1, 2.99f);
   }

   @Test(timeout=1000) public void test_student_graduation_bad0() throws Exception {
      sException.expect(StudentException.class);
      sException.expectMessage("Dark Night hasn't taken any courses yet");
      Student s = new Student("Dark Night");
      s.graduation();
   }

   @Test(timeout=1000) public void test_student_graduation_bad1() throws Exception {
      uException.expect(UniversityException.class);
      uException.expectMessage("Donald\'s GPA is lower than the minimum required");
      uException.reportMissingExceptionWithMessage("missing exception in graduation method");
      Student s = new Student("Donald", "senior");
      Course c1 = new Course("CS 112", 4);
      Course c2 = new Course("CS 211", 4);
      Course c3 = new Course("CS 310", 3);
      Course c4 = new Course("CS 321", 3);
      Course c5 = new Course("CS 367", 3);
      Registrar.setMinimumGrade(c1,2.0f);
      Registrar.setMinimumGrade(c2,2.0f);
      Registrar.setMinimumGrade(c3,2.0f);
      Registrar.setMinimumGrade(c4,2.0f);
      Registrar.setMinimumGrade(c5,2.0f);
      s.addCourse(c1,2.8f);
      s.addCourse(c2,3.3f);
      s.addCourse(c3,2.85f);
      s.addCourse(c4,3.71f);
      s.addCourse(c5,2.28f);
      s.graduation();
   }

   @Test(timeout=1000) public void test_student_graduation_bad2() throws Exception {
      uException.expect(UniversityException.class);
      uException.expectMessage("Bill Gates doesn't have enough credits to graduate");
      uException.reportMissingExceptionWithMessage("missing exception in graduation method");
      Student s = new Student("Bill Gates", "junior");
      Course c1 = new Course("CS 211", 4);
      Course c2 = new Course("SWE 510", 5);
      Registrar.setMinimumGrade(c1,3.0f);
      Registrar.setMinimumGrade(c2,3.0f);
      s.addCourse(c1,3.1f);
      s.addCourse(c2,3.0f);
      s.graduation();
   }

   @Rule public ExpectedException eException = ExpectedException.none();

   @Test(timeout=1000) public void test_registrar_getminimumgrade_bad() throws Exception {
      eException.expect(Exception.class);
      eException.expectMessage("PSY 214 was not found in the registry");
      Course c = new Course("PSY 214",4);
      Registrar.getMinimumGrade(c);
   }

   private final String expectedOut = ""
           + "Congrats to Doctor Strange for graduating with GPA 3.2\n"
           + "Black Widow hasn\'t taken any courses yet\n"
           + "Congrats to Spider Man for graduating with GPA 3.8\n"
           ;
   private final String expectedErr = ""
           + "Sorry,Iron Man\'s GPA is lower than the minimum required\n"
           + "Sorry,Captain America doesn\'t have enough credits to graduate\n"
           ;
   @Test(timeout=1000) public void test_registrar_class2020() throws Exception
   {
      Student[] roster = new Student[5];
      roster[0] = new Student("Doctor Strange");
      roster[1] = new Student("Black Widow");
      roster[2] = new Student("Spider Man");
      roster[3] = new Student("Iron Man");
      roster[4] = new Student("Captain America");
      Course c0 = new Course("CS 100", 3);
      Course c1 = new Course("CS 112", 4);
      Course c2 = new Course("CS 211", 4);
      Course c3 = new Course("CS 310", 3);
      Course c4 = new Course("CS 321", 3);
      Course c5 = new Course("CS 367", 3);
      Course c6 = new Course("CS 455", 4);
      Course c7 = new Course("CS 478", 4);
      Course c8 = new Course("CS 483", 3);
      Course c9 = new Course("CS 499", 3);
      Registrar.setMinimumGrade(c0,2.0f);
      Registrar.setMinimumGrade(c1,2.0f);
      Registrar.setMinimumGrade(c2,2.0f);
      Registrar.setMinimumGrade(c3,2.0f);
      Registrar.setMinimumGrade(c4,2.0f);
      Registrar.setMinimumGrade(c5,2.0f);
      Registrar.setMinimumGrade(c6,2.0f);
      Registrar.setMinimumGrade(c7,2.0f);
      Registrar.setMinimumGrade(c8,2.0f);
      Registrar.setMinimumGrade(c9,2.0f);
      roster[0].addCourse(c1, 3.1f);
      roster[0].addCourse(c2, 3.2f);
      roster[0].addCourse(c3, 3.3f);
      roster[2].addCourse(c4, 4.0f);
      roster[2].addCourse(c5, 3.4f);
      roster[2].addCourse(c6, 4.0f);
      roster[3].addCourse(c7, 2.1f);
      roster[3].addCourse(c8, 3.2f);
      roster[4].addCourse(c9, 3.5f);
      roster[4].addCourse(c0, 3.7f);
      setCapture();
      String errMsgStdout = "invalid standard output";
      String errMsgStderr = "invalid error output";
      Registrar.class2020(roster);
      String actualOut = getCapture();
      String actualErr = getCaptureErr();
      assertEquals(errMsgStdout, expectedOut, actualOut);
      assertEquals(errMsgStderr, expectedErr, actualErr);
      unsetCapture();
   }

   /** the lines below are for setting up input/output redirection so that the
    * tests can see what is being set to the screen as well as produce its own
    * pseudo-keyboard input.  No test appear below here. */

   static ByteArrayOutputStream localOut, localErr;
   static ByteArrayInputStream localIn;
   static PrintStream sOut, sErr;
   static InputStream sIn;

   @BeforeClass public static void setup() throws Exception {
      sOut = System.out;
      sErr = System.err;
      sIn  = System.in;
   }

   @AfterClass public static void cleanup() throws Exception {
      unsetCapture();
      unsetInput();
   }

   private static void setCapture() {
      localOut = new ByteArrayOutputStream();
      localErr = new ByteArrayOutputStream();
      System.setOut(new PrintStream( localOut ) );
      System.setErr(new PrintStream( localErr ) );
   }

   private static String getCapture() {
      return localOut.toString().replaceAll("\\r?\\n", "\n");
   }

   private static String getCaptureErr() {
      return localErr.toString().replaceAll("\\r?\\n", "\n");
   }

   private static void unsetCapture() {
      System.setOut( null );
      System.setOut( sOut );
      System.setErr( null );
      System.setErr( sErr );
   }

   private static void setInput(String input) {
      localIn = new ByteArrayInputStream(input.getBytes());
      System.setIn(localIn);
   }

   private static void unsetInput() throws IOException {
      if (localIn != null) localIn.close();
      System.setIn( null );
      System.setIn( sIn  );
   }


}