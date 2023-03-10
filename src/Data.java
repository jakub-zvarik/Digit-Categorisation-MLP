import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//The data class does read the data from CSV files and transforms them into 2D arrays.
public class Data {
    /*
    sortData calls on the readInData method from this class to read the data from
    CSV file and sort them into a 2D array with 65 columns (64 values representing the
    number and the 65th column is the label - the number the previous values are representing
    in the 8x8 image). Returns sorted data in a 2D array of doubles.
    */
    public static double[][] sortData(String dataSet) throws FileNotFoundException {
        // Saves data in the list of doubles
        List<Double> temporary = readInData(dataSet);
        final int COLUMNS = 65;
        final int ROWS = temporary.size() / COLUMNS;
        double[][] sortedData = new double[ROWS][COLUMNS];
        // // Index points to index in the temporary list
        int INDEX = 0;
        // Loops which iterate through all rows and their columns in the 2D array.
        for (int linePointer = 0; linePointer < sortedData.length; linePointer++) {
            for (int numberPointer = 0; numberPointer < COLUMNS; numberPointer++){
                // Adding the number into array
                sortedData[linePointer][numberPointer] = temporary.get(INDEX);
                // Index increased
                INDEX += 1;
            }
        }
        return sortedData;
    }

    /*
    Method readInData takes in one parameter - the name of the dataset. It does read data
    from the CSV file. Data are separated by a comma, so the data is using comma as a delimiter
    to split the data and therefore read in only the numbers without commas and transform them
    from string datatype into double. Returns list of doubles. By using a list, it is ensured
    that the method can be used on datasets with different lengths.
    */
    private static List<Double> readInData(String dataSetName) throws FileNotFoundException {
        // Gets an absolut path of the dataset
        String path = new File(dataSetName).getAbsolutePath();
        // Get access to the file
        File coordinatesTxtFile = new File(path);
        // Scanner to scan file content
        Scanner scan = new Scanner(coordinatesTxtFile);
        List<Double> temporaryList = new ArrayList<>();
        // Loop to scan contains of the file until the last line
        while (scan.hasNextLine()) {
            // Splitting the data with comma
            String[] numbers = scan.nextLine().split(",");
            for (String number : numbers) {
                // Parsing the data from string to double and adding them to the list
                double num = Double.parseDouble(number);
                temporaryList.add(num);
            }
        }
        return temporaryList;
    }
}
