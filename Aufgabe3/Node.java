
/**
 * ein Knoten in einer IntList
 */
class Node {
    private int value;
    private Node next;

    Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
    int getValue() {
      return value;
    }
    Node getNext() {
      return next;
    }
    void setNext(Node n) {
      next = n;
    }
}
