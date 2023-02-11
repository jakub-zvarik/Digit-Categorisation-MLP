import java.util.Random;

public class MathsLibrary {

    public static double[][] scalarAdd(double[][] input, double[][] bias) {
        for (int row = 0; row < input.length; row++) {
            for (int column = 0; column < input[0].length; column++) {
                input[row][column] += bias[row][column];
            }
        }
        return input;
    }

    public static double[][] generateRandomMatrix(int wantedRows, int wantedColumns) {
        Random random = new Random();
        double[][] weights = new double[wantedRows][wantedColumns];

        for (int rows = 0; rows < wantedRows; rows++) {
            for (int columns = 0; columns < wantedColumns; columns++) {
                weights[rows][columns] = random.nextDouble()* 2 - 1;
            }
        }
        return weights;
    }

    public static double[][] dotProduct(double[] inputs, double[][] weights) {
        int rows = weights.length;
        int columns = inputs.length;
        double[][] weightedSums = new double[rows][1];

        for (int row = 0; row < rows; row++) {
            double sum = 0;
            for (int column = 0; column < columns; column++) {
                for (int chooseWeight = 0; chooseWeight < rows; chooseWeight++) {
                    sum += inputs[column] * weights[row][chooseWeight];
                }
                weightedSums[row][0] = sum;
            }
        }
        return weightedSums;
    }

    public static double[] to1DArray(double[][] input) {
        double[] array = new double[input.length];

        for (int row = 0; row < input.length; row++) {
            for (int column = 0; column < 1; column++ ) {
                array[row] = input[row][column];
            }
        }
        return array;
    }

    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static double[][] activationFunction(double[][] inputs) {
        for (int row = 0; row < inputs.length; row++) {
            for (int column = 0; column < 1; column++) {
                inputs[row][column] = sigmoid(inputs[row][column]);
            }
        }
        return inputs;
    }
}
