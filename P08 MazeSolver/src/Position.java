//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 MazeSolver - Position
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
 * A class that represents a single row and column in a grid, where rows and columns start at 0.
 * Provides methods to get and set the row and column, and to check equality of positions.
 * Overrides the toString method to provide a string representation of the position.
 * <p>
 * Author: andrewkuemmel
 */
public class Position {
    private int row;  // the first coordinate, representing the row
    private int col;  // the second coordinate, representing the column

    /**
     * Constructor to initialize the row and colum of the position
     *
     * @param row
     * @param col
     */
    Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Getter method for the column
     *
     * @return
     */
    public int getCol() {
        return col;
    }

    /**
     * Setter method for the column
     *
     * @param col
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Getter method for the row
     *
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * Setter method for the row
     *
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Override the equals method to compare two Position objects
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { // check if both references point to the same object
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) { // check if the other object is null or of a different class
            return false;
        }
        Position position = (Position) obj; // typecast the other object to Position
        return row == position.row && col == position.col; // compare the row and column values
    }

    /**
     * Override the toString method to provide a string representation of the position
     *
     * @return
     */
    @Override
    public String toString() {
        return "[" + row + "," + col + "]";
    }
}
