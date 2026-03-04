public class LinkedList<T> {
    Node<T> head;
    Node<T> tail;

    public LinkedList(T head) {
        this.head = new Node<>(head);
        tail = this.head;
    }

    public LinkedList() {
        this.head = null;
        tail = null;
    }

    public Node<T> get(int pos) {
        if (pos >= size()) {
            throw new IndexOutOfBoundsException(
                "Position " + pos + " is out of bounds. Size is " + size() + "."
            );
        } else {
            Node<T> currentNode = head;
            for (int i = 0; i < pos; i++) {
                currentNode = currentNode.right;
            }
            return currentNode;
        }
    }    

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

    }

    public void add(int pos, T value) {
        Node<T> newNode = new Node<>(value);

        if (pos > size()) {
            throw new IndexOutOfBoundsException(
                "Position " + pos + " is out of bounds. Size is " + size() + "."
            );
        } else if (pos > 0) {
            Node<T> leftNode = get(pos - 1);
            Node<T> rightNode = leftNode.right;

            newNode.left = leftNode;
            newNode.right = rightNode;

            leftNode.right = newNode;

            // for if pos == size()
            if (rightNode != null) rightNode.left = newNode;
            else tail = newNode;

        } else { // Changing Head
            newNode.right = head;
            head.left = newNode;
            head = newNode;
        }
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        if (head != null) head.left = newNode;
        newNode.right = head;
        head = newNode;
        if (tail == null) tail = newNode;
    }

    public void remove(int pos) {
        Node<T> node = get(pos);

        if (node.right != null) node.right.left = node.left;
        else tail = node.left;

        if (node.left != null) node.left.right = node.right;
        else head = node.right;
    }

    public void set(int pos, T value) {
        if (pos < 0 || pos >= size()) {
            throw new IndexOutOfBoundsException(
                "Position " + pos + " is out of bounds. Size is " + size() + "."
            );
        } else {
            Node<T> node = get(pos);
            node.data = value;
        }
    }

    public int size() {
        int size = 0;

        Node<T> currentNode = head;
        while (currentNode != null) {
            currentNode = currentNode.right;
            size++;
        }
        return size;
    }
}
