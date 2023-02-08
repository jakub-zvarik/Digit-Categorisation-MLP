import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Data {

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
