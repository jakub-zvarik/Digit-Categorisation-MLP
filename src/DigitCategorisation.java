import java.io.FileNotFoundException;

// Main app function
public class DigitCategorisation {
    public static void main(String[] args) throws FileNotFoundException {
        String trainingSetFile = "cw2DataSet1.csv";
        String testSetFile = "cw2DataSet2.csv";
        Data data = new Data();

        // Initialise training data set
        int[][] trainingSet = data.sortTo2D(trainingSetFile);

        // Initialise test data set
        int[][] testSet = data.sortTo2D(testSetFile);

        // Euclidean distance classification
        // init of EuclideanDistance
        EuclideanDistance euclideanClassifier = new EuclideanDistance();

        // Print the result for euclidean distance
        System.out.println(euclideanClassifier.euclideanDistance(trainingSet, testSet));

    }
}