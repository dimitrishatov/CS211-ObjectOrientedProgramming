import java.util.ArrayList;

public class WeeklyExercise {
   private ArrayList<Fitness> exerciseList;
   private int days;
   private double weeklyCalorieTarget;
   private Profile profile;

   public WeeklyExercise(ArrayList<Fitness> exerciseList, int days, double weeklyCalorieTarget, Profile profile) {
      this.exerciseList = exerciseList;
      this.days = days;
      this.weeklyCalorieTarget = weeklyCalorieTarget;
      this.profile = profile;
   }

   public WeeklyExercise(ArrayList<Fitness> exerciseList, Profile profile) {
      this.exerciseList = exerciseList;
      this.profile = profile;
      this.days = 7;
      this.weeklyCalorieTarget = 3500;
   }

   /**
    *  Returns a list of DailyExercises that the user should do in order
    *  to meet the targeted calorie loss. Evenly distributes the calorie loss
    *  over the number of days the user plans to work out. User determines intensity of
    *  exercises. One exercise used per day for days number of exercises in exerciseList
    *
    * @return list of exercises
    */
   public ArrayList<DailyExercise> getWeeklyExercises(Intensity intensity) {
      ArrayList<DailyExercise> weeklyExercises = new ArrayList<DailyExercise>(days);
      double dailyCalorieGoal = weeklyCalorieTarget / days;
      int exerciseCounter = 0;

      // We are going to add one exercise for each day
      for (int i = 0; i < days; i++) {

         // Assigns exercise for the day and assures that counter
         // will continually cycle through exercises
         Fitness currentExercise = exerciseList.get(exerciseCounter++);
         if (exerciseCounter == exerciseList.size()) {
            exerciseCounter = 0;
         }

         // We calculate how many minutes of exercise will be needed
         // to meet the daily calorie goal using LOW intensity.
         int minsNeededToReachGoal = 1;
         while (currentExercise.calorieLoss(intensity, profile.getWeight(), minsNeededToReachGoal) < dailyCalorieGoal) {
            minsNeededToReachGoal++;
         }

         ArrayList<Fitness> exList = new ArrayList<Fitness>();
         exList.add(currentExercise);
         weeklyExercises.add(new DailyExercise(exList, minsNeededToReachGoal - 1, dailyCalorieGoal, profile));
      }
      return weeklyExercises;
   }

   /**
    *  Returns a list of DailyExercises that the user should do in order
    *  to meet the targeted calorie loss. Evenly distributes the calorie loss
    *  over the number of days the user plans to work out. Assumes low intensity
    *  exercises. One exercise used per day for days number of exercises in exerciseList
    *
    * @return list of exercises
    */
   public ArrayList<DailyExercise> getWeeklyExercises() {
      ArrayList<DailyExercise> weeklyExercises = new ArrayList<DailyExercise>(days);
      double dailyCalorieGoal = weeklyCalorieTarget / days;
      int exerciseCounter = 0;

      // We are going to add one exercise for each day
      for (int i = 0; i < days; i++) {

         // Assigns exercise for the day and assures that counter
         // will continually cycle through exercises
         Fitness currentExercise = exerciseList.get(exerciseCounter++);
         if (exerciseCounter == exerciseList.size()) {
            exerciseCounter = 0;
         }

         // We calculate how many minutes of exercise will be needed
         // to meet the daily calorie goal using LOW intensity.
         int minsNeededToReachGoal = 1;
         while (currentExercise.calorieLoss(Intensity.LOW, profile.getWeight(), minsNeededToReachGoal) < dailyCalorieGoal) {
            minsNeededToReachGoal++;
         }

         ArrayList<Fitness> exList = new ArrayList<Fitness>();
         exList.add(currentExercise);
         weeklyExercises.add(new DailyExercise(exList, minsNeededToReachGoal - 1, dailyCalorieGoal, profile));
      }
      return weeklyExercises;
   }

   /**
    * Returns a string that contains a suggestion on how to lose
    * the targeted weight within the specified number of days.
    *
    * @param targetWeight weight user wants to achieve
    * @param withInDays number of days in which user wants to reach targetWeight
    * @return String with suggestion
    * @throws TargetWeightException if targetWeight > user weight
    */
   public String targetedCalorieLoss(double targetWeight, int withInDays) throws TargetWeightException {
      if (targetWeight > profile.getWeight()) {
         throw new TargetWeightException();
      }

      double weightLossNeeded = profile.getWeight() - targetWeight;
      double needToLoseCals = 7000 * weightLossNeeded / withInDays;
      double currentIntake = profile.dailyCalorieIntake();
      double neededIntake = profile.dailyCalorieIntake() - needToLoseCals;

      return String.format("You need to lose %.2f calories per day or decrease your intake from %.2f to %.2f in order to lose %.2f kg of weight",
              needToLoseCals, currentIntake, neededIntake, weightLossNeeded);
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

   public void setExerciseList(ArrayList<Fitness> exerciseList) {
      this.exerciseList = exerciseList;
   }

   public int getDays() {
      return days;
   }

   public void setDays(int days) {
      this.days = days;
   }

   public double getWeeklyCalorieTarget() {
      return weeklyCalorieTarget;
   }

   public void setWeeklyCalorieTarget(double weeklyCalorieTarget) {
      this.weeklyCalorieTarget = weeklyCalorieTarget;
   }

   public Profile getProfile() {
      return profile;
   }

   public void setProfile(Profile profile) {
      this.profile = profile;
   }
}

