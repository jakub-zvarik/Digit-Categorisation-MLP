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

    public static double[] weightedSum(double[] input, double[][] weights, int neurons) {
        double[] weightedSums = new double[neurons];

        for (int neuron = 0; neuron < weightedSums.length; neuron++) {
            double sum = 0;
            for (int column = 0; column < input.length; column++) {
                sum += input[column] * weights[column][neuron];
            }
            weightedSums[neuron] = sum;
        }
        return weightedSums;
    }
}
