//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 MazeSolver - MazeSolver
// Course:   CS 300 Summer 2024
//
// Author:   Winston Chan
// Email:    wchan35@wisc.edu
// Lecturer: (Andy Kuemmel)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         TA Diana, TA Kate
// Online Sources:  ZyBooks Ch.9, 10, 11
//
///////////////////////////////////////////////////////////////////////////////

/**
 * A class that solves the maze, and displays the maze grid and solved maze grid. Also
 * Prints the pathing.
 */
public class MazeSolver {
    private static final int UP = 0; // facing up in the grid
    private static final int RIGHT = 1; // facing right in the grid
    private static final int DOWN = 2; // facing down in the grid
    private static final int LEFT = 3; // facing left in the grid
    private MazeStack path; // stack to keep track of the path taken
    private Boolean solved;  // true if solved, false if no solution, null if not attempted yet
    private char[][] mazeInfo; // 2D array representing the maze
    private Position start; // starting position in the maze
    private Position finish; // finishing position in the maze
    private int direction; // current direction the solver is facing (UP, RIGHT, DOWN, LEFT)

    /**
     * Constructor to initialize the maze solver with the maze information
     *
     * @param mazeInfo
     */
    public MazeSolver(char[][] mazeInfo) {
        this.mazeInfo = mazeInfo;
        this.setStart(0, 0); // default start position
        this.path = new MazeStack(); // initialize the path stack
        this.solved = null; // mark as not attempted yet
        this.direction = RIGHT; // default direction
    }

