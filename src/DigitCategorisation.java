import java.io.FileNotFoundException;
import java.util.Arrays;

// Main app function
public class DigitCategorisation {
    public static void main(String[] args) throws FileNotFoundException {
        String trainingDataSet = "cw2DataSet1.csv";
        String testDataSet = "cw2DataSet2.csv";
        Data data = new Data();

        // Initialise training data set
        double[][] trainingSet = data.sortData(trainingDataSet);

        // Initialise test data set
        double[][] testSet = data.sortData(testDataSet);

        // Euclidean distance classification
        // init of EuclideanDistance
        EuclideanDistance euclideanClassifier = new EuclideanDistance();

        // Print the result for euclidean distance
        System.out.println(euclideanClassifier.euclideanDistance(trainingSet, testSet));

        // Generate Random Weights
        double[][] weights = MathsLibrary.generateRandomWeights(40,64);

        // Calculate dot product
        double[][] test1 = MathsLibrary.dotProduct(trainingSet[0], weights);

        System.out.println(Arrays.deepToString(test1));

    }
}