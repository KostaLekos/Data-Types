public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public LinkedList(T head) {
        this.head = new Node<>(head);
        tail = this.head;
    }

    public LinkedList() {
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
    }

    public void add(int pos, T value) {
        Node<T> newNode = new Node<>(value);

        if (pos > size()) {
            throw new IndexOutOfBoundsException(
                "Position " + pos + " is out of bounds. Size is " + size() + "."
            );
        } else if (pos == size()) {
            add(value);
        }else if (pos > 0) {
            Node<T> leftNode = get(pos - 1);
            Node<T> rightNode = leftNode.right;

            newNode.left = leftNode;
            newNode.right = rightNode;

            leftNode.right = newNode;
            rightNode.left = newNode;
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

    public void addLast(T value) {
        add(value);
    }



    ///////////////////////
    // Removing Elements //
    ///////////////////////

    public void remove(int pos) {
        Node<T> node = get(pos);

        if (node.right != null) node.right.left = node.left;
        else tail = node.left;

        if (node.left != null) node.left.right = node.right;
        else head = node.right;
    }

    public void removeFirst() {
        if (head == null) return;
        if (head == tail) { clear(); return; }
        head.right.left = null;
        head = head.right;
    }

    public void removeLast() {
        if (head == null) return;
        if (head == tail) { clear(); return;} 
        tail.left.right = null;
        tail = tail.left;        
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

    public Node<T> getFirst() {
        return head;
    }

    public Node<T> getLast() {
        return tail;
    }

    public int indexOf(T value) {
        int pos = 0;
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.data.equals(value)) return pos;

            currentNode = currentNode.right;
            pos++;
        }
        return -1;
    }

    public boolean contains(T value) {
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.data.equals(value)) return true;

            currentNode = currentNode.right;
        }
        return false;
    }



    ///////////////////////
    // Updating Elements //
    ///////////////////////

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



    ///////////////
    // Utilities //
    ///////////////

    public int size() {
        int size = 0;

        Node<T> currentNode = head;
        while (currentNode != null) {
            currentNode = currentNode.right;
            size++;
        }
        return size;
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    public boolean isCorrupted() {
        return head == null ^ tail == null;
    }

    public void fixList() {
        if (head == null && tail != null) {
            Node<T> currentNode = tail;
            while (currentNode.left != null) currentNode = currentNode.left;
            head = currentNode;
        } else if (head != null && tail == null) {
            Node<T> currentNode = head;
            while (currentNode.right != null) currentNode = currentNode.right;
            tail = currentNode;
        }
    }

    public void ensureIntegrity() {
        if (head == null ^ tail == null) fixList();
    }



    ///////////////
    // Traversal //
    ///////////////
    
    public void shiftRight() {
        // empty or 1 element
        if (head == null || head.right == null) return;

        // Move second to last to tail
        Node<T> oldTail = tail;
        tail = tail.left;
        tail.right = null;

        // Move oldTail to head
        oldTail.right = head;
        head.left = oldTail;
        oldTail.left = null;
        head = oldTail;
    }

    public void shiftRight(int num) {
        if (num == 0) return;
        num %= size();
        for (int i = 0; i < num; i++) shiftRight();
    }

    public void shiftLeft() {
        // empty or 1 element
        if (head == null || head.right == null) return;

        // Move second node to head
        Node<T> oldHead = head;
        head = head.right;
        head.left = null;

        // Move oldHead to tail
        tail.right = oldHead;
        oldHead.left = tail;
        oldHead.right = null;
        tail = oldHead;
    }

    public void shiftLeft(int num) {
        if (num == 0) return;
        num %= size();
        for (int i = 0; i < num; i++) shiftLeft();
    }

    public String toString() {
        if (head == null) return "";
        StringBuilder sb = new StringBuilder();

        Node<T> currentNode = head;
        while (currentNode != null) {
            sb.append(currentNode.data);
            if (currentNode.right != null) sb.append(", ");
            currentNode = currentNode.right;
        }
        return sb.toString();
    }

    public String toStringBackwards() {
        if (tail == null) return "";
        StringBuilder sb = new StringBuilder();

        Node<T> currentNode = tail;
        while (currentNode != null) {
            sb.append(currentNode.data);
            if (currentNode.left != null) sb.append(", ");
            currentNode = currentNode.left;
        }
        return sb.toString();
    }
}
