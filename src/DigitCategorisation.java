import java.io.FileNotFoundException;
import java.util.Arrays;

// Main app function
public class DigitCategorisation {
    public static void main(String[] args) throws FileNotFoundException {
        String trainingDataSet = "cw2DataSet1.csv";
        String testDataSet = "cw2DataSet2.csv";

        // Initialise training data set
        double[][] trainingSet = Data.sortData(trainingDataSet);

        // Initialise test data set
        double[][] testSet = Data.sortData(testDataSet);

        // Euclidean distance classification
        // init of EuclideanDistance
        EuclideanDistance euclideanClassifier = new EuclideanDistance();

        // Print the result for euclidean distance
        NeuralNetwork neural = new NeuralNetwork(65, 96, 0.02, 100);
        neural.train(trainingSet);
        neural.test(testSet);
    }
}