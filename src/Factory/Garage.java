package Factory;

import java.util.ArrayDeque;

public class Garage {
    private ArrayDeque<Car> storage = new ArrayDeque<Car>();
    private final int size;
    StorageChecker checker;

    public Garage(int size) {
        this.size = size;
    }

    public void setChecker(StorageChecker checker) {
        this.checker = checker;
    }

    public boolean size() {
        return storage.size() < (size/2);
    }

    public int realSize() {
        return storage.size();
    }

    public int carToFull() {
        return size - storage.size();
    }

    public synchronized Car getDetail() {
        while(storage.isEmpty()) {
            try {
                checker.change();
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        checker.change();
        notify();
        return storage.pollLast();
    }

    public synchronized void add(Car d) {
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
}
