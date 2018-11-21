import static java.lang.Math.exp;

class Neural {

    public static void main(String[] args) {
        new Neural().run();
    }

    private void run() {
        double input0 = 1;
        double expectedOutput0 = 0;

        double input1 = 0.001;
        double expectedOutput1 = 0.999;

        double weight = 0.23462346;
        double currentOutput0 = 0;
        double currentOutput1 = 0;
        for (int i = 0; i < 10; i++) {
            double before = input0 * weight;
            currentOutput0 = before; // TODO

            double before1 = input1 * weight;
            currentOutput1 = sigmoid(before1); // TODO

            double error0 = expectedOutput0 - currentOutput0;
            double error1 = expectedOutput1 - currentOutput1;
            double adjustment0 = error0 * input0 * sigmoidGradient(currentOutput0);
            double adjustment1 = error1 * input1 * sigmoidGradient(currentOutput1);

            System.out.println("error0= " + error0 + ", error1= " + error1);
            System.out.println("adj0= " + adjustment0 + ", adj1= " + adjustment1);

            weight += adjustment0 + adjustment1;
//            System.out.println(weight + ", error0 = " + error0 + ", adj by= " + adjustment);
        }

        System.out.println("output:  " + currentOutput0);
        System.out.println("weight: " + weight);
    }

    private static double sigmoid(double x) {
        return 1 / (1 + exp(-x));
    }

    private static double sigmoidGradient(double x) {
        return x * (1 - x);
    }
}