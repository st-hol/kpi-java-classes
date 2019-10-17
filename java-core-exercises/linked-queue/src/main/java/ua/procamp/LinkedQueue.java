package ua.procamp;

/**
 * {@link LinkedQueue} implements FIFO {@link Queue}, using singly linked nodes. Nodes are stores in instances of nested
 * class Node. In order to perform operations {@link LinkedQueue#add(Object)} and {@link LinkedQueue#poll()}
 * in a constant time, it keeps to references to the head and tail of the queue.
 *
 * @param <T> a generic parameter
 */
public class LinkedQueue<T> implements Queue<T> {

    private int size;
    private Node<T> first;
    private Node<T> last;

    public LinkedQueue() {
        size = 0;
        first = null;
        last = null;
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to add
     */
    public void add(T element) {
        Node<T> node = new Node<T>(element);

        if (isEmpty()) {
            first = node;
        } else {
            last.setNextNode(node);
        }
        last = node;
        size++;
    }

    /**
     * Retrieves and removes queue head.
     *
     * @return an element that was retrieved from the head or null if queue is empty
     */
    public T poll() {
        if (isEmpty()) {
            return null;
        }

        T result = first.getData();
        first = first.getNextNode();
        size--;

        if (isEmpty()) {
            last = null;
        }
        return result;
    }


    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty, returns {@code false} if it's not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns a size of the queue.
     *
     * @return an integer value that is a size of queue
     */
    public int size() {
        return size;
    }


    public static class Node<T> {
        private T data;
        private Node<T> nextNode;

        public Node(T data) {
            this.data = data;
        }

        public Node<T> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}


















//http://faculty.washington.edu/moishe/javademos/ch07%20Code/jss2/LinkedQueue.java


















