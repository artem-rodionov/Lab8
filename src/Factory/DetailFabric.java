package Factory;

public class DetailFabric {
    private static long detail_num;

    public DetailFabric() {
        detail_num = 0;
    }

    static synchronized public Detail createDetail(String type) {
        Detail a = new Detail(type, detail_num++);
        return a;
    }
}
