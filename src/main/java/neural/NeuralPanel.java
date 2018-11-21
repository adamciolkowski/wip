package neural;

import javax.swing.*;
import java.awt.*;

import static neural.Card.testing_set;
import static neural.Main.cardNum;

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

        g.drawString("neural.Card number: #" + cardNum, width * 0.05f, height * 0.9f);
        g.drawString("neural.Card label: " + testing_set[cardNum].output, width * 0.05f, height * 0.95f);
    }
}
