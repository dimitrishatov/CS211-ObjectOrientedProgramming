import java.util.ArrayList;
import java.util.Arrays;

public class DailyExercise {
   private ArrayList<Fitness> exerciseList;
   private int duration;
   private double calorieTarget;
   private Profile profile;

   public DailyExercise(ArrayList<Fitness> exerciseList, int duration, double calorieTarget, Profile profile) {
      this.exerciseList = exerciseList;
      this.duration = duration;
      this.calorieTarget = calorieTarget;
      this.profile = profile;
   }

   public DailyExercise(ArrayList<Fitness> exerciseList, Profile profile) {
      this.exerciseList = exerciseList;
      this.profile = profile;
      this.duration = 60;
      this.calorieTarget = 500;
   }

   /**
    * Returns array with exercises that fulfill target muscle
    * group requirements
    *
    * @param targetMuscles muscles user wants to target
    * @return array of exercises which target said muscles
    */
   public Fitness[] getExercises(Muscle[] targetMuscles) {
      ArrayList<Fitness> targetMuscleExercises = new ArrayList<>();

      // For each muscle user wants to target, if an exercise in exerciseList
      // works that muscle and isnt already in targetMuscleExercises, add it
      for (Muscle muscle : targetMuscles) {
         for (Fitness exercise : exerciseList) {
            if (Arrays.asList(exercise.muscleTargeted()).contains(muscle) && !targetMuscleExercises.contains(exercise)) {
               targetMuscleExercises.add(exercise);
            }
         }
      }
      return (Fitness[]) targetMuscleExercises.toArray();
   }

   public void addExercise(Fitness ex) {
      exerciseList.add(ex);
   }

   public void removeExercise(Fitness ex) {
      exerciseList.remove(ex);
   }

   public ArrayList<Fitness> getExerciseList() {
      return exerciseList;
   }

   public void setExerciseList(ArrayList<Fitness> list) {
      this.exerciseList = list;
   }

   public int getDuration() {
      return duration;
   }

   public void setDuration(int duration) {
      this.duration = duration;
   }

   public double getCalorieTarget() {
      return calorieTarget;
   }

   public void setCalorieTarget(double target) {
      this.calorieTarget = target;
   }

   public Profile getProfile() {
      return profile;
   }

   public void setProfile(Profile profile) {
      this.profile = profile;
   }
}
