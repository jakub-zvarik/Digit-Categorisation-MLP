import java.util.Random;

/*
Maths Library is a "support" class containing all maths for euclidean distance
and neural network. By this class I was trying to follow DRY (do not repeat yourself)
but not every part of the neural network is susceptible to follow this rule.
All methods in this class are static so there is no need to initialise this class
to work with the maths.
*/

public class MathsLibrary {

    // Used in Euclidean Distance class

    /*
    Method for euclidean distance calculation
    Method takes in 4 parameters - training set, test set, training set row and test set row
    */
    public static double euclideanDistanceCalculator(double[][] trainingSet, double[][] testSet, int trainingSetRow, int testSetRow) {
        double distance = 0;
        for (int numPointer = 0; numPointer < 64; numPointer++) {
            double difference = trainingSet[trainingSetRow][numPointer] - testSet[testSetRow][numPointer];
            distance += difference * difference;

        }
        distance = (int) Math.sqrt(distance);

        return distance;
    }

    // Used in Neural Network class

    /*
    Random weights generator takes in two parameters - wanted rows and wanted columns
    Method will return 2D array - size is specified by the parameters
    For every column, it generates gaussian number - this means generated numbers will
    be "clustered" around 0 but could be also bigger than 1 or -1. By 0.01 multiplication,
    the number will be considerably smaller - making it better for weights and biases
    in the neural network.
    */
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

    /*
    Method for random biases generation
    Same as the previous method but returning 1D array, which is enough for biases,
    since only one biases per neutron is needed. Same approach with the gaussian
    number is used.
    */
    public static double[] generateRandomBias(int wantedColumns) {
        Random random = new Random();
        double[] bias = new double[wantedColumns];

        for (int column = 0; column < wantedColumns; column++) {
            bias[column] = random.nextGaussian() * 0.01;
        }
        return bias;
    }

    /*
    addBias takes in 2 arrays and adds numbers in the array to numbers in
    another array. Only 1 column + 1 column form another array. This method is
    void because it is only used to change the first array.
    */
    public static void addBias(double[] input, double[] bias) {
        final int neurons = bias.length;

        for(int column = 0; column < neurons; column++) {
            input[column] = input[column] + bias[column];
        }
    }

    /*
    Sigmoid method takes in one double number and returns another double, on which
    sigmoid function is performed. Sigmoid function makes every input number between
    -1 and 1.
    */
    public static double sigmoid(double x) {
        return (1 / (1 + Math.exp(-x)));
    }

    /*
    Method activationFunction takes in 1 parameter - input array and performs
    sigmoid function on every element in the input array. In neural network,
    this is used as part of the feedforward algorithm to normalise data.
    */
    public static void activationFunction(double[] input) {
        final int neurons = input.length;

        for (int column = 0; column < neurons; column++) {
            input[column] = sigmoid(input[column]);
        }
    }

    /*
    Method weightedSum takes in 3 parameters - input array, 2D array with weights
    and number of neurons and returns 1 double array. Parameter neurons is used
    to define number of columns in the returning array. This method is performing
    dot product on input array and weights array - meaning calculating all weights
    pointing to a neuron. Returned array is array of these weighted sums with the
    length corresponding to number of neurons in the layer following input.
    */
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

    /*
    Method feedforwardAlgorithm takes in 4 parameters - input array, weights (2D array),
    number of neurons and array of biases and return array of doubles. It is an algorithm
    needed for feedforward process in neural networks. It performs weightedSums, adds biases
    on the weighted sums and perform activation function for every element in the array.
    (for reference of these methods, see previous methods)
    */
    public static double[] feedforwardAlgorithm(double[] input, double[][] weights, int neurons, double[] bias) {
        double[] weightedSum = weightedSum(input, weights, neurons);
        addBias(weightedSum, bias);
        activationFunction(weightedSum);

        return weightedSum;
    }
}
