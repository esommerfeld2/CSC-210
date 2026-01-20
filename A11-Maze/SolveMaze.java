import java.io.*;
import java.util.Scanner;

class SolveMaze {
  /**
   * Recursive Maze solver
   * @param maze to solve
   * @param current current position
   * @return true/false if maze was solved
   */
  public static Boolean recursiveMazeSolver(Maze maze, MazeLocation current){
    try { Thread.sleep(50);	} catch (InterruptedException e) {};
    int row = current.getRow();
    int col = current.getCol();

   //if current is finish
   int rowFinsih = maze.getFinish().getRow();
   int colFinish = maze.getFinish().getCol();
   if(rowFinsih == row & colFinish == col){
    maze.setContents(row, col, MazeContents.PATH);
    return true;
   }else if(!maze.checkExplorable(row, col)){
    return false;
   }else{
    maze.setContents(row, col, MazeContents.VISITED);

    //Go one row over
    if(maze.checkExplorable(row-1, col)&& maze.getContents(row-1, col) != MazeContents.WALL && maze.getContents(row-1, col) != MazeContents.VISITED){
      MazeLocation temp = new MazeLocation(row-1, col);
      if(recursiveMazeSolver(maze, temp)){
      maze.setContents(row, col, MazeContents.PATH);
      return true;
      }
    }
    
    //Go the other way in the row
    if(maze.checkExplorable(row+1, col) && maze.getContents(row+1, col) != MazeContents.WALL && maze.getContents(row+1, col) != MazeContents.VISITED){
      MazeLocation temp = new MazeLocation(row+1, col);
      if(recursiveMazeSolver(maze, temp)){
        maze.setContents(row, col, MazeContents.PATH);
        return true;
      }
    }

    //Go one way col
    if(maze.checkExplorable(row, col-1) && maze.getContents(row, col-1) != MazeContents.WALL && maze.getContents(row, col-1) != MazeContents.VISITED){
      MazeLocation temp = new MazeLocation(row, col-1);
      if(recursiveMazeSolver(maze, temp)){
        maze.setContents(row, col, MazeContents.PATH);
        return true;
      }
    }

    //Go the other way col
    if(maze.checkExplorable(row, col+1) && maze.getContents(row, col+1) != MazeContents.WALL && maze.getContents(row, col+1) != MazeContents.VISITED){
      MazeLocation temp = new MazeLocation(row, col+1);
      if(recursiveMazeSolver(maze, temp)){
        maze.setContents(row, col, MazeContents.PATH);
        return true;
      }
    }

    //Dead end
    maze.setContents(row, col, MazeContents.DEAD_END);
    return false;

   }

  }

  /**
   * This prints if the maze was solved or not
   * @param maze to solve
   * @param start the start of the maze
   */
  public static void recursiveMazeSolverOutput(Maze maze, MazeLocation start){
    if (recursiveMazeSolver(maze, start)){
      System.out.println("Sucess the maze is solved");
    }else{
      System.out.println("The maze was not solved.");
    }
  }

  /**
   * Method to read maze from a file
   * @param fname filename
   * @return new maze
   */
  public static Maze readMaze(String fname){
    try{
    Scanner file = new Scanner(new File(fname));
    int row = 0;
    int col = 0;
    int startRow = 0;
    int startCol = 0;
    int finishRow = 0;
    int finishCol = 0;
    //Read maze once
    while (file.hasNextLine()) {
      String line =file.nextLine();
      if(col < 1){
        for(int i = 0; i < line.length(); i++){
        char current= line.charAt(i);
        if(current == 'S'){
          startRow = row;
          startCol = col;
        }

        if(current == 'F'){
          finishRow = row;
          finishCol = i;
        }

        col++;
        }
      }else{
        for(int i = 0; i < line.length(); i++){
        char current= line.charAt(i);
        if(current == 'S'){
          startRow = row;
          startCol = i;
        }

        if(current == 'F'){
          finishRow = row;
          finishCol = i;
        }
      }
      }
      row++;
    }
    file.close();
    Maze maze = new Maze(row, col, startRow, startCol, finishRow, finishCol);
    file = new Scanner(new File(fname));
    row = 0;
      while (file.hasNextLine()) {
        //Get the line and break it apart
        String line =file.nextLine();
        char current;
        for(int i = 0; i < line.length(); i++){
          current= line.charAt(i);
          if(current == '#'){
            maze.setContents(row, i, MazeContents.WALL);
          }else if(current == '.' || current == ' ' ){
            maze.setContents(row, i, MazeContents.OPEN);
          }else if(current == 'S' || current == 'F'){
            maze.setContents(row, i, MazeContents.OPEN);
          }else{
            System.err.println("Not a valid symbol.");
          }
          
        }
        row++;
      }
      file.close();
      return maze;

    }catch (FileNotFoundException e) {
      System.out.println("File not found.");
    }
    return null;
  }

  /**
   * Main method for maze reading/solving
   * @param args to pass
   */
  public static void main(String[] args) {
    if(args.length <= 0){
      System.err.println("Please provide the name of the maze file.");
      System.exit(-1);
    }
    Maze maze = readMaze(args[0]);
    
    //Maze maze = new Maze();
    //maze.initDemoMaze();
    MazeViewer viewer = new MazeViewer(maze);
    //ShortestPath.Dijkstra(maze);
    recursiveMazeSolverOutput(maze, maze.getStart());
  }
}
