package Factory;
import java.io.IOException;
import java.util.Date;
import java.util.logging.*;

public class Dealer {
    private static final Logger LOGGER = Logger.getLogger(Dealer.class.getName());

    Garage garage;

    ThreadPool dealers;
    int CarSale = 0;

    int num;

    int milisec = 2000;
    FileHandler handler;


    public Dealer(int num, Garage storage,boolean salelog) {
        this.num = num;
        this.dealers = new ThreadPool(num);
        garage = storage;
        if(salelog) {
            try {
                FileHandler fhandler = new FileHandler("Logfile.txt");
                LOGGER.addHandler(fhandler);

            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            } catch (SecurityException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    public void setSpeed(int n){
        milisec = n;
    }

    public int getSaled() {
        return CarSale;
    }

    public void setWork(Runnable task) {
        dealers.execute(task);
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
            while (true) {
                try {
                    Thread.sleep(milisec);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Car car = garage.getDetail();
                CarSale++;
                LOGGER.log(Level.FINE, new Date() + ": Dealer " + Thread.currentThread().getId() + " : Auto " + car.getId() + " (Body: " + car.body.getId() + " , Motor: " + car.engine.getId() + " , Accessory: " + car.accessories.getId() + ")");
            }
        };
    }
}
