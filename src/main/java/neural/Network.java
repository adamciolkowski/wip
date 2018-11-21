package neural;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

class Network {

    private final Neuron[] m_input_layer;

    Network(int inputs) {
        m_input_layer = new Neuron[inputs];
        for (int i = 0; i < m_input_layer.length; i++) {
            m_input_layer[i] = new Neuron();
        }
    }

    void respond(Card card) {
        for (int i = 0; i < m_input_layer.length; i++) {
            m_input_layer[i].m_output = card.inputs[i];
        }
    }

    void display(Graphics2D g) {
        Rectangle bounds = g.getClipBounds();
        int width = bounds.width;
        int height = bounds.height;
        for (int i = 0; i < m_input_layer.length; i++) {
            AffineTransform at = g.getTransform();
            g.translate((i % 14) * height / 20.0 + width * 0.06, (i / 14) * height / 20.0 + height * 0.15);
            m_input_layer[i].display(g);
            g.setTransform(at);
        }
    }
}
