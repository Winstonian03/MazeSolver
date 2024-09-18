/**
 * No file header needed.
 * This class represents an Immutable Node in a stack.
 * Immutable means it does not allow a node to change its data or next
 * Do not make any changes to this class.
 *
 * @param <T>
 * @author Andrew Kuemmel
 */
public class StackNode<T> {
    private T data;
    private StackNode<T> next;

    public StackNode(T data, StackNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public StackNode<T> getNext() {
        return next;
    }
}
