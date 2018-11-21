import javax.swing.*;

public class Part4 {

    public static void main(String[] args) {
        new Part4().setup();
    }

    int totalTrain = 0;
    int totalTest = 0;
    int totalRight = 0;
    float sucess = 0;
    int testCard = 0;
    int trainCard = 0;

    Network neuralnet;
//    Button trainB, testB;

    void setup() {
        JFrame frame = new JFrame();
        frame.setSize(1000, 400);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
//        setupSigmoid();
        Card.loadData();
        neuralnet = new Network(196, 49, 10);
//        smooth(); // TODO antialias?
//        stroke(150);

//        trainB = new Button(width*0.06, height*0.9, "Train");
//        testB = new Button(width*0.11, height*0.9, "Test");
    }

    void draw() {

//        background(255);
//        neuralnet.display();
//
//        fill(100);
//        text("Test card: #" + testCard, width*0.18, height*0.89);
//        text("Train card: " + trainCard, width*0.18, height*0.93);
//
//        text("Total train: " + totalTrain, width*0.32, height*0.89);
//        text("Total test: " + totalTest, width*0.32, height*0.93);
//
//        if(totalTest>0) sucess = float(totalRight)/float(totalTest);
//        text("Success rate: " + nfc(sucess, 2), width*0.44, height*0.89);
//        text("Card label: " + testing_set[testCard].output, width*0.44, height*0.93);
//
//        trainB.display();
//        testB.display();
    }

    void mousePressed() {
//        if (trainB.hover()) {
        doTrain();
//        } else if (testB.hover()){
//            testCard = (int) floor(random(0, testing_set.length));
//            neuralnet.respond(testing_set[testCard]);
//            neuralnet.display();
//            if(neuralnet.bestIndex == testing_set[testCard].output) totalRight ++;
//            totalTest ++;
//        }
//        redraw();
    }

    private void doTrain() {
        for (int i = 0; i < 500; i++) {
            trainCard = (int) floor(random(0, training_set.length));
            neuralnet.respond(training_set[trainCard]);
            neuralnet.train(training_set[trainCard].outputs);
            totalTrain++;
        }
    }
}
