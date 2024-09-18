//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 MazeSolver - MazeTestsImmediate
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

import java.util.EmptyStackException;

public class MazeTestsImmediate {

    public static boolean testMazeStack() {
        try {
            Position p1 = new Position(2, 7);
            Position p2 = new Position(5, 4);
            if (p2.equals(p1)) {
                return false;
            }
            MazeStack stack = new MazeStack();
            if (!stack.isEmpty()) {
                return false; // stack should be empty
            }

            try {
                stack.peek(); // EmptyStackException
                return false;
            } catch (EmptyStackException e) {
                // expected behavior, do nothing
            }

            try {
                stack.pop(); // EmptyStackException
                return false;
            } catch (EmptyStackException e) {
                // expected behavior, do nothing
            }

            stack.push(p1);
            if (stack.isEmpty()) {
                return false; // stack should be not empty
            }

            stack.push(p2);
            if (!stack.peek().equals(new Position(5, 4))) {
                return false;
            }

            if (!stack.pop().equals(new Position(5, 4))) {
                return false;
            }
            if (stack.contains(new Position(5, 4))) {
                return false; // should be gone
            }

            if (!stack.contains(new Position(2, 7))) {
                return false; // never was there
            }
            stack.push(new Position(9, 0));
            if (!stack.toString().equals("[9,0] --> [2,7]")) {
                return false;
            }
        } catch (Exception e) {
            return false; // there should be no unexpected Exceptions
        }
        return true;
    }

    public static boolean testMazeExceptions() {
        char[][] mazeArray = {{'L', '_', '.', '|'}, {'.', '.', '.', '.'}};
        MazeSolver m1 = new MazeSolver(mazeArray);
        try {
            m1.setStart(-1, 0); // should throw IllegalArgumentException
            return false;
        } catch (IllegalArgumentException e) {
            // correct behavior, do nothing
        }

        try {
            m1.setStart(0, 6); // should throw IllegalArgumentException
            return false;
        } catch (IllegalArgumentException e) {
            // correct behavior, do nothing
        }

        try {
            m1.setFinish(-1, 3); // should throw IllegalArgumentException
            return false;
        } catch (IllegalArgumentException e) {
            // correct behavior, do nothing
        }

        // a Maze must have a start and finish in order to solveMaze
        try {
            m1.setStart(0, 0);
            // no finish set
            m1.solveMaze(); // should throw IllegalStateException
            return false;
        } catch (IllegalStateException e) {
            // correct behavior, do nothing
        }

        return true; // passed all tests

    }

    public static boolean testMaze1() {
        try {
            char[][] mazeArray = {{'L', '_', '.', '|'}, {'.', '.', '.', '.'}};
            MazeSolver m1 = new MazeSolver(mazeArray);
            m1.setStart(0, 0);
            m1.setFinish(0, 3);
            m1.displayMaze();
            System.out.println();
            m1.solveMaze();
            m1.displayMaze();
            String actual = m1.getPath();
            String expected = "[0,0] --> [0,1] --> [0,2] --> [1,2] --> [1,3] --> [0,3]";
            if (!actual.equals(expected)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false; // there should be no Exceptions
        }
    }

    // issue with the backtracking (it gets stuck at [1,1] and the direction keeps changing between 0 and 1)
    public static boolean testMaze2() {
        try {
            char[][] mazeArray =
                    {{'.', '_', '_', '_', '.'}, {'.', 'L', '_', '_', '.'}, {'.', '.', '.', '.', '.'}};
            MazeSolver m2 = new MazeSolver(mazeArray);
            m2.setStart(1, 0);
            m2.setFinish(0, 2);
            m2.displayMaze();
            System.out.println();
            m2.solveMaze();
            m2.displayMaze();
            String actual = m2.getPath();
            String expected =
                    "[1,0] --> [2,0] --> [2,1] --> [2,2] --> [2,3] --> [2,4] --> [1,4] --> [0,4] --> [0,3] --> [0,2]";
            if (!actual.equals(expected)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false; // there should be no Exceptions
        }
    }

    public static void testMaze33() {
        char[][] mazeArray = {{'.', '_', '.', '|', '.'}, {'L', '|', '.', '|', '|'},
                {'.', '.', '_', '_', '|'}, {'.', '.', '|', '.', '.'}};
        MazeSolver m3 = new MazeSolver(mazeArray);
        m3.setStart(1, 0);
        m3.setFinish(3, 2);
        m3.displayMaze();
        System.out.println();

        m3.solveMaze();
        m3.displayMaze();
    }

    public static void testMaze44() {
        char[][] mazeArray = {{'.', '_', '_', '_', '_', '_', '_', '.'},
                {'.', 'L', '_', '_', '_', '_', '_', '|'}, {'.', '.', '.', '.', '.', '.', '.', '.'}};
        MazeSolver m4 = new MazeSolver(mazeArray);
        m4.setStart(1, 0);
        m4.setFinish(0, 2);
        m4.displayMaze();
        m4.solveMaze();
        m4.displayMaze();
    }

    // this maze has no solution because S is blocked off
    public static boolean testMaze3() {
        char[][] mazeArray = {{'L', '|', '.'}, {'.', '.', '.'}};
        MazeSolver m5 = new MazeSolver(mazeArray);
        m5.setStart(0, 0);
        m5.setFinish(0, 2);
        m5.displayMaze();
        m5.solveMaze();
        m5.displayMaze();
        // should be no solution, isSolved should return false
        return !m5.isSolved(); //
    }

    // this maze has no solution because F is blocked off, error occurs since F is able to move.
    public static boolean testMaze4() {
        char[][] mazeArray = {{'.', '.', 'L'}, {'.', '.', '.'}};
        MazeSolver m6 = new MazeSolver(mazeArray);
        m6.setStart(0, 0);
        m6.setFinish(0, 2);
        m6.displayMaze();
        m6.solveMaze();
        m6.displayMaze();
        // should be no solution, isSolved should return false
        return !m6.isSolved(); //
    }

    public static void main(String[] args) {
        // uncomment each line below, one at a time, to test
        System.out.println("test MazeStack: " + testMazeStack());
        System.out.println("test MazeExceptions: " + testMazeExceptions());
        System.out.println("test Maze1: " + testMaze1());
        System.out.println("test Maze2: " + testMaze2());
        System.out.println("test Maze3: " + testMaze3());
        System.out.println("test Maze4: " + testMaze4());
        testMaze44();
        testMaze33();

    }

}// class

