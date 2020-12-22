public class MazePosition {
   private int row;
   private int col;

   public MazePosition(int row, int col) {
      this.row = row;
      this.col = col;
   }

   /**
    * Returns a new MazePosition with the same row, and column -1
    * @return - MazePosition with the same row, and column -1
    */
   public MazePosition left() {
      return new MazePosition(row, col - 1);
   }

   /**
    * Returns a new MazePosition with the same row and column +1
    * @return - MazePosition with the same row, and column + 1
    */
   public MazePosition right() {
      return new MazePosition(row, col + 1);
   }

   /**
    * Returns a new mazePosition with the same column, and row - 1
    * @return - MazePosition with the same column, and row - 1
    */
   public MazePosition up() {
      return new MazePosition(row - 1, col);
   }

   /**
    * Returns a new MazePosition with the same column, and row + 1
    * @return - MazePosition with the same column, and row + 1
    */
   public MazePosition down() {
      return new MazePosition(row + 1, col);
   }

   /**
    * Checks equality
    * @param o - object to be compared
    * @return - whether object is equal or not
    */
   @Override
   public boolean equals(Object o) {
      if (o instanceof MazePosition) {
         MazePosition x = (MazePosition) o;
         return this.row == x.getRow() && this.col == x.getCol();
      }
      return false;
   }

   // *-*-*-* Getter Methods *-*-*-* //
   public int getRow() {
      return this.row;
   }

   public int getCol() {
      return this.col;
   }
}
