interface ListOfInt {
    void add(int element);
    int get(int element);
    int size();
    IteratorOfInt iterator();
  }
  
// Einfachverkettete Liste mit Integer-Elementen
public class IntList implements ListOfInt{
    private Node first;


    /**
     * Erstellt eine leere IntList
     */
    public IntList() {
        first = null;
     }

    /**
     * Erstellt eine IntList mit den gegebenen Elementen
     */
    public IntList(int[] elements) {
        for (int i = 0; i < elements.length; i++) {
            add(elements[i]);
        }
    }

    /**
     * Fügt ein Element am Ende der Liste ein
     */
    public void add (int element) {
        Node newNode = new Node(element, null);
        if (first == null) {
            first = newNode;
        } else {
            // Ende der Liste finden:
            Node n = first;
            while (n.getNext() != null) {
                n = n.getNext();
            }
            // Neuen Knoten am Ende einfuegen:
            n.setNext(newNode);
        }
    }

    /**
     *  Liefert das Element an Position index
     *
     *  requires 0 <= index < size()
    */
    public int get(int index) {
        Node n = first;
        for (int i = 0; i < index; i++) {
            n = n.getNext();
        }
        return n.getValue();
    }

    /** gibt die Anzahl der Elemente in der Liste zurück */
    public int size() {
        int size = 0;
        Node n = first;
        while (n != null) {
            size++;
            n = n.getNext();
        }
        return size;
    }


    /** Rekursive Variante der size-Methode **/
    public int sizeRec() {
        return sizeRec(first);
    }

    // Rekursive Hilfsmethode
    private int sizeRec(Node n) {
        if (n == null) {
            return 0;
        } else {
            return 1 + sizeRec(n.getNext());
        }
    }

    /** prueft, ob das gegebene Element in der Liste vorkommt
     *
     * ensures \result == (es gibt ein int i in [0,size()-1], so dass get(i) == element)
     * */
    public boolean contains(int element) {
        Node n = first;
        while (n != null) {
            if (n.getValue() == element) {
                return true;
            }
            n = n.getNext();
        }
        return false;
    }

    /** gibt eine String-Darstellung der Liste zurück */
    public String toString() {
        String result = "";
        Node n = first;
        while (n != null) {
            if (n != first) {
                result += ", ";
            }
            result += n.getValue();
            n = n.getNext();
        }
        return result;
    }

    /** fügt das gegebene Element an der gegeben Stelle (index) in die Liste ein
     *
     * requries 0 <= index <= size()
     * ensures size() == 1 + \old(size())
     *     und get(index) == element
     * */
    public void add(int index, int element) {
        if (index == 0) {
            // einfuegen am Anfang:
            first = new Node(element, first);
        } else {
            Node n = first;
            for (int i=1; i<index; i++) {
                n = n.getNext();
            }
            // einfuegen nach n:
            n.setNext(new Node(element, n.getNext()));
        }
    }

    public IntListIterator iterator() {
        return new IntListIterator(first);
    }

}
