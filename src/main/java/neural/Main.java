package neural;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static neural.Card.testing_set;

public class Main {

    public static void main(String[] args) {
        new Main().setup();
    }

    private final static Random random = new Random();

    static int cardNum = random.nextInt(2000);

    private Network neuralnet;

    private void setup() {
        Card.loadData();
        neuralnet = new Network(196);
        neuralnet.respond(testing_set[cardNum]);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setSize(1000, 400);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            NeuralPanel contentPane = new NeuralPanel(neuralnet);
            contentPane.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    cardNum = random.nextInt(2000);
                }
            });
            frame.setContentPane(contentPane);
        });
    }
}
