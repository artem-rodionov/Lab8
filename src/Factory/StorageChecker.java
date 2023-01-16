package Factory;

public class StorageChecker implements Runnable{
    Garage garage;

    volatile boolean del = true;

    CarFactory factory;

    public StorageChecker(Garage garage,CarFactory factory) {
        this.garage = garage;
        this.factory = factory;
    }

    public void change() {
        del = !del;
    }

    @Override
    public void run() {
        while (true) {
            if(del) {
                if (garage.size()) {
                    factory.createTask(garage.carToFull());
                }
                change();
            }
        }
    }
}
