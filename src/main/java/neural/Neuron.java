package neural;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

class Neuron {

    private static final Ellipse2D.Float shape = new Ellipse2D.Float(0, 0, 16, 16);

    float m_output;

    void display(Graphics2D g) {
        float value = 1 - m_output / 2f;
        g.setColor(Color.getHSBColor(0, 0, value));
        g.fill(shape);
        g.setColor(Color.BLACK);
        g.draw(shape);
    }
}
