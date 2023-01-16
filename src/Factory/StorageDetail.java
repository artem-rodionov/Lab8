package Factory;

import java.util.ArrayDeque;

public class StorageDetail {

    private ArrayDeque<Detail> storage = new ArrayDeque<Detail>();
    private final int size;

    public StorageDetail(int size) {
        this.size = size;
    }

    public int size() {
        return storage.size();
    }

    public synchronized Detail getDetail() {
        while(storage.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        notify();
        return storage.pollLast();
    }

    public synchronized void add(Detail d) {
        while (this.size < storage.size()+1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        storage.addFirst(d);
        notify();
    }

    public boolean isEmpty() {
        return storage.isEmpty();
    }

    public boolean isFull() {
        if(this.size == storage.size()) return true;
        else return false;
    }
}
