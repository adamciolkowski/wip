package neural;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import static neural.Sigmoid.lookupSigmoid;

class Neuron {

    private static final float LEARNING_RATE = 0.01f;

    private static final Ellipse2D.Float shape = new Ellipse2D.Float(0, 0, 16, 16);
    private static final Random random = new Random();

    Neuron[] inputs; // Strores the neurons from the previous layer
    float[] weights;
    float output;
    float error;

    Neuron() {
        error = 0.0f;
    }

    Neuron(Neuron[] p_inputs) {
        inputs = new Neuron[p_inputs.length];
        weights = new float[p_inputs.length];
        error = 0.0f;
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = p_inputs[i];
            weights[i] = random(-1.0f, 1.0f);
        }
    }

    void respond() {
        float input = 0.0f;
        for (int i = 0; i < inputs.length; i++) {
            input += inputs[i].output * weights[i];
        }
        output = lookupSigmoid(input);
        error = 0.0f;
    }

    void setError(float desired) {
        error = desired - output;
    }

    void train() {
        float delta = (1.0f - output) * (1.0f + output) *
                error * LEARNING_RATE;
        for (int i = 0; i < inputs.length; i++) {
            inputs[i].error += weights[i] * error;
            weights[i] += inputs[i].output * delta;
        }
    }

    void display(Graphics2D g) {
        g.setColor(Color.getHSBColor(0, 0, 1 - output));
        g.fill(shape);
        g.setColor(Color.BLACK);
        g.draw(shape);
    }

    private static float random(float lower, float upper) {
        float range = upper - lower;
        return lower + random.nextFloat() * range;
    }
}
