public class BinaryTree<T extends Comparable<T>> {
    private Node<T> head;

    public BinaryTree(T head) {
        this.head = new Node<>(head);
    }

    public BinaryTree() {
        this.head = null;
    }

    public Node<T> get(T value) {
        Node<T> current = head;

        while (current != null) {
            int cmp = value.compareTo(current.data);
            if (cmp == 0) return current;
            else if (cmp < 0) current = current.left;
            else current = current.right;
        }
        return null; // not found
    }

    public Node<T> getByPath(String binPath) { // Not recommended
        Node<T> currNode = head;
        for (int i = 0; i < binPath.length(); i++) {
            char c = binPath.charAt(i);

            if (currNode == null) {
                return currNode;
            } else if (c == '0') {
                currNode = currNode.left;
            } else if (c == '1') {
                currNode = currNode.right;
            } else {
                throw new IllegalArgumentException(
                    "Path may only contain a binary String. Invalid char: " + c
                );
            }
        }
        return currNode;
    }

    public void add(T value) {
        if (head == null) {
            head = new Node<>(value);
            return;
        }

        Node<T> current = head;

        while (true) {
            int cmp = value.compareTo(current.data);
            if (cmp == 0) {
                return;
            } else if (cmp < 0) {
                if (current.left != null) {
                    current = current.left;
                } else {
                    current.left = new Node<T>(value);
                    return;
                }
            } else if (cmp > 0) {
                if (current.right != null) {
                    current = current.right;
                } else {
                    current.right = new Node<T>(value);
                    return;
                }
            }
        }
    }

    public boolean remove(T value) {
        Node<T> current = head;

        if (head == null) return false;

        if (head.data.equals(value)) {
            if (head.left == null) head = head.right;
            else if (head.right == null) head = head.left;
            else {
                // head has two children, more complex
                return false; // for now, ignored
            }
            return true;
        }

        while (true) {
            int cmp = value.compareTo(current.data);

            if (cmp < 0) {
                if (current.left == null) return false;

                if (current.left.data.equals(value)) {
                    // zero or one child
                    if (current.left.left == null) current.left = current.left.right;
                    else if (current.left.right == null) current.left = current.left.left;
                    else {
                        // two children, more complex
                        return false; // for now, ignored
                    }
                    return true;
                } else {
                    current = current.left;
                }

            } else { // cmp > 0
                if (current.right == null) return false;

                if (current.right.data.equals(value)) {
                    // zero or one child
                    if (current.right.left == null) current.right = current.right.right;
                    else if (current.right.right == null) current.right = current.right.left;
                    else {
                        // two children, more complex
                        return false; // for now, ignored
                    }
                    return true;
                } else {
                    current = current.right;
                }
            }
        }
    }
}