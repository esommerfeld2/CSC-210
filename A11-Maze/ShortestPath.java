import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestPath {

/**
 * Method for finding the shortest Path
 * @param maze you want to solve
 * @return the solved maze
 */
    public static Maze Dijkstra(Maze maze){

        //Set up
        int[][] distance = new int[maze.getHeight()][maze.getWidth()];
        int[][] previousRow = new int[maze.getHeight()][maze.getWidth()];
        int[][] previousCol = new int[maze.getHeight()][maze.getWidth()];
        Queue<Integer> rows = new ArrayDeque<>();
        Queue<Integer> cols = new ArrayDeque<>();
        int row;
        int col;

        //create arrays with stuff so it isn't empty
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                distance[i][j] = 1000000;
                previousRow[i][j] = -1;
                previousCol[i][j] = -1;
            }
        }

        //start
        distance[maze.getStart().getRow()][maze.getStart().getCol()] = 0;
        rows.add(maze.getStart().getRow());
        cols.add(maze.getStart().getCol());

        while(!rows.isEmpty()){
            row = rows.remove();
            col = cols.remove();

            if(maze.checkExplorable(row-1, col)){
                int newDistance = distance[row][col] + 1;
                if(newDistance < distance[row-1][col]){
                    distance[row-1][col] = newDistance;
                    previousRow[row-1][col] = row;
                    previousCol[row-1][col] = col;
                    rows.add(row-1);
                    cols.add(col);
                }
            }
            //Other row
            if(maze.checkExplorable(row+1, col)){
                int newDistance = distance[row][col] + 1;
                if(newDistance < distance[row+1][col]){
                    distance[row+1][col] = newDistance;
                    previousRow[row+1][col] = row;
                    previousCol[row+1][col] = col;
                    rows.add(row+1);
                    cols.add(col);
                }
            }

            //cols
            if(maze.checkExplorable(row, col-1)){
                int newDistance = distance[row][col] + 1;
                if(newDistance < distance[row][col-1]){
                    distance[row][col-1] = newDistance;
                    previousRow[row][col-1] = row;
                    previousCol[row][col-1] = col;
                    rows.add(row);
                    cols.add(col-1);
                }
            }

            if(maze.checkExplorable(row, col+1)){
                int newDistance = distance[row][col] + 1;
                if(newDistance < distance[row][col+1]){
                    distance[row][col+1] = newDistance;
                    previousRow[row][col+1] = row;
                    previousCol[row][col+1] = col;
                    rows.add(row);
                    cols.add(col+1);
                }       
            }

        }

        //no end found
        if(distance[maze.getFinish().getRow()][maze.getFinish().getCol()] == 1000000){
            System.out.println("Maze not solveable.");
            return null;
        }

        //Marking path
        row = maze.getFinish().getRow();
        col = maze.getFinish().getCol();
        
        while (!(row == maze.getStart().getRow() && col == maze.getStart().getCol())) {
            maze.setContents(row, col, MazeContents.PATH);
            row = previousRow[row][col];
            col = previousCol[row][col];
        }
        return maze;

    }
    
}
