interface IteratorOfInt{
    boolean hasNext();
    int next();
  }
public class IntArrayListIterator implements IteratorOfInt {
    private int[] elementData;
    private int size;
    private int position;
    
    IntArrayListIterator(int[] elementData, int size) {
        this.elementData = elementData;
        this.size = size;
        this.position = 0;
    }

    public boolean hasNext() {
        return position < size;
    }
    
    public int next() {
        int elem = elementData[position];
        position++;
        return elem;
    }

}
