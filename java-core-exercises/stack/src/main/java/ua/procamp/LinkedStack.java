package ua.procamp;


import ua.procamp.exception.EmptyStackException;

public class LinkedStack<T> implements Stack<T> {
	private Node<T> front;
	private int size;

	public LinkedStack() {
		front = null;
		size = 0;
	}

	@Override
	public T pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		T top = front.getData();
		front = front.getNextNode();
		size--;
		return top;
	}

	@Override
	public void push(T value) {
		Node<T> newNode = new Node<>(value);
		newNode.setNextNode(front);
		front = newNode;
		size++;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
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

































//https://algs4.cs.princeton.edu/13stacks/LinkedStack.java.html









