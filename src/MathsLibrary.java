import java.util.Random;

/*
Maths Library is a "support" class containing all maths used in euclidean distance
and neural networks classes.
*/

public class MathsLibrary {

    // Methods used in Euclidean Distance class

    /*
    Method for euclidean distance calculation
    The method takes in 2 parameters - coordinates of 2 numbers from the first and second
    fold and performs the calculation of Euclidean distance between these two points.
    */
    public static double euclideanDistanceCalculator(double[] firstFold, double[] secondFold) {
        double distance = 0;
        for (int numPointer = 0; numPointer < 64; numPointer++) {
            double difference = firstFold[numPointer] - secondFold[numPointer];
            distance += difference * difference;

        }
        distance = Math.sqrt(distance);

        return distance;
    }

    // Methods used in Neural Network class

    /*
    Random weights generator takes in two parameters - wanted rows and wanted columns
    The method will return 2D array - size is specified by the parameters
    For every column, it generates a gaussian number - this means generated numbers will
    be "clustered" around 0 but could be also bigger than 1 or -1. By 0.01 multiplication,
    the number will be considerably smaller - making it better for weights and biases
    in the neural network.
    */
    public static double[][] generateRandomWeights(int wantedRows, int wantedColumns) {
        Random random = new Random();
        double[][] weights = new double[wantedRows][wantedColumns];
        // Filling the columns with the random gaussian weights
        for (int rows = 0; rows < wantedRows; rows++) {
            for (int columns = 0; columns < wantedColumns; columns++) {
                weights[rows][columns] = random.nextGaussian() * 0.01;
            }
        }
        return weights;
    }

    /*
    Method for random biases generation
    Same as the previous method but returns a 1D array, which is enough for biases,
    since only one bias per neutron is needed. The same approach with the gaussian
    number is used.
    */
    public static double[] generateRandomBias(int wantedColumns) {
        Random random = new Random();
        double[] bias = new double[wantedColumns];
        // Filling the columns with the random gaussian biases
        for (int column = 0; column < wantedColumns; column++) {
            bias[column] = random.nextGaussian() * 0.01;
        }
        return bias;
    }

    /*
    Method weightedSum takes in 3 parameters and return array of doubles. Parameter neurons are used
    to define the number of columns in the returning array. This method is performing
    dot product on input array and weights array - meaning calculating all weights
    pointing to a neuron. The returned array is the array of these weighted sums with the
    length corresponding to the number of neurons in the layer following input.
    */
    public static double[] weightedSum(double[] input, double[][] weights, int neurons) {
        double[] weightedSums = new double[neurons];
        // Calculation of weighted sums for every neuron in the next layer
        for (int neuron = 0; neuron < weightedSums.length; neuron++) {
            double sum = 0;
            for (int inputPointer = 0; inputPointer < input.length -1; inputPointer++) {
                sum += input[inputPointer] * weights[inputPointer][neuron];
            }
            weightedSums[neuron] = sum;
        }
        return weightedSums;
    }


    /*
    The addBias method takes in 2 arrays and adds numbers in the array to numbers in
    another array. Only 1 column + 1 column form another array. This method is
    void because it is only used to change the first array.
    */
    public static void addBias(double[] input, double[] bias) {
        final int neurons = bias.length;

        for (int column = 0; column < neurons; column++) {
            input[column] = input[column] + bias[column];
        }
    }

    /*
    The sigmoid method takes in one double number and returns another double, on which
    the sigmoid function is performed. The sigmoid function makes every input number between
    -1 and 1.
    */
    public static double sigmoid(double x) {
        return (1 / (1 + Math.exp(-x)));
    }

    /*
    Method activationFunction takes in 1 parameter - input array and performs
    the sigmoid function on every element in the input array. In a neural network,
    this is used as part of the feedforward algorithm to normalise data.
    */
    public static void activationFunction(double[] input) {
        final int neurons = input.length;
        // Performing sigmoid function for every column in the array
        for (int column = 0; column < neurons; column++) {
            input[column] = sigmoid(input[column]);
        }
    }
}
