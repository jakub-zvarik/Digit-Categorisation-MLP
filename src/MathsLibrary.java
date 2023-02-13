import java.util.Random;

public class MathsLibrary {

    public static double[][] generateRandomWeights(int wantedRows, int wantedColumns) {
        Random random = new Random();
        double[][] weights = new double[wantedRows][wantedColumns];

        for (int rows = 0; rows < wantedRows; rows++) {
            for (int columns = 0; columns < wantedColumns; columns++) {
                weights[rows][columns] = random.nextGaussian() * 0.01;
            }
        }
        return weights;
    }

    public static double[] generateRandomBias(int wantedColumns) {
        Random random = new Random();
        double[] bias = new double[wantedColumns];

        for (int column = 0; column < wantedColumns; column++) {
            bias[column] = random.nextGaussian() * 0.01;
        }
        return bias;
    }

    public static double[] weightedSum(double[] input, double[][] weights, int neurons) {
        double[] weightedSums = new double[neurons];
        final int COLS = input.length;

        for (int neuron = 0; neuron < neurons; neuron++) {
            double sum = 0;
            for (int column = 0; column < COLS; column++) {
                sum += input[column] * weights[neuron][column];
            }
            weightedSums[neuron] = sum;
        }
        return weightedSums;
    }

    public static void addBias(double[] input, double[] bias) {
        final int neurons = bias.length;

        for(int column = 0; column < neurons; column++) {
            input[column] = input[column] + bias[column];
        }
    }

    public static double sigmoid(double x) {
        return (1 / (1 + Math.exp(-x)));
    }

    public static void activationFunction(double[] input) {
        final int neurons = input.length;

        for (int column = 0; column < neurons; column++) {
            input[column] = sigmoid(input[column]);
        }
    }


    public static double[] to1DArray(double[][] input) {
        double[] array = new double[input.length];

        for (int row = 0; row < input.length; row++) {
            for (int column = 0; column < 1; column++) {
                array[row] = input[row][column];
            }
        }
        return array;
    }


    // Try softmax ?
    public static double[][] softmax(double[][] input) {
        int rows = input.length;
        int columns = input[0].length;
        double[][] output = new double[rows][columns];
        double sum = 0;

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                sum += Math.exp(input[row][column]);
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                output[row][column] = Math.exp(input[row][column]) / sum;
            }
        }

        return output;
    }
}
