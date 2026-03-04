public class Node<T> {
    T data;
    Node<T> right;
    Node<T> left;

    public Node(T data) {
        this.data = data;
        this.right = null;
        this.left = null;
    }
}