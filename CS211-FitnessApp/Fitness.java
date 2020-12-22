public interface Fitness {
   /**
    * Returns muscle thats going to be affected by the fitness
    * @return affected muscles
    */
   public Muscle[] muscleTargeted();

   /**
    * Returns total amount of calorie burnt by the exercise for
    * the duration number of minutes for a person with the given weight
    * @param intensity intensity of exercise
    * @param weight weight of user
    * @param duration duration of exercise
    * @return # of calories burnt by exercise
    */
   public double calorieLoss(Intensity intensity, double weight, int duration);

   /**
    * Returns short description of the fitness type
    * @return description of fitness type
    */
   public String description();
}
