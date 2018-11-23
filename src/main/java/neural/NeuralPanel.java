package neural;

import javax.swing.*;
import java.awt.*;

import static neural.Card.testing_set;
import static neural.Main.*;

public class NeuralPanel extends JPanel {

    private final Network network;

    NeuralPanel(Network network) {
        this.network = network;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw((Graphics2D) g);
    }

    private void draw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Rectangle bounds = g.getClipBounds();
        int width = bounds.width;
        int height = bounds.height;

        network.display(g);

        g.setColor(Color.BLACK);
        g.drawString("Test card: #" + testCard, width * 0.18f, height * 0.89f);
        g.drawString("Train card: " + trainCard, width * 0.18f, height * 0.93f);

        g.drawString("Total train: " + totalTrain, width * 0.32f, height * 0.89f);
        g.drawString("Total test: " + totalTest, width * 0.32f, height * 0.93f);

        if (totalTest > 0) sucess = (float) totalRight / totalTest;
        g.drawString("Success rate: " + sucess, width * 0.44f, height * 0.89f);
        g.drawString("Card label: " + testing_set[testCard].output, width * 0.44f, height * 0.93f);
    }
}
