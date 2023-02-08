import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        Classifier classifier = new Classifier();

        int rulings = classifier.euclideanDistance(trainingSet, testSet);

        System.out.println(rulings);

    }
}

class Classifier {

    public int euclideanDistance(int[][] trainingSet, int[][] testSet) {
        double distance;
        int[] closestFoundArray;
        double closestDistance;
        int correctRulings = 0;

        for (int trainingSetRow = 0; trainingSetRow < trainingSet.length; trainingSetRow++) {
                closestFoundArray = new int[65];
                closestDistance = 0;

            for (int testSetRow = 0; testSetRow < testSet.length; testSetRow++) {

                distance = euclideanDistanceCalculator(trainingSet, testSet, trainingSetRow, testSetRow);

                if (closestDistance == 0 || distance < closestDistance) {
                    closestDistance = distance;
                    closestFoundArray = testSet[testSetRow];
                }
            }

            if (trainingSet[trainingSetRow][64] == closestFoundArray[64]) {
                correctRulings += 1;
            }

        }

        return correctRulings;
    }

    private static double euclideanDistanceCalculator(int[][] trainingSet, int[][] testSet, int trainingSetRow, int testSetRow) {
        double distance = 0;
        for (int numPointer = 0; numPointer < 64; numPointer++) {
            double difference = trainingSet[trainingSetRow][numPointer] - testSet[testSetRow][numPointer];
            distance += difference * difference;

        }
        distance = (int) Math.sqrt(distance);

        return distance;
    }

}

class Data {

    public int[][] sortTo2D(String dataSet) throws FileNotFoundException {
        List<Integer> temporary = readInData(dataSet);
        int COLUMNS = 65;
        int ROWS = temporary.size() / COLUMNS;
        int[][] sortedData = new int[ROWS][COLUMNS];
        int NUMBER = 0;

        for (int linePointer = 0; linePointer < sortedData.length; linePointer++) {
            for (int numberPointer = 0; numberPointer < 65; numberPointer++){
                sortedData[linePointer][numberPointer] = temporary.get(NUMBER);
                NUMBER += 1;
            }
        }

        return sortedData;
    }

    private static List<Integer> readInData(String dataSetName) throws FileNotFoundException {
        String path = new File(dataSetName).getAbsolutePath();
        File coordinatesTxtFile = new File(path);
        Scanner scan = new Scanner(coordinatesTxtFile);
        List<Integer> temporaryList = new ArrayList<>();

        while (scan.hasNextLine()) {
            String[] numbers = scan.nextLine().split(",");
            for (String number : numbers) {
                int num = Integer.parseInt(number);
                temporaryList.add(num);
            }
        }

        return temporaryList;
    }
}
