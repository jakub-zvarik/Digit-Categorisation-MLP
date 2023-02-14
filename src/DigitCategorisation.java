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
        NeuralNetwork neural = new NeuralNetwork(65, 94, 0.01, 2000);
        neural.train(trainingSet);
        neural.test(testSet);


        /*// Generate Random Weights
        double[][] weights = MathsLibrary.generateRandomMatrix(40,64);

        // Calculate dot product
        double[][] test1 = MathsLibrary.dotProduct(trainingSet[0], weights);

        System.out.println(Arrays.deepToString(test1));
        System.out.println(test1.length);*/

    }
}