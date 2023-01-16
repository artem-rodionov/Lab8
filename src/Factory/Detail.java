package Factory;

public class Detail {
        private final long id;
        private final String type;

    public Detail(String type, long id) {
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return this.id;
    }
}
