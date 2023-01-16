package Factory;

public class Provider implements Runnable{
    StorageDetail storage;
    final String type;
    DetailFabric fabric;

    public int millisec = 1000;

    public void setSpeed(int n) {
        millisec = n;
    }

    public Provider(StorageDetail storage,String type, DetailFabric fabric) {
        this.storage = storage;
        this.fabric = fabric;
        this.type = type;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(millisec);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Detail a = fabric.createDetail(type);
            storage.add(a);
        }
    }
}
