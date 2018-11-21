package neural;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import static neural.Sigmoid.lookupSigmoid;

class Neuron {

    private static final Ellipse2D.Float shape = new Ellipse2D.Float(0, 0, 16, 16);
    private static final Random random = new Random();

    Neuron[] inputs; // Strores the neurons from the previous layer
    float[] weights;
    float output;

    Neuron() { }

    Neuron(Neuron[] p_inputs) {
        inputs = new Neuron[p_inputs.length];
        weights = new float[p_inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = p_inputs[i];
            weights[i] = random(-1.0f, 1.0f);
        }
    }

    private static float random(float lower, float upper) {
        float range = upper - lower;
        return lower + random.nextFloat() * range;
    }

    void respond() {
        float input = 0.0f;
        for (int i = 0; i < inputs.length; i++) {
            input += inputs[i].output * weights[i];
        }
        output = lookupSigmoid(input);
    }

    void display(Graphics2D g) {
        float value = 1 - output / 2f;
        g.setColor(Color.getHSBColor(0, 0, value));
        g.fill(shape);
        g.setColor(Color.BLACK);
        g.draw(shape);
    }
}
