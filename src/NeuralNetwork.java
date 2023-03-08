/*
The neural network class creates a neural network with a custom number of inputs, one hidden layer
containing a custom number of neurons and an output layer with a custom number of neurons. The initialisation,
feedforward process, backpropagation, and training and testing methods are created within this class.
*/

public class NeuralNetwork {
    // Class variables
    private final int inputs;
    private final int hiddenNeurons;
    private final int outputNeurons=10;
    private final double learningRate;
    private final int epochs;
    private final double[][] hiddenWeights;
    private final double[] hiddenBias;
    private final double[][] outputWeights;
    private final double[] outputBias;

    /*
    Constructor method - takes in 4 parameters These parameters are then assigned to class variables
    and used all across this class. Also initialises weights to both, hidden and output layers,
    and biases for hidden and the output layer.
    */
    public NeuralNetwork(int numberOfInputs, int numberOfHiddenNeurons, double learningRate, int numberOfEpochs) {
        // Initialise all variables
        this.inputs = numberOfInputs;
        this.hiddenNeurons = numberOfHiddenNeurons;
        this.learningRate = learningRate;
        this.epochs = numberOfEpochs;
        // Initialise and generate random weights
        this.hiddenWeights = MathsLibrary.generateRandomWeights(inputs, hiddenNeurons);
        this.outputWeights = MathsLibrary.generateRandomWeights(hiddenNeurons, outputNeurons);
        // Initialise and generate random biases
        this.hiddenBias = MathsLibrary.generateRandomBias(hiddenNeurons);
        this.outputBias = MathsLibrary.generateRandomBias(outputNeurons);
    }

    // Feedforward algorithms

    /*
    Method feedforwardAlgorithm takes in 4 parameters. It is an algorithm needed for the complete
    feedforward process in neural networks. It calculates weighted sums, adds biases on the weighted
    sums and performs an activation function for every element in the array.
    (for reference of these methods, see MathsLibrary)
    */
    public static double[] feedforwardAlgorithm(double[] input, double[][] weights, int neurons, double[] bias) {
        double[] weightedSum = MathsLibrary.weightedSum(input, weights, neurons);
        MathsLibrary.addBias(weightedSum, bias);
        MathsLibrary.activationFunction(weightedSum);

        return weightedSum;
    }

    /*
    Method feedforward takes in one parameter. It feeds data in the forward direction in the
    neural network (from the input layer to the output layer), performing all necessary calculation.
    For more details, see feedforwardAlgorithm above.
    */
    private double[] feedforward(double[] input) {
        // Feedforward process from input to the hidden layer
        double[] weightedSumHidden = feedforwardAlgorithm(input, this.hiddenWeights, this.hiddenNeurons, this.hiddenBias);
        // Return result of feedforward process from hidden to the output layer
        return feedforwardAlgorithm(weightedSumHidden, this.outputWeights, this.outputNeurons, this.outputBias);
    }

    // Backpropagation algorithms

    /*
    Method backpropagationOutputHidden takes in 3 parameters and returns an array of doubles.
    This method calculates errors made between the output and hidden layers and is based on
    the result, it changes the values of the weights and biases between the hidden and output layer.
    */
    private double[] backpropagationOutputHidden(double[] hiddenOutputs, double[] outputs, double[] target) {
        double[] errorsInOutput = new double[this.outputNeurons];
        // Calculation of the error between hidden and output layer
        for (int outputNeuron = 0; outputNeuron < this.outputNeurons; outputNeuron++) {
            double error = outputs[outputNeuron] * (1 - outputs[outputNeuron]) * (target[outputNeuron] - outputs[outputNeuron]);
            errorsInOutput[outputNeuron] = error;
            for (int column = 0; column < this.hiddenNeurons; column++) {
                // Update the weights pointing to output layer
                this.outputWeights[column][outputNeuron] += this.learningRate * error * hiddenOutputs[column];
            }
            // Update the output bias
            this.outputBias[outputNeuron] += this.learningRate * error;
        }
        return errorsInOutput;
    }

    /*
    Method backpropagationHiddenInput takes in 3 parameters and is void.
    This method calculates errors made between the hidden and input layers and
    changes the values based on the result. This is the last step of
    backpropagation.
    */
    private void backpropagationHiddenInput(double[] outputsErrors, double[] hiddenOutputs, double[] input)  {
        // Calculation of the error between input and hidden layer
        for (int hiddenNeuron = 0; hiddenNeuron < this.hiddenNeurons; hiddenNeuron++) {
            double error = 0;
            for (int outputNeuron = 0; outputNeuron < this.outputNeurons; outputNeuron++) {
                error += outputsErrors[outputNeuron] * this.outputWeights[hiddenNeuron][outputNeuron];
            }
            error *= hiddenOutputs[hiddenNeuron] * (1 - hiddenOutputs[hiddenNeuron]);
            for (int numOfInputs = 0; numOfInputs < this.inputs; numOfInputs++) {
                // Update the weights pointing to hidden layer
                this.hiddenWeights[numOfInputs][hiddenNeuron] += this.learningRate * error * input[numOfInputs];
            }
            // Update the hidden bias
            this.hiddenBias[hiddenNeuron] += this.learningRate * error;
        }
    }

    /*
    Method backpropagation takes in 2 arrays of doubles as parameters.
    Input is the training dataset for the neural network and the target is an array containing all
    possible targets. It uses the above backpropagation algorithms to complete the backpropagation
    process. This is how the neural network learns.
    */
    private void backpropagation(double[] input, double[] target) {
        // Feedforward process performed for hidden and output layers separately (this is needed for gradient descent)
        double[] hiddenOutputs = feedforwardAlgorithm(input, this.hiddenWeights, this.hiddenNeurons, this.hiddenBias);
        double[] outputs = feedforwardAlgorithm(hiddenOutputs, this.outputWeights, this.outputNeurons, this.outputBias);
        // Calculation of errors in the output layer and updating output layer weights and biases
        double[] outputsErrors = backpropagationOutputHidden(hiddenOutputs, outputs, target);
        // Calculation of errors in the hidden layer and updating hidden layer weights and biases
        backpropagationHiddenInput(outputsErrors, hiddenOutputs, input);
    }

    /*
    Method training takes in one parameter - training data set. This method feeds training
    data into the neural networks and invokes backpropagation to let the neural network
    learn.
    */
    public void train(double[][] trainingDataSet) {
        System.out.println("Neural Network - Training: ");
        // Epochs - numbers of training cycles
        for (int epoch = 0; epoch < this.epochs; epoch++) {
            double error = 0.0;
            // Perform training on every row in the training dataset
            for (int row = 0; row < trainingDataSet.length; row++) {
                // Feedforward process
                double[] outputs = feedforward(trainingDataSet[row]);
                // Array of targets
                double[] target = new double[this.outputNeurons];
                // Taking data label from the training data set
                int label = (int) trainingDataSet[row][trainingDataSet[row].length - 1];
                // Changing the value on target index (wanted result) to 1.0 - others stay 0
                target[label] = 1.0;
                // Calculating error on the output
                for (int neuron = 0; neuron < this.outputNeurons; neuron++) {
                    error += 0.5 * (target[neuron] - outputs[neuron]) * (target[neuron] - outputs[neuron]);
                }
                backpropagation(trainingDataSet[row], target);
            }
            System.out.println("Number of epochs:" + (epoch+1) + " Calculated error: " + error);
        }
    }

    /*
    Method test takes in 1 parameter - test data set. It feeds forward data through
    the neural network, giving the neural network's final guess on the data. If the guess
    is corresponding with the label of the data, it is counted in as the correct guess.
    It also outputs result accuracy into the console.
    */
    public void test(double[][] testDataSet) {
        int correct = 0;
        // Feedforwarding the data by row into neural network
        for (int dataPointer = 0; dataPointer < testDataSet.length; dataPointer++) {
            // Feedforward process
            double[] output = feedforward(testDataSet[dataPointer]);
            int guess = 0;
            // Iterating through the results from output layer looking for the index with the highest number
            for (int index = 0; index < output.length; index++) {
                if (output[index] > output[guess]) {
                    guess = index;
                }
            }
            // Accuracy check
            int label = (int) testDataSet[dataPointer][testDataSet[dataPointer].length - 1];
            if (guess == label) {
                correct++;
            }
        }
        // Accuracy calculation
        double accuracy = (double) correct / testDataSet.length * 100.0;
        System.out.println("Neural Network - Test results: ");
        System.out.println("Test accuracy: " + accuracy + "%");
    }
}
