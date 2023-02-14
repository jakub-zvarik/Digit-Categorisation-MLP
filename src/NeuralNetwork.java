public class NeuralNetwork {
    final int inputs;
    final int hiddenNeurons;
    final int outputNeurons;
    final double tuningRate;
    final int epochs;
    double[][] hiddenWeights;
    double[] hiddenBias;
    double[][] outputWeights;
    double[] outputBias;

    // Constructor function
    public NeuralNetwork(int numberOfInputs, int numberOfHiddenNeurons, double learningRate, int numberOfEpochs) {
        // Initialise all parameters
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

    private double[] feedforwardHidden(double[] input) {
        double[] weightedSumHidden = MathsLibrary.weightedSum(input, this.hiddenWeights, this.hiddenNeurons);
        MathsLibrary.addBias(weightedSumHidden, this.hiddenBias);
        MathsLibrary.activationFunction(weightedSumHidden);

        return weightedSumHidden;
    }

    private double[] feedforwardOutput(double[] weightedSumHidden) {
        double[] weightedSumOutput = MathsLibrary.weightedSum(weightedSumHidden, this.outputWeights, this.outputNeurons);
        MathsLibrary.addBias(weightedSumOutput, this.outputBias);
        MathsLibrary.activationFunction(weightedSumOutput);

        return weightedSumOutput;
    }

    private double[] feedforward(double[] input) {
        double[] weightedSumHidden = MathsLibrary.weightedSum(input, this.hiddenWeights, this.hiddenNeurons);
        MathsLibrary.addBias(weightedSumHidden, this.hiddenBias);
        MathsLibrary.activationFunction(weightedSumHidden);

        double[] weightedSumOutput = MathsLibrary.weightedSum(weightedSumHidden, this.outputWeights, this.outputNeurons);
        MathsLibrary.addBias(weightedSumOutput, this.outputBias);
        MathsLibrary.activationFunction(weightedSumOutput);

        return weightedSumOutput;
    }

    private void backpropagation(double[] input, double[] target) {
        double[] hiddenOutputs = feedforwardHidden(input);
        double[] outputs = feedforwardOutput(hiddenOutputs);
        double[] outputsErrors = new double[this.outputNeurons];
        double[] hiddenErrors = new double[this.hiddenNeurons];

        for (int outputNeuron = 0; outputNeuron < this.outputNeurons; outputNeuron++) {
            double error = outputs[outputNeuron] * (1- outputs[outputNeuron]) * (target[outputNeuron] - outputs[outputNeuron]);
            outputsErrors[outputNeuron] = error;
            for (int column = 0; column < this.hiddenNeurons; column++) {
                this.outputWeights[column][outputNeuron] += this.tuningRate * error * hiddenOutputs[column];
            }
            this.outputBias[outputNeuron] += tuningRate * error;
        }

        for (int hiddenNeuron = 0; hiddenNeuron < this.hiddenNeurons; hiddenNeuron++) {
            double error = 0;
            for (int outputNeuron = 0; outputNeuron < this.outputNeurons; outputNeuron++) {
                error += outputsErrors[outputNeuron] * this.outputWeights[hiddenNeuron][outputNeuron];
            }
            error *= hiddenOutputs[hiddenNeuron] * (1 - hiddenOutputs[hiddenNeuron]);
            hiddenErrors[hiddenNeuron] = error;
            for (int numOfInputs = 0; numOfInputs < this.inputs; numOfInputs++) {
                this.hiddenWeights[numOfInputs][hiddenNeuron] += this.tuningRate * error * input[numOfInputs];
            }
            this.hiddenBias[hiddenNeuron] += this.tuningRate * error;
        }
    }

    public void test(double[][] testDataSet) {
        int correct = 0;
        for (int dataPointer = 0; dataPointer < testDataSet.length; dataPointer++) {
            double[] output = feedforward(testDataSet[dataPointer]);
            int guess = 0;
            for (int j = 0; j < output.length; j++) {
                if (output[j] > output[guess]) {
                    guess = j;
                }
            }
            int label = (int) testDataSet[dataPointer][testDataSet[dataPointer].length - 1];
            if (guess == label) {
                correct++;
            }
        }
        double accuracy = (double) correct / testDataSet.length * 100.0;
        System.out.println("Test accuracy: " + accuracy + "%");
    }


    public void train(double[][] trainingDataSet) {
        for (int epoch = 0; epoch < this.epochs; epoch++) {
            double error = 0.0;
            for (int input = 0; input < trainingDataSet.length; input++) {
                double[] outputs = feedforward(trainingDataSet[input]);
                double[] target = new double[this.outputNeurons];
                int label = (int) trainingDataSet[input][trainingDataSet[input].length - 1];
                target[label] = 1.0;
                for (int neuron = 0; neuron < this.outputNeurons; neuron++) {
                    error += 0.5 * (target[neuron] - outputs[neuron]) * (target[neuron] - outputs[neuron]);
                }
                backpropagation(trainingDataSet[input], target);
            }
            System.out.println("Epoch number " + (epoch+1) + " error: " + error);
        }
    }
}


