interface IteratorOfInt{
  boolean hasNext();
  int next();
}
public class IntListIterator implements IteratorOfInt{
  private Node current;

  public IntListIterator(Node e) {
    this.current = e;
  }

  //prueft, ob es noch weitere Eintraege gibt
  public boolean hasNext() {
    return current != null;
  }

  //liefert den naechsten Eintrag
  public int next() {
    int res = current.getValue();
    current = current.getNext();
    return res;
  }
}