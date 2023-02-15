/*
The neural network class creates a neural network with a custom number of inputs, one hidden layer
containing a custom number of neurons and an output layer with a custom number of neurons. The initialisation,
feedforward process, backpropagation, and training and testing methods are created within this class.
*/

public class NeuralNetwork {
    // Class variables
    final int inputs;
    final int hiddenNeurons;
    final int outputNeurons;
    final double tuningRate;
    final int epochs;
    double[][] hiddenWeights;
    double[] hiddenBias;
    double[][] outputWeights;
    double[] outputBias;

    /*
    Constructor method - takes in 4 parameters - number of inputs to the neural network,
    number of neurons in the hidden layer, learning rate and number of epochs (training cycles).
    These parameters are then assigned to class variables and used all across this class.
    Also initialises weights to both, hidden and output layers, and biases for hidden and the
    output layer.
    */
    public NeuralNetwork(int numberOfInputs, int numberOfHiddenNeurons, double learningRate, int numberOfEpochs) {
        // Initialise all variables
        this.inputs = numberOfInputs;
        this.hiddenNeurons = numberOfHiddenNeurons;
        this.outputNeurons = 10;
        this.tuningRate = learningRate;
        this.epochs = numberOfEpochs;
        // Initialise and generate random weights
        this.hiddenWeights = MathsLibrary.generateRandomWeights(inputs, hiddenNeurons);
        this.outputWeights = MathsLibrary.generateRandomWeights(hiddenNeurons, outputNeurons);
        // Initialise and generate random biases
        this.hiddenBias = MathsLibrary.generateRandomBias(hiddenNeurons);
        this.outputBias = MathsLibrary.generateRandomBias(outputNeurons);
    }

    /*
    Method feedforward takes in one parameter - input (row from the dataset) and feeds
    data in the forward direction in the neural network (from the input layer to the output layer).
    The calculation has the same process for every layer, therefore the actual calculation
    algorithm for one layer is part of my MathsLibrary which is written and reused multiple
    times within this class. Calculates weighted sums, adds biases and performs activation function.
    For more details see my MathsLibrary.
    */
    private double[] feedforward(double[] input) {
        // Feedforward process from input to the hidden layer
        double[] weightedSumHidden = MathsLibrary.feedforwardAlgorithm(input, this.hiddenWeights, this.hiddenNeurons, this.hiddenBias);
        // Feedforward process from hidden to the output layer
        double[] weightedSumOutput = MathsLibrary.feedforwardAlgorithm(weightedSumHidden, this.outputWeights, this.outputNeurons, this.outputBias);

        return weightedSumOutput;
    }

    /*
    Method backpropagation takes in 2 arrays of doubles as parameters - input and targets.
    Input is the training dataset for the neural network and the target is an array containing all
    possible targets. During training, these targets are all set to 0 only the element sitting on
    the index corresponding with the target value (wanted result) is set to 1.0. With this,
    the neural network knows how to manipulate weights and biases to get better results.
    This is how the neural network learns. To manipulate weights and biases correctly,
    gradient descent is used. It is void because it does not need to return anything,
    just calculates errors and updates weights and biases.
    */
    private void backpropagation(double[] input, double[] target) {
        // Feedforward process performed for hidden and output layer separately (this is needed for gradient descent)
        double[] hiddenOutputs = MathsLibrary.feedforwardAlgorithm(input, this.hiddenWeights, this.hiddenNeurons, this.hiddenBias);
        double[] outputs = MathsLibrary.feedforwardAlgorithm(hiddenOutputs, this.outputWeights, this.outputNeurons, this.outputBias);
        double[] outputsErrors = new double[this.outputNeurons];
        double[] hiddenErrors = new double[this.hiddenNeurons];

        // Calculation of the error between hidden and output layer
        for (int outputNeuron = 0; outputNeuron < this.outputNeurons; outputNeuron++) {
            double error = outputs[outputNeuron] * (1 - outputs[outputNeuron]) * (target[outputNeuron] - outputs[outputNeuron]);
            outputsErrors[outputNeuron] = error;
            for (int column = 0; column < this.hiddenNeurons; column++) {
                // Update weights pointing to output layer
                this.outputWeights[column][outputNeuron] += this.tuningRate * error * hiddenOutputs[column];
            }
            // Update output bias
            this.outputBias[outputNeuron] += tuningRate * error;
        }

        // Calculation of the error between input and hidden layer
        for (int hiddenNeuron = 0; hiddenNeuron < this.hiddenNeurons; hiddenNeuron++) {
            double error = 0;
            for (int outputNeuron = 0; outputNeuron < this.outputNeurons; outputNeuron++) {
                error += outputsErrors[outputNeuron] * this.outputWeights[hiddenNeuron][outputNeuron];
            }
            error *= hiddenOutputs[hiddenNeuron] * (1 - hiddenOutputs[hiddenNeuron]);
            hiddenErrors[hiddenNeuron] = error;
            for (int numOfInputs = 0; numOfInputs < this.inputs; numOfInputs++) {
                // Update weights pointing to hidden layer
                this.hiddenWeights[numOfInputs][hiddenNeuron] += this.tuningRate * error * input[numOfInputs];
            }
            // Update hidden bias
            this.hiddenBias[hiddenNeuron] += this.tuningRate * error;
        }
    }

    /*
    Method training takes in one parameter - training data set. This method feeds training
    data into the neural networks and invokes backpropagation to let the neural network
    learn.
    */
    public void train(double[][] trainingDataSet) {
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
            System.out.println("Number of epochs:" + (epoch+1) + " error: " + error);
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
        System.out.println("Test accuracy: " + accuracy + "%");
    }
}
