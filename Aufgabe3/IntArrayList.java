import java.util.Arrays;
public class IntArrayList implements ListOfInt {
    private int[] elementData = {};
    private int size = 0;
    
    /** Element am Ende der Liste einfuegen */
    public void add(int element) {
        ensureCapacity(size + 1);
        elementData[size] = element;
        size++;
    }

    /** stellt sicher, dass Array genug Platz hat */
    private void ensureCapacity(int minCapacity) {
        if (elementData.length < minCapacity) {
            // Groesse verdoppeln, mindestens auf minCapacity
            int newSize = Math.max(minCapacity, 2*elementData.length);
            elementData = Arrays.copyOf(elementData, newSize);
        }
    }

    /** liefert einen Iterator fuer die Liste*/
    public IteratorOfInt iterator() {
        return new IntArrayListIterator(elementData, size);
    }
    
    /** liefert das Element an der gegebenen Position */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elementData[index];
    }
    
    /** gibt die erste Position eines Elements in der Liste oder -1,
     *  wenn das Element nicht in der Liste ist */
    public int indexOf(int element) {
        for (int i=0; i<size; i++) {
            if (elementData[i] == element) {
                return i;
            }
        }
        return -1;
    }
    
    /** entfernt das erste Vorkommen eines Elements aus der Liste */
    public void remove(int element) {
        int indexOf = indexOf(element);
        if (indexOf < 0) {
            // Element nicht vorhanden
            return;
        }
        // alle Elemente nach dem ersten Vorkommen nach links verschieben:
        for (int i=indexOf; i<size-1; i++) {
            elementData[i] = elementData[i+1];
        }
        size--;
    }
    
    /** liefert eine String-Repraesentation der Liste */
    public String toString() {
        String result = "";
        for (int i=0; i<size; i++) {
            if (i>0) {
                result += ", ";
            }
            result += elementData[i];
        }
        return result;
    }
    public int size() {
        return this.size;
    }

}
