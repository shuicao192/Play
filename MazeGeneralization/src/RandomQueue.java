import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RandomQueue<E> {

    private LinkedList<E> queue;

    public RandomQueue() {
        queue = new LinkedList<>();
    }

    public void add(E e) {
        if (Math.random() < 0.5) {
            queue.addFirst(e);
        } else {
            queue.addLast(e);
        }

    }

    public E remove() {
        int size = queue.size();
        if (size == 0) {
            throw new IllegalArgumentException("no element to remove");
        }

//        int randomIndex = (int) (Math.random() * size);
//        E randomElement = queue.get(randomIndex);
//        queue.set(randomIndex, queue.get(size - 1));
//        queue.remove(size - 1);
//        return randomElement;
        if(Math.random() < 0.5)
            return queue.removeFirst();
        else
            return queue.removeLast();
    }

    public int getSize(){
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
