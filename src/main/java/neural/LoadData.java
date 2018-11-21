package neural;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

class Card { // This class contains all the functions to format and save the data

    static Card[] testing_set; // the set we use to train (2000)
    static Card[] training_set; // the set we use to train (8000)

    float[] inputs;
    float[] outputs;
    int output;

    Card() {
        inputs = new float[196]; // the images are a grid of 14x14 pixels which makes for a total of 196
        outputs = new float[10]; // the number of possible outputs; from 0 to 9
    }

    void imageLoad(byte[] images, int offset) { // Images is an array of 1,960,000 bytes, each one representing a pixel (0-255) of the 10,000 * 14x14 (196) images
        // We know one image consists of 196 bytes so the location is: offset*196
        for (int i = 0; i < 196; i++) {
            byte image = images[i + offset];
            float value = (image + 128) / 128.0f - 1f;
//            System.out.println(value);
            inputs[i] = value; // We then store each pixel in the array inputs[] after converting it from (0 - 255) to (+1 - -1) as they vary on the greyscale
        }
//        System.exit(0);
    }

    void labelLoad(byte[] labels, int offset) {  // Labels is an array of 10,000 bytes, each representing the answer of each image

        output = labels[offset];

        for (int i = 0; i < 10; i++) {  // We then set the correct index in output[] to +1 if it corresponds to the ouput and -1 if not
            if (i == output) {
                outputs[i] = 1.0f;
            } else {
                outputs[i] = -1.0f;
            }
        }
    }

    static void loadData() { // In this function we initialise all out data in two seperate arrays, training[] and test[]
        byte[] images = loadBytes("t10k-images-14x14.idx3-ubyte");
        byte[] labels = loadBytes("t10k-labels.idx1-ubyte");
        training_set = new Card[8000];
        int tr_pos = 0;
        testing_set = new Card[2000];
        int te_pos = 0;
        for (int i = 0; i < 10000; i++) {
            if (i % 5 != 0) {
                training_set[tr_pos] = new Card();
                training_set[tr_pos].imageLoad(images, 16 + i * 196); // There is an offset of 16 bytes
                training_set[tr_pos].labelLoad(labels, 8 + i);  // There is an offset of 8 bytes
                tr_pos++;
            } else {
                testing_set[te_pos] = new Card();
                testing_set[te_pos].imageLoad(images, 16 + i * 196);  // There is an offset of 16 bytes
                testing_set[te_pos].labelLoad(labels, 8 + i);  // There is an offset of 8 bytes
                te_pos++;
            }
        }
    }

    private static byte[] loadBytes(String path) {
        try {
            URL resource = Card.class.getResource("/" + path);
            return Files.readAllBytes(Paths.get(resource.toURI())); // TODO inefficient loading
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}