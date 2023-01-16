package Factory;

public class CarFactory {
    ThreadPool workers;

    int num;
    StorageDetail body;
    StorageDetail engine;
    StorageDetail accessories;

    Garage garage;

    boolean isRunnable = true;
    int ID = 0;
    public int millisec = 10000;

    public CarFactory(int num,StorageDetail body, StorageDetail engine, StorageDetail accessories,Garage garage) {
        workers = new ThreadPool(num);
        this.num = num;
        this.body = body;
        this.engine = engine;
        this.accessories = accessories;
        this.garage = garage;
    }

    public void setWork(Runnable task) {
        workers.execute(task);
    }

    public void createTask(int num) {
        int n = 0;
        while(n < num) {
            setWork(run());
            n++;
        }
    }

    public void setSpeed(int n) {
        millisec = n;
    }

    public void shutdown() {
        isRunnable = !isRunnable;
    }

    public Runnable run() {
        return () -> {
                Detail nbody = body.getDetail();
                Detail nengine = engine.getDetail();
                Detail naccessor = accessories.getDetail();
                Car car = null;
                if (nbody != null && nengine != null && naccessor != null) {
                    car = new Car(nbody, nengine, naccessor, ++ID);
                }
                if (car != null) {
                    garage.add(car);
                    try {
                        Thread.sleep(millisec);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                   // System.out.println(Thread.currentThread().getName() + ": Машина собрана " + car.getId() + " (двигатель : " + nbody.getId() + "; мотор : " + nengine.getId() + "; аксесуар : " + naccessor.getId() + " )");
                }
        };
    }
}
