package ixs180019;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Program implements a bound sized queue ixs180019.BoundedQueue using data structures 'arrays'
 * It consists of following methods: offer(), poll(), peek(), size(), isEmpty(), clear(), toArray(), printQ()
 * @aurthor Ishan Suketu Shah(ixs180019)
 * @author Ayesha Gurnani (ang170003)
 * @param <T>
 * */
public class BoundedQueue<T> {
    private int maxSize, front, rear, size;
    private Object[] boundedQueue;

    /**
     * Constructor of ixs180019.BoundedQueue
     * @param size: Size of the bounded queue
     * @return null
     * */
    public BoundedQueue(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Queue size should be greater than 1");
        }
        maxSize = size;
        front = size = 0;
        rear = maxSize - 1;
        boundedQueue = new Object[maxSize];
    }

    /**
     * Method: offer()
     * Adds element at the rear end of queue. Return false if the queue is full and element cant be inserted else True.
     * @param x: Element to be added
     * @return: boolean values, True or False
     * */
    public boolean offer(T x) {
        if (isFull()) {
            return false;
        }
        rear = (rear + 1) % maxSize;
        boundedQueue[rear] = x;
        size++;
        return true;
    }

    /***
     * Method: poll()
     * Pops an element from the front end of the queue.
     * @param: null
     * @return: element that is popped
     */
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T firstItem = (T) boundedQueue[front];
        front = (front + 1) % maxSize;
        size--;
        return firstItem;
    }

    /***
     * Method: peek()
     * Returns an element from the front end of the queue
     * @param: null
     * @return: element that is returned
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return (T)boundedQueue[front];
    }

    /**
     * Method: size()
     * Gives size of the queue
     * @param: null
     * @return: size of the queue
     * */
    public int size() {
        return size;
    }

    /**
     * Method: isEmpty()
     * Tells if the queue is empty or not
     * @param: null
     * @return: boolean, True or False
     * */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Method: clear()
     * Clear the queue
     * @param: null
     * @return: null
     * */
    public void clear() {
        front = size = 0;
        rear = maxSize - 1;
    }

    /**
     * Method: toArray()
     * Converts queue to an array
     * @param: initialized array 'a'
     * @return: null
     * */
    public void toArray(T[] a) {
        if (a.length < size) {
            throw new IllegalArgumentException("Array size is smaller than elements present in queue");
        }

        int i = front % maxSize;
        int arrayIndex = 0;
        while (arrayIndex < size) {
            a[arrayIndex++] = (T)boundedQueue[i];
            i = (i+1) % maxSize;
        }
        System.out.println();
    }

    /**
     * Method: printQ()
     * Print the elements of queue
     * @param: null
     * @return: null
     * */
    public void printQ() {

        int i = front % maxSize;
        int arrayIndex = 0;
        System.out.print(this.size + ": ");
        while (arrayIndex < size) {
            System.out.print(boundedQueue[i] + " ");
            i = (i+1) % maxSize;
            arrayIndex += 1;
        }
        System.out.println();
    }

    /**
     * Method checks if the queue is full or not
     * @param: null
     * @return: boolean, True or False
     * */
    public boolean isFull() {
        return size == maxSize;
    }

    /**
     * Main method of the ixs180019.BoundedQueue class
     * @param: args array
     * @return: null
     * */
    public static void main(String[] args) {
        int n;
        if (args.length > 0) {
            n = Integer.parseInt((args[0]));
        }

        Scanner in = new Scanner(System.in);
        System.out.println("Enter size:");
        n = in.nextInt();

        BoundedQueue<Integer> queue = new BoundedQueue<>(n);
        for(int i = 1; i <= n; i++) {
            queue.offer(Integer.valueOf(i));
        }

        queue.printQ();
        System.out.println("1: Add an element");
        System.out.println("2: Poll queue");
        System.out.println("3: Peek Queue");
        System.out.println("4: Get Size");
        System.out.println("5: Check if queue is empty");
        System.out.println("6 :Clear queue");
        System.out.println("7 :Convert queue to array");

        whileloop:
        while (in.hasNext()) {
            int com = in.nextInt();
            switch (com) {
                case 1: // Add element
                    System.out.println("Add an element: ");
                    if (!queue.offer(Integer.valueOf(in.nextInt()))) {
                        System.out.println("False: Can't add element. Queue is full");
                    };
                    queue.printQ();
                    break;
                case 2: // Poll element
                    System.out.println("Poll: " + queue.poll());
                    queue.printQ();
                    break;
                case 3: // Peek element
                    System.out.println("Peek: " + queue.peek());
                    queue.printQ();
                    break;
                case 4: // Get size element
                    System.out.println("Size: " + queue.size());
                    queue.printQ();
                    break;
                case 5: // Check if queue is empty
                    System.out.println(queue.isEmpty() ? "Queue is empty" : "Queue is not empty");
                    queue.printQ();
                    break;
                case 6: // Clear queue
                    System.out.println("Clear queue: ");
                    queue.clear();
                    queue.printQ();
                    break;
                case 7: // Add elements from queue to array
                    Integer[] arr = new Integer[queue.size];
                    queue.toArray(arr);
                    System.out.println(Arrays.toString(arr));
                    break;
                default:
                    break whileloop;
            }
        }
    }
}
