package Factory;

public class ProviderAccesories {
    StorageDetail storage;
    final String type = "accessories";
    DetailFabric fabric;
    ThreadPool2 providers;
    int num;

    public int millisec = 1000;

    public ProviderAccesories(int num,StorageDetail accessories, DetailFabric fabric) {
        providers = new ThreadPool2(num);
        this.num = num;
        storage = accessories;
        this.fabric = fabric;
    }

    public void setSpeed(int n) {
        millisec = n;
    }
    public void setWork(Runnable task) {
        providers.execute(task);
    }

    public void createTask() {
        int n = 0;
        while(n < num) {
            setWork(run());
            n++;
        }
    }

    public Runnable run() {
        return () -> {
            while(true) {
                try {
                    Thread.sleep(millisec);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Detail a = fabric.createDetail(type);
                storage.add(a);
            }
        };
    }
}
