import java.util.ArrayList;

public class Maze {
   private char[][] data;

   /**
    * Assigns maze to our private data member
    * @param data - desired maze
    */
   public Maze(char[][] data) {
      this.data = data;
   }

   /**
    * Converts given ArrayList to 2D character array.
    * Each individual string corresponds to a row.
    * @param data - desired maze
    */
   public Maze(ArrayList<String> data) {
      // Initialize data to proper size
      this.data = new char[data.size()][data.get(0).length()];
      // Turn each string into char array and add it to its respective position
      for (int i = 0; i < data.size(); i++) {
         this.data[i] = data.get(i).toCharArray();
      }
   }

   /**
    * Returns true if the cell corresponding to the given MazePosition holds
    * the empty space ' ' character. Also returns false if position is out of bounds.
    * @param p - desired position
    * @return - whether p holds ' ' character
    */
   public boolean isClear(MazePosition p) {
      if (p.getRow() >= this.data.length || p.getCol() >= this.data[0].length || p.getRow() < 0 || p.getCol() < 0) {
         return false;
      }
      return this.data[p.getRow()][p.getCol()] == ' ';
   }

   /**
    * Returns true if the cell corresponding to the given MazePosition holds
    * the 'G' character. This character represents our goal. Also returns false
    * if position is out of bounds
    * @param p - desired position
    * @return whether p holds 'G' character
    */
   public boolean isGoal(MazePosition p) {
      if (p.getRow() >= this.data.length || p.getCol() >= this.data[0].length || p.getRow() < 0 || p.getCol() < 0) {
         return false;
      }
      return this.data[p.getRow()][p.getCol()] == 'G';
   }
}