    /**
     * Method to set the start position
     *
     * @param row
     * @param col
     * @throws IllegalArgumentException
     */
    public void setStart(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row >= mazeInfo.length || col < 0 || col >= mazeInfo[0].length) {
            throw new IllegalArgumentException("Invalid start position");
        }
        this.start = new Position(row, col);
    }

    /**
     * Method to set the finish position
     *
     * @param row
     * @param col
     * @throws IllegalArgumentException
     */
    public void setFinish(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row >= mazeInfo.length || col < 0 || col >= mazeInfo[0].length) {
            throw new IllegalArgumentException("Invalid finish position");
        }
        this.finish = new Position(row, col);
    }

    /**
     * Helper method to display the top or bottom border of the maze
     *
     * @param mazeArray
     */
    private static void displayTopBottomHelper(char[] mazeArray) {
        for (int i = 0; i < mazeArray.length; i++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

    /**
     * Method to display the body of the maze, row by row
     *
     * @param mazeArray
     * @param currentRow
     */
    private void displayBody(char[] mazeArray, int currentRow) {
        for (int j = 0; j < mazeArray.length; j++) {
            if (mazeArray[j] == 'L' || j == 0 || mazeArray[j] == '|') {
                if (start != null && currentRow == start.getRow() && j == start.getCol()) {
                    System.out.print("| S ");
                } else if (finish != null && currentRow == finish.getRow() && j == finish.getCol()) {
                    System.out.print("| F ");
                } else if (path != null && path.contains(new Position(currentRow, j))) {
                    System.out.print("| * ");
                } else {
                    System.out.print("|   ");
                }
            } else {
                if (start != null && currentRow == start.getRow() && j == start.getCol()) {
                    System.out.print("  S ");
                } else if (finish != null && currentRow == finish.getRow() && j == finish.getCol()) {
                    System.out.print("  F ");
                } else if (path != null && path.contains(new Position(currentRow, j))) {
                    System.out.print("  * ");
                } else {
                    System.out.print("    ");
                }
            }
        }
        System.out.println("|");
    }

    /**
     * Helper method to display the floor between rows in the maze
     *
     * @param mazeArray
     */
    private static void displayFloor(char[] mazeArray) {
        for (int i = 0; i < mazeArray.length; i++) {
            if (mazeArray[i] == 'L' || mazeArray[i] == '_') {
                System.out.print("+---");
            } else {
                System.out.print("+   ");
            }
        }
        System.out.println("+");
    }

    /**
     * Method to display the entire maze
     */
    public void displayMaze() {
        displayTopBottomHelper(this.mazeInfo[0]); // display the top border

        for (int i = 0; i < mazeInfo.length; i++) {
            displayBody(mazeInfo[i], i); // display each row
            if (i != mazeInfo.length - 1) {
                displayFloor(mazeInfo[i]); // display the floor between rows
            }
        }
        displayTopBottomHelper(this.mazeInfo[0]); // display the bottom border
    }

    /**
     * Method to solve the maze
     *
     * @throws IllegalStateException
     */
    public void solveMaze() throws IllegalStateException {
        if (this.start == null || this.finish == null) {
            throw new IllegalStateException("Neither start nor finish can be null");
        }

        int stepsTaken = 0;
        // set a maximum step limit
        int maxSteps = this.mazeInfo.length * this.mazeInfo[0].length * 4;
        // start from finish position
        Position pathPos = new Position(this.finish.getRow(), this.finish.getCol());
        // add the finish position to the path
        this.path.push(new Position(finish.getRow(), finish.getCol()));

        while (!pathPos.equals(this.start) && stepsTaken < maxSteps) {
            this.solved = pathPos.equals(this.start); // check if the current position is the start

            // try moving in different directions and update the path and direction accordingly
            if (isValidDirection(((this.direction + 1) % 4), pathPos)) {
                this.direction = (this.direction + 1) % 4;
                stepsTaken++;
            } else if (isValidDirection(this.direction, pathPos)) {
                this.direction = this.direction % 4;
                stepsTaken++;
            } else if (isValidDirection(((this.direction + 3) % 4), pathPos)) {
                this.direction = (this.direction + 3) % 4;
                stepsTaken++;
            } else if (isValidDirection(((this.direction + 2) % 4), pathPos)) {
                this.direction = (this.direction + 2) % 4;
                stepsTaken++;
            } else {
                break;
            }
        }

        if (pathPos.equals(this.start)) {
            this.solved = true;
        } else {
            this.solved = false;
        }
    }

    /**
     * Method to check if the moving in the given direction is valid
     *
     * @param direction
     * @param pathPos
     * @return
     */
    private boolean isValidDirection(int direction, Position pathPos) {
        switch (direction) {
            case DOWN:
                if (isWithinBounds((pathPos.getRow() + 1), pathPos.getCol())) {
                    if (this.mazeInfo[pathPos.getRow()][pathPos.getCol()] != 'L'
                            && this.mazeInfo[pathPos.getRow()][pathPos.getCol()] != '_') {
                        pathPos.setRow(pathPos.getRow() + 1);
                        if (this.path.contains(pathPos)) {
                            backtracking(pathPos);
                        }
                        // add new position to the path
                        this.path.push(new Position(pathPos.getRow(), pathPos.getCol()));
                        return true;
                    }
                }
                return false;
            case RIGHT:
                if (isWithinBounds(pathPos.getRow(), pathPos.getCol() + 1)) {
                    if (this.mazeInfo[pathPos.getRow()][pathPos.getCol() + 1] != 'L'
                            && this.mazeInfo[pathPos.getRow()][pathPos.getCol() + 1] != '|') {
                        pathPos.setCol(pathPos.getCol() + 1);
                        if (this.path.contains(pathPos)) {
                            backtracking(pathPos);
                        }
                        this.path.push(new Position(pathPos.getRow(), pathPos.getCol()));
                        return true;
                    }
                }
                return false;
            case UP:
                if (isWithinBounds((pathPos.getRow() - 1), pathPos.getCol())) {
                    if (this.mazeInfo[pathPos.getRow() - 1][pathPos.getCol()] != 'L'
                            && this.mazeInfo[pathPos.getRow() - 1][pathPos.getCol()] != '_') {
                        pathPos.setRow(pathPos.getRow() - 1);
                        if (this.path.contains(pathPos)) {
                            backtracking(pathPos);
                        }
                        this.path.push(new Position(pathPos.getRow(), pathPos.getCol()));
                        return true;
                    }
                }
                return false;
            case LEFT:
                if (isWithinBounds(pathPos.getRow(), pathPos.getCol() - 1)) {
                    if (this.mazeInfo[pathPos.getRow()][pathPos.getCol()] != 'L'
                            && this.mazeInfo[pathPos.getRow()][pathPos.getCol()] != '|') {
                        pathPos.setCol(pathPos.getCol() - 1);
                        if (this.path.contains(pathPos)) {
                            backtracking(pathPos);
                        }
                        this.path.push(new Position(pathPos.getRow(), pathPos.getCol()));
                        return true;
                    }
                }
                return false;
        }
        return false;
    }

    /**
     * Method to backtrack in the path
     *
     * @param pathPos
     */
    private void backtracking(Position pathPos) {
        while (this.path.contains(pathPos)) {
            this.path.pop();
        }
    }

    // Method to check if a position is within the maze bounds

    /**
     * Method to check if a position is within the maze bounds
     *
     * @param row
     * @param col
     * @return
     */
    private boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < this.mazeInfo.length && col >= 0 && col < this.mazeInfo[0].length;
    }

    /**
     * Method to get the path as a string
     *
     * @return
     */
    public String getPath() {
        if (path == null) {
            return "No solution found.";
        }
        return path.toString();
    }

    /**
     * Method to check if the maze is solved
     *
     * @return
     */
    public boolean isSolved() {
        return this.solved;
    }
}
