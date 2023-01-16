package Factory;

public class Car {
    public final Detail body;
    public final Detail engine;
    public final Detail accessories;
    private final int ID;

    public Car(Detail body,Detail engine,Detail accessories, int id) {
        this.body = body;
        this.engine = engine;
        this.accessories = accessories;
        this.ID = id;
    }

    public long getId() {
        return this.ID;
    }
}
