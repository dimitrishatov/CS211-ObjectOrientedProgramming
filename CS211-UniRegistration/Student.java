import java.util.ArrayList;

public class Student {
   private String name;
   private String year;
   private ArrayList<CourseGradePair> gradebook;

   public Student(String name) {
      if (!isValidName(name)) {
         throw new StudentException("Student name is invalid");
      }
      this.name = name;
      this.year = "freshman";
      gradebook = new ArrayList<>();
   }

   public Student (String name, String year) {
      if (!isValidName(name)) {
         throw new StudentException("Student name is invalid");
      }
      if (!isValidYear(year)) {
         throw new StudentException("Student must be one of freshman|sophomore|junior|senior");
      }
      this.name = name;
      this.year = year;
      gradebook = new ArrayList<>();

   }

   public boolean addCourse(Course course, float grade) throws StudentException, Exception {
      CourseGradePair newCourse = new CourseGradePair(course,grade);
      // Checks if course has already been taken
      for (CourseGradePair takenCourse : gradebook) {
         if (takenCourse.getCourse().equals(course)) {
            throw new StudentException(name + " has already taken " + course.getCode());
         }
      }
      // Throws UniversityException if the grade is lower than the minimum required for this course
      if (grade < Registrar.getMinimumGrade(course)) {
         throw new UniversityException(
                 course.getCode() + " requires a grade greater than or equal to " + Registrar.getMinimumGrade(course));
      }
      gradebook.add(newCourse);
      return true;
   }

   public float graduation() throws StudentException, UniversityException {
      if (gradebook.size() == 0) {
         throw new StudentException(name + " hasn't taken any courses yet");
      }
      // Sums credit and GPA
      int credits = 0;
      float GPA = 0.0f;
      for (CourseGradePair course : gradebook) {
         GPA += course.getGrade();
         credits += course.getCourse().getCredits();
      }
      GPA = GPA / gradebook.size();

      // Checks against minGPA and minCredits
      if (GPA < Registrar.minGPA) {
         throw new UniversityException(name + "'s GPA is lower than the minimum required");
      }
      if (credits < Registrar.minCredits) {
         throw new UniversityException(name + " doesn't have enough credits to graduate");
      }

      return GPA;
   }

   // Quick verification for name
   private boolean isValidName(String name) {
      if (name == null || name.equals("")) {
         return false;
      }
//      for (int i = 0; i < name.length(); i++) {
//         if (!Character.isLetter(name.charAt(i)) && !Character.isWhitespace(name.charAt(i))) {
//            return false;
//         }
//      }
      return true;
   }

   // Quick verification for year
   private boolean isValidYear(String year) {
      if (year.equals("")) {
         return false;
      }
      return year.equals("freshman") || year.equals("sophomore") || year.equals("junior") || year.equals("senior");
   }

   public String getName() {
      return name;
   }

   public String getYear() {
      return year;
   }
}

class CourseGradePair {
   private Course course;
   private float grade;

   public CourseGradePair(Course course, float grade) {
      this.course = course;
      this.grade = grade;
   }

   public Course getCourse() {
      return course;
   }

   public float getGrade() {
      return grade;
   }
}
