package queue;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

class CircularQueueLL {
    private Node front = null;
    private Node rear = null;

    // Check empty
    public boolean isEmpty() {
        return front == null;
    }

    // Enqueue operation
    public void enqueue(int data) {
        Node newNode = new Node(data);

        if (isEmpty()) {
            front = rear = newNode;
            rear.next = front;   // circular link
        } else {
            rear.next = newNode;
            rear = newNode;
            rear.next = front;
        }
        System.out.println(data + " inserted");
    }

    // Dequeue operation
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is Empty!");
            return -1;
        }

        int removed;

        // Single element case
        if (front == rear) {
            removed = front.data;
            front = rear = null;
        } else {
            removed = front.data;
            front = front.next;
            rear.next = front;
        }

        System.out.println(removed + " removed");
        return removed;
    }

    // Peek front
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is Empty");
            return -1;
        }
        return front.data;
    }

    // Display queue
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is Empty!");
            return;
        }

        Node temp = front;
        System.out.print("Queue elements: ");

        do {
            System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != front);

        System.out.println();
    }
}

public class CirecularQueueViaLinkedList {
    public static void main(String[] args) {
        CircularQueueLL q = new CircularQueueLL();

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);

        q.display();

        q.dequeue();
        q.display();
    }
}
