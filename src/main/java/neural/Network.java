package neural;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.text.NumberFormat;

import static java.lang.Math.sqrt;

class Network {

    private final Neuron[] input_layer;
    private final Neuron[] hidden_layer;
    private final Neuron[] output_layer;
    int bestIndex = 0;

    Network(int inputs, int hidden, int outputs) {
        input_layer = new Neuron[inputs];
        hidden_layer = new Neuron[hidden];
        output_layer = new Neuron[outputs];

        for (int i = 0; i < input_layer.length; i++) {
            input_layer[i] = new Neuron();
        }

        for (int j = 0; j < hidden_layer.length; j++) {
            hidden_layer[j] = new Neuron(input_layer);
        }

        for (int k = 0; k < output_layer.length; k++) {
            output_layer[k] = new Neuron(hidden_layer);
        }
    }

    void respond(Card card) {
        for (int i = 0; i < input_layer.length; i++) {
            input_layer[i].output = card.inputs[i];
        }
        // now feed forward through the hidden layer
        for (int j = 0; j < hidden_layer.length; j++) {
            hidden_layer[j].respond();
        }
        for (int k = 0; k < output_layer.length; k++) {
            output_layer[k].respond();
        }
    }

    void display(Graphics2D g) {
        Rectangle bounds = g.getClipBounds();
        int width = bounds.width;
        int height = bounds.height;

        // Draw the input layer
        for (int i = 0; i < input_layer.length; i++) {
            AffineTransform at = g.getTransform();
            g.translate(
                    (i % 14) * height / 20.0 + width * 0.05,
                    (i / 14) * height / 20.0 + height * 0.15);
            input_layer[i].display(g);
            g.setTransform(at);
        }

        // Draw the hidden layer
        for (int j = 0; j < hidden_layer.length; j++) {
            AffineTransform at = g.getTransform();
            g.translate(
                    (j % 7) * height / 20.0 + width * 0.53,
                    (j / 7) * height / 20.0 + height * 0.32);
            hidden_layer[j].display(g);
            g.setTransform(at);
        }

        // Draw the output layer
        float [] resp = new float [output_layer.length];
        float respTotal = 0.0f;
        for (int k = 0; k < output_layer.length; k++) {
            resp[k] = output_layer[k].output;
            respTotal += resp[k]+1;
        }

        for (int k = 0; k < output_layer.length; k++) {
            AffineTransform at = g.getTransform();
            g.translate(
                    width * 0.85,
                    (k%10) * height / 15.0 + height * 0.2);
            output_layer[k].display(g);
            g.setStroke(new BasicStroke((float) (sqrt(output_layer[k].output)/2)));
            g.drawLine(12, 0, 25, 0);
            g.drawString(String.valueOf(k%10), 40, 5);
            float v = ((output_layer[k].output + 1) / respTotal) * 100;
            g.drawString(nfc(v) + "%", 55, 5);
            g.setTransform(at);
            g.setStroke(new BasicStroke(1));
        }
        float best = -1.0f;
        for(int i =0; i < resp.length; i++){
            if(resp[i]>best){
                best = resp[i];
                bestIndex = i;
            }
        }
        g.setColor(Color.RED);
        g.draw(new Ellipse2D.Float(
                width * 0.85f, (bestIndex%10) * height / 15.0f + height * 0.2f,
                25f, 25f));
    }

    void train(float [] outputs) {
        // adjust the output layer
        for (int k = 0; k < output_layer.length; k++) {
            output_layer[k].setError(outputs[k]);
            output_layer[k].train();
        }
        float best = -1.0f;
        for (int i = 0; i < output_layer.length; i++) {
            if (output_layer[i].output > best) bestIndex = i;
        }
        // propagate back to the hidden layer
        for (int j = 0; j < hidden_layer.length; j++) {
            hidden_layer[j].train();
        }

        // The input layer doesn't learn: it is the input and only that
    }

    private static final NumberFormat format = NumberFormat.getNumberInstance();

    private static String nfc(float val) {
        format.setMaximumFractionDigits(2);
        return format.format(val);
    }
}