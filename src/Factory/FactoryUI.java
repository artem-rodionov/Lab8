package Factory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.synth.SynthTreeUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.util.Dictionary;
import java.util.Hashtable;

public class FactoryUI extends JFrame {

    JLabel label;
    JPanel contents = new JPanel();

    JLabel body_label = new JLabel();
    JLabel engine_label = new JLabel();
    JLabel accessor_label = new JLabel();

    JLabel car_storage = new JLabel();
    JLabel car_saled = new JLabel();

    StorageDetail bodyst, enginest, accessorst;
    Garage garage;

    Dealer dealers;
    public FactoryUI(Provider bodyp, StorageDetail bodyst, Provider enginep, StorageDetail enginest, ProviderAccesories accesories, StorageDetail accessorst, CarFactory factor, Garage garage, Dealer dealers) {
        super("Фабрика");
        this.bodyst = bodyst;
        this.enginest = enginest;
        this.accessorst = accessorst;
        this.garage = garage;
        this.dealers = dealers;
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Создание модели ползунков
        BoundedRangeModel model = new DefaultBoundedRangeModel(80, 0, 0, 200);

        // Создание ползунков
        JSlider body = new JSlider(0, 60, 1);
        JSlider engine = new JSlider(0, 60, 1);
        JSlider accessor = new JSlider(0, 60, 2);
        JSlider factory = new JSlider(0, 60, 4);
        JSlider dealer = new JSlider(0, 60, 1);
        // Настройка внешнего вида ползунков

        body.setPaintLabels(true);
        body.setMajorTickSpacing(10);

        engine.setPaintLabels(true);
        engine.setMajorTickSpacing(10);

        accessor.setPaintLabels(true);
        accessor.setMajorTickSpacing(10);

        factory.setPaintLabels(true);
        factory.setMajorTickSpacing(10);

        dealer.setPaintLabels(true);
        dealer.setMajorTickSpacing(10);
        body.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // меняем надпись
                int value = ((JSlider) e.getSource()).getValue();
                bodyp.setSpeed(value * 1000);
            }
        });
        engine.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // меняем надпись
                int value = ((JSlider) e.getSource()).getValue();
                enginep.setSpeed(value * 1000);
            }
        });
        accessor.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // меняем надпись
                int value = ((JSlider) e.getSource()).getValue();
                accesories.setSpeed(value * 1000);
            }
        });
        factory.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // меняем надпись
                int value = ((JSlider) e.getSource()).getValue();
                factor.setSpeed(value * 1000);
            }
        });
        dealer.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // меняем надпись
                int value = ((JSlider) e.getSource()).getValue();
                dealers.setSpeed(value * 1000);
            }
        });
        // Размещение ползунков в интерфейсе
        String[] name = {"Speed body provider", "Speed engine provider", "Speed accessories provider", "Factory workers speed", "Dealers speed"};
        GridLayout layout = new GridLayout(3, 5, 1, 1);
        contents.setLayout(layout);
        for (int i = 0; i < 5; i++) {
            contents.add(new Label(name[i]), BorderLayout.CENTER);
        }
        contents.add(body);
        contents.add(engine);
        contents.add(accessor);
        contents.add(factory);
        contents.add(dealer);
        getContentPane().add(contents);
        body_label.setText(String.valueOf(bodyst.size()));
        engine_label.setText(String.valueOf(enginest.size()));
        accessor_label.setText(String.valueOf(accessorst.size()));
        car_storage.setText(String.valueOf(garage.realSize()));
        car_saled.setText(String.valueOf(dealers.getSaled()));
        contents.add(body_label);
        contents.add(engine_label);
        contents.add(accessor_label);
        contents.add(car_storage);
        contents.add(car_saled);
        // getContentPane().add(label, BorderLayout.SOUTH);
        // Вывод окна на экран
        pack();
        setVisible(true);
    }

    public void update() {
        body_label.setText(String.valueOf(bodyst.size()));
        engine_label.setText(String.valueOf(enginest.size()));
        accessor_label.setText(String.valueOf(accessorst.size()));
        car_storage.setText(String.valueOf(garage.realSize()));
        car_saled.setText(String.valueOf(dealers.getSaled()));
    }

}

