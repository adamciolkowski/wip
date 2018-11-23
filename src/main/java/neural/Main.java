package neural;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static neural.Card.testing_set;
import static neural.Card.training_set;

public class Main {

    public static void main(String[] args) {
        new Main().setup();
    }

    private final static Random random = new Random();

    static int totalTrain = 0;
    static int totalTest = 0;
    static int totalRight = 0;
    static float sucess = 0;
    static int testCard = 0;
    static int trainCard = 0;

    private Network neuralnet;
    private JButton trainB, testB;

    private NeuralPanel contentPane;

    private void setup() {
        Card.loadData();
        Sigmoid.setupSigmoid();
        neuralnet = new Network(196, 49, 10);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setSize(1000, 400);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            JPanel panel = new JPanel(new BorderLayout());
            contentPane = new NeuralPanel(neuralnet);
            panel.add(contentPane, BorderLayout.CENTER);
            JPanel buttons = new JPanel();
            trainB = new JButton("Train");
            trainB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doTrain();
                    SwingUtilities.invokeLater(() -> contentPane.repaint());
                }
            });
            buttons.add(trainB);
            testB = new JButton("Test");
            testB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doTest();
                    SwingUtilities.invokeLater(() -> contentPane.repaint());
                }
            });
            buttons.add(testB);
            panel.add(buttons, BorderLayout.SOUTH);
            frame.setContentPane(panel);
        });
    }

    private void doTest() {
        testCard = random.nextInt(testing_set.length);
        neuralnet.respond(testing_set[testCard]);

        System.out.println(neuralnet.bestIndex);
        if (neuralnet.bestIndex == testing_set[testCard].output) totalRight++;
        System.out.println(testCard + "  " + neuralnet.bestIndex + "  " + testing_set[testCard].output);
        totalTest++;
    }

    private void doTrain() {
        for (int i = 0; i < 500; i++) {
            trainCard = random.nextInt(training_set.length);
            neuralnet.respond(training_set[trainCard]);
            neuralnet.train(training_set[trainCard].outputs);
            totalTrain++;
        }
    }
}
