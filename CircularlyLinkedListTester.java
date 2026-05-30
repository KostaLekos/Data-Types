import java.util.LinkedList;

public class CircularlyLinkedListTester {
    public static void main(String[] args) {
        // Create empty list
        CircularlyLinkedList<Integer> list = new CircularlyLinkedList<>();

        System.out.println("List empty? " + list.isEmpty());

        // Add elements
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("After add(10,20,30): " + list);

        // Add at position
        list.add(0, 5);  // add at head
        list.add(2, 15); // add in middle
        list.add(list.size(), 35); // add at tail
        System.out.println("After add at positions: " + list);

        // Add first / last
        list.addFirst(1);
        list.addLast(40);
        System.out.println("After addFirst(1) and addLast(40): " + list);

        // Remove by position
        list.remove(0); // remove head
        list.remove(list.size() - 1); // remove tail
        list.remove(2); // remove middle
        System.out.println("After removes: " + list);

        // Remove by value
        boolean removed = list.remove((Integer) 20);
        System.out.println("Removed 20? " + removed + ", list: " + list);

        removed = list.remove((Integer) 100);
        System.out.println("Removed 100? " + removed + ", list: " + list);

        // Get elements
        System.out.println("First: " + list.getFirst());
        System.out.println("Last: " + list.getLast());
        System.out.println("Element at index 1: " + list.get(1));

        // Set elements
        list.set(1, 25);
        System.out.println("After set(1,25): " + list);

        // Contains / indexOf
        System.out.println("Contains 25? " + list.contains(25));
        System.out.println("Index of 25: " + list.indexOf(25));

        // Traversal backwards
        System.out.println("Backwards: " + list.toStringBackwards());

        // Utilities
        System.out.println("Size: " + list.size());
        System.out.println("Empty? " + list.isEmpty());

        // Clear
        list.clear();
        System.out.println("After clear(), empty? " + list.isEmpty() + ", list: " + list);

        CircularlyLinkedList<String> stringList = new CircularlyLinkedList<>();
        stringList.add("string1");
        stringList.add("string2");
        stringList.add("string3");

        System.out.println("String list: " + stringList);
        System.out.println("String list backwards: " + stringList.toStringBackwards());
    }
}