public class CircularlyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public CircularlyLinkedList(T head) {
        this.head = new Node<>(head);
        tail = this.head;
        this.head.left = tail;
        tail.right = this.head;
    }

    public CircularlyLinkedList() {
        this.head = null;
        tail = null;
    } 

    /////////////////////
    // Adding Elements //
    /////////////////////

    public void add(T value) {
        Node<T> placeholder = new Node<>(value);

        if (head == null) {
            head = placeholder;
            tail = placeholder;
        } else {
            tail.right = placeholder;
            placeholder.left = tail;
            tail = placeholder;
        }

        head.left = tail;
        tail.right = head;
    }

    public void insertAt(int index, T data) {
        add(index, data);
    }

    public void add(int pos, T value) {
        Node<T> newNode = new Node<>(value);

        if (head == null) {
            if (pos != 0) throw new IndexOutOfBoundsException();
            add(value);
            return;
        }

        if (pos < 0) throw new IndexOutOfBoundsException();

        if (pos == 0) {
            newNode.right = head;
            newNode.left = tail;

            head.left = newNode;
            tail.right = newNode;

            head = newNode;
            return;
        }

        Node<T> currentNode = head;
        for (int i = 0; i < pos; i++) {
            currentNode = currentNode.right;

            if (currentNode == head) {
                if (i == pos - 1) {
                    newNode.left = tail;
                    newNode.right = head;

                    head.left = newNode;
                    tail.right = newNode;

                    tail = newNode;
                    return;

                } else {
                    throw new IndexOutOfBoundsException();                    
                }
            }
        }

        newNode.left = currentNode.left;
        newNode.right = currentNode;
        newNode.left.right = newNode;
        newNode.right.left = newNode;
    }

    public void addFirst(T value) {
        add(0, value);
    }

    public void addLast(T value) {
        add(value);
    }



    ///////////////////////
    // Removing Elements //
    ///////////////////////

    public void remove(int pos) {
        Node<T> node = getNode(pos);

        if (head == tail) {
            clear();
            return;
        }
    
        if (node == head) {
            head = head.right;
        }
    
        if (node == tail) {
            tail = tail.left;
        }

        node.left.right = node.right;
        node.right.left = node.left;

        head.left = tail;
        tail.right = head;
    }

    public void removeFirst() {
        if (head == null) return;
        if (head == tail) { clear(); return; }
        head = head.right;

        head.left = tail;
        tail.right = head;
    }

    public void removeLast() {
        if (head == null) return;
        if (head == tail) { clear(); return;}
        tail = tail.left;
        
        head.left = tail;
        tail.right = head;
    }

    public boolean remove(T value) {
        int index = indexOf(value);

        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    public void clear() {
        head = null;
        tail = null;
    }



    /////////////////////////
    // Retrieving Elements //
    /////////////////////////

    public T get(int pos) {
        if (pos < 0) throw new IndexOutOfBoundsException();

        Node<T> currentNode = head;
        for (int i = 0; i < pos; i++) {
            currentNode = currentNode.right;

            if (currentNode == head) {
                throw new IndexOutOfBoundsException();
            }
        }

        return currentNode.data;
    }

    public Node<T> getNode(int pos) {
        if (pos < 0) throw new IndexOutOfBoundsException();

        Node<T> currentNode = head;
        for (int i = 0; i < pos; i++) {
            currentNode = currentNode.right;

            if (currentNode == head) {
                throw new IndexOutOfBoundsException();
            }
        }

        return currentNode;
    }

    public T getFirst() {
        return head.data;
    }

    public T getLast() {
        return tail.data;
    }

    public int indexOf(T value) {
        if (head == null) return -1;

        int pos = 0;
        Node<T> currentNode = head;
        do {
            if (currentNode.data.equals(value)) return pos;

            currentNode = currentNode.right;
            pos++;
        } while (currentNode != head);

        return -1;
    }

    public boolean contains(T value) {
        if (head == null) return false;

        Node<T> currentNode = head;
        do {
            if (currentNode.data.equals(value)) return true;

            currentNode = currentNode.right;
        } while (currentNode != head);

        return false;
    }

    public String toString() {
        if (head == null) return "";
        StringBuilder sb = new StringBuilder();

        Node<T> currentNode = head;
        do {
            sb.append(currentNode.data);
            currentNode = currentNode.right;
            if (currentNode != head) sb.append(", ");
        } while (currentNode != head);

        return sb.toString();
    }

    public String toStringBackwards() {
        if (tail == null) return "";
        StringBuilder sb = new StringBuilder();

        Node<T> currentNode = tail;
        do {
            sb.append(currentNode.data);
            currentNode = currentNode.left;
            if (currentNode != tail) sb.append(", ");
        } while (currentNode != tail);

        return sb.toString();
    }



    ///////////////////////
    // Updating Elements //
    ///////////////////////

    public void set(int pos, T value) {
        Node<T> node = getNode(pos);
        node.data = value;
    }



    ///////////////
    // Utilities //
    ///////////////

    public int size() {
        if (head == null) return 0;

        int size = 0;
        Node<T> currentNode = head;

        do {
            currentNode = currentNode.right;
            size++;
        } while (currentNode != head);
        
        return size;
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }
}
