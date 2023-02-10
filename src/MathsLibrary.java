import java.util.Random;

public class MathsLibrary {

    public static double[][] scalarAdd(double[][] inputs, int number) {
        for (int row = 0; row < inputs.length; row++) {
            for (int column = 0; column < 64; column++) {
                inputs[row][column] += number;
            }
        }
        return inputs;
    }

    public static double[][] scalarMultiply(double[][] inputs, int number) {
        for (int row = 0; row < inputs.length; row++) {
            for (int column = 0; column < 64; column++) {
                inputs[row][column] *= number;
            }
        }
        return inputs;
    }

    public static double[][] elementWiseAdd(double[][] inputs, double weight) {
        for (int row = 0; row < inputs.length; row++) {
            for (int column = 0; column < 64; column++) {
                inputs[row][column] += weight;
            }
        }
        return inputs;
    }

    public static double[][] elementWiseMultiply(double[][] inputs, double weight) {
        for (int row = 0; row < inputs.length; row++) {
            for (int column = 0; column < 64; column++) {
                inputs[row][column] *= weight;
            }
        }
        return inputs;
    }

    public static double[][] generateRandomWeights(int neuronsInHiddenLayer, int numberOfInputs) {
        Random random = new Random();
        double[][] weights = new double[neuronsInHiddenLayer][numberOfInputs];

        for (int rows = 0; rows < neuronsInHiddenLayer; rows++) {
            for (int columns = 0; columns < numberOfInputs; columns++) {
                weights[rows][columns] = random.nextDouble()*100;
            }
        }

        return weights;
    }

    public static double[][] dotProduct(double[] inputs, double[][] weights) {
        int rows = weights.length;
        int columns = inputs.length-1;
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

}
