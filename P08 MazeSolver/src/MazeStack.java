//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 MazeSolver - MazeStack
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

/**
 * A class that helps create the maze stack.
 */
public class MazeStack implements StackADT<Position> {
    private StackNode<Position> head; // top of the stack


    /**
     * constructor to initialize an empty stack
     */
    public MazeStack() {
        this.head = null;
    }

    /**
     * Push a new item onto the stack
     *
     * @param item
     */
    @Override
    public void push(Position item) {
        if (item == null) {
            throw new IllegalArgumentException("Can't push null item onto stack");
        }
        head = new StackNode<>(item, head);
    }

    /**
     * Pop an item off the stack
     *
     * @return
     */
    @Override
    public Position pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Position item = head.getData();
        head = head.getNext();
        return item;
    }

    /**
     * Peek at the top item of the stack without removing it
     *
     * @return
     */
    @Override
    public Position peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return head.getData();
    }

    /**
     * Check if the stack is empty
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Check if a specific position is contained in the stack
     *
     * @param p of type Position
     * @return true if p is contained in this stack, false otherwise
     */
    public boolean contains(Position p) {
        StackNode<Position> current = head;
        while (current != null) {
            if (current.getData().equals(p)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Overrides the toString method of the Object class
     * Calls the toString method of the Position class
     * Follows the guidelines in sample output to match the
     * correct spacing and characters. The String must match this example:
     * “[0,0] --> [0,1] --> [0,2] --> [1,2] --> [1,3] --> [0,3]”
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        StackNode<Position> current = head;
        while (current != null) {
            sb.append(current.getData().toString());
            if (current.getNext() != null) {
                sb.append(" --> ");
            }
            current = current.getNext();
        }
        return sb.toString();
    }
}
