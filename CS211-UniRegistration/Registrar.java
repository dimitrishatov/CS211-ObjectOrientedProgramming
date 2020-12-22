import java.util.ArrayList;

public class Registrar {
   public static float minGPA = 3.0f;
   public static int minCredits = 10;

   private static ArrayList<CourseGradePair> minimumGrades = new ArrayList<>();

   public static void setMinimumGrade(Course course, float grade) {
      minimumGrades.add(new CourseGradePair(course, grade));
   }

   public static float getMinimumGrade(Course course) throws Exception {
      for (CourseGradePair minimumGrade : minimumGrades) {
         if (minimumGrade.getCourse().equals(course)) {
            return minimumGrade.getGrade();
         }
      }
      throw new Exception(course.getCode() + " was not found in the registry.");
   }

   public static void class2020(Student[] students) throws StudentException, UniversityException {
      for (Student student : students) {
         try {
            System.out.println("Congrats to " + student.getName() + " for graduating with GPA " + student.graduation());
         }
         catch (StudentException e) {
            System.out.println(e.getMessage());
         }
         catch (UniversityException e) {
            System.err.println("Sorry," + e.getMessage());
         }
      }
   }

}
