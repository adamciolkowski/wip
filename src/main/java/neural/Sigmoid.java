package neural;

import static java.lang.Math.exp;
import static java.lang.Math.floor;

public class Sigmoid {

    static float [] g_sigmoid = new float [200];

    void setupSigmoid() {

        for (int i = 0; i < 200; i++) {
            float x = (i / 20.0f) - 5.0f;
            g_sigmoid[i] = (float) (2.0f / (1.0f + exp(-2.0f * x)) - 1.0f);
        }
    }

    // once the sigmoid has been set up, this function accesses it:
    static float lookupSigmoid(float x) {

        return g_sigmoid[(int) constrain((float) floor((x + 5.0f) * 20.0f), 0f, 199f)];
    }

    public static float constrain(float value, float lower, float upper) {
        if (value > upper)
            return upper;
        if (value < lower)
            return lower;
        return value;
    }
}
