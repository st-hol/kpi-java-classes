package ua.procamp;

import java.util.Objects;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}. In order to keep track on nodes, {@link LinkedList} keeps a reference to a
 * head node.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {

    private Node<T> first = null;
    private int size = 0;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    @SafeVarargs
    public static <T> List<T> of(T... elements) {
        LinkedList<T> linkedList = new LinkedList<>();
        for (T element : elements) {
            linkedList.add(element);
        }
        return linkedList;
    }

    /**
     * Adds an element to the end of the list
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        if (element == null) {
            return;
        }
        if (first == null) {
            first = new Node<>(element);
        } else {
            Node<T> newNode = new Node<>(element);
            Node<T> lastNode = getLastNode(first);
            lastNode.setNextNode(newNode);
        }
        size++;
    }

    /**
     * Add element at first. set the newly created node as root node
     *
     * @param data
     */
    public void addAtFirst(T data) {
        if (data == null) {
            return;
        }
        Node<T> newNode = new Node<>(data);
        if (this.first != null) {
            newNode.setNextNode(this.first);
            this.first = newNode;
        } else {
            this.first = newNode;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if (index == 0) {
            addAtFirst(element);
            return;
        }
        if (index == this.size) {
            add(element);
        } else if (index < this.size) {
            Node<T> newNode = new Node<>(element);
            Node<T> leftNode = getNode(index - 1);
            Node<T> rightNode = getNode(index);
            newNode.setNextNode(rightNode);
            leftNode.setNextNode(newNode);
            size++;
        } else {
            throw new IndexOutOfBoundsException("Index not available.");
        }
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        if (index >= 0 && index < size - 1) {
            Node<T> node = getNode(index);
            assert node != null;
            node.setData(element);
        } else {
            throw new IndexOutOfBoundsException("Index not available.");
        }
    }


    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        return Objects.requireNonNull(getNode(index)).getData();
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     */
    @Override
    public void remove(int index) {
        if (this.first == null) {
            throw new IndexOutOfBoundsException("Index not available.");
        }
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException("Index not available.");
        }
        if (index == 0) {
            if (this.first.getNextNode() != null) {
                this.first = getNode(index + 1);
            } else {
                this.first = null;
            }
            size--;
            return;
        }
        if (index == this.size - 1) {
            getNode(index - 1).setNextNode(null);
            size--;
            return;
        }

        Node<T> leftNode = getNode(index - 1);
        Node<T> rightNode = getNode(index + 1);
        leftNode.setNextNode(rightNode);
        size--;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        boolean contains = false;
        for(int i = size-1; i >=0; i--){
            if (Objects.requireNonNull(getNode(i)).getData().equals(element)){
                contains = true;
                break;
            }
        }
        return contains;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        this.first = null;
        this.size = 0;
//        for(int i = size-1; i >= 0; i--){
//            remove(i);
//        }
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException("Index not available.");
        }
        if (index == 0) {
            return this.first;
        }
        if (index == this.size - 1) {
            return getLastNode(this.first);
        }
        int pointer = 0;
        Node<T> pointerNode = this.first;
        while (pointer <= index) {
            if (pointer == index) {
                return pointerNode;
            } else {
                pointerNode = next(pointerNode);
                pointer++;
            }
        }
        return null;
    }

    private Node<T> getLastNode(Node<T> node) {
        Node<T> lastNode = node;
        if (lastNode.getNextNode() != null) {
            return getLastNode(lastNode.getNextNode());
        } else {
            return lastNode;
        }
    }

    private Node<T> next(Node<T> node) {
        if (node.getNextNode() != null) {
            return node.getNextNode();
        } else {
            return null;
        }
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



































//https://netsurfingzone.com/datastructures/delete-operation-in-linked-list







