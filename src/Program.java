import Factory.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class Program {

    public static void main(String[] args) throws InterruptedException {
        Properties property = new Properties();
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream("A:\\Users\\Artem\\IdeaProjects\\lab8\\src\\Conf.properties"))) {
            property.load(reader);
        } catch (IOException e) {
        }
        DetailFabric fabric = new
        DetailFabric();
        StorageDetail storeBody = new StorageDetail(Integer.parseInt(property.getProperty("StorageBodySize")));
        StorageDetail storeEngine = new StorageDetail(Integer.parseInt(property.getProperty("StorageMotorSize")));
        StorageDetail storeAccessor = new StorageDetail(Integer.parseInt(property.getProperty("StorageAccessorySize")));

        Garage garage = new Garage(Integer.parseInt(property.getProperty("StorageAutoSize")));

        CarFactory factory = new CarFactory(Integer.parseInt(property.getProperty("Workers")), storeBody, storeEngine, storeAccessor, garage);

        StorageChecker checker = new StorageChecker(garage, factory);
        garage.setChecker(checker);
        new Thread(checker).start();

        ProviderAccesories accessorProvider = new ProviderAccesories(Integer.parseInt(property.getProperty("AccessorySuppliers")), storeAccessor, fabric);

        Provider bodyProvider = new Provider(storeBody, "body", fabric);
        Provider engineProvider = new Provider(storeEngine, "engine", fabric);

        Dealer dealer = new Dealer(Integer.parseInt(property.getProperty("Dealers")), garage,(boolean) Boolean.parseBoolean(property.getProperty("LogSale")));

        FactoryUI UI = new FactoryUI(bodyProvider,storeBody,engineProvider,storeEngine,accessorProvider,storeAccessor,factory,garage,dealer);

        new Thread(bodyProvider).start();
        new Thread(engineProvider).start();
        accessorProvider.createTask();
        Thread.sleep(2000);
        dealer.createTask();

        while(true) {
            UI.update();
        }
    }
}
