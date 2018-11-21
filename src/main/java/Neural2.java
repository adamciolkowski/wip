import static java.lang.Math.exp;

class Neural2 {

    public static void main(String[] args) {
        new Neural2().run();
    }

    private void run() {
//        double input0 = 1;
//        double expectedOutput0 = 0;

        double input1 = 0.001;
        double expectedOutput1 = 0.999;

        double weight = 0.23462346;
//        double currentOutput0 = 0;
//        double currentOutput1 = 0;


        for (int i = 0; i < 10000; i++) {
            double actualOutput = input1 * weight;
            System.out.println("actualOutput: " + actualOutput);
            double error = expectedOutput1 - actualOutput;
            System.out.println("error: " + error);
            double delta = (1.0 - actualOutput) * (1.0 + actualOutput) * error * 0.1;
            System.out.println("delta: " + delta);

            double inputError = weight * error;
            double adj = input1 * delta;
            System.out.println("adj: " + adj);
            weight += adj;
        }

        System.out.println("weight: " + weight);
    }

    private static double sigmoid(double x) {
        return 1 / (1 + exp(-x));
    }

    private static double sigmoidGradient(double x) {
        return x * (1 - x);
    }
}