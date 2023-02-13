import java.util.Arrays;

public class NeuralNetwork {
    final int inputs;
    final int hiddenNeurons;
    final int outputNeurons;
    final double tuningRate;
    final int epochs;
    double[][] weightsHidden;
    double[] biasHidden;
    double[][] weightsOutput;
    double[] biasOutput;

    // Constructor function
    public NeuralNetwork(int numberOfInputs, int numberOfHiddenNeurons, int numberOfOutputNeurons, double learningRate, int numberOfEpochs) {
        // Initialise all parameters
        this.inputs = numberOfInputs;
        this.hiddenNeurons = numberOfHiddenNeurons;
        this.outputNeurons = numberOfOutputNeurons;
        this.tuningRate = learningRate;
        this.epochs = numberOfEpochs;
        // Initialise and generate random weights
        this.weightsHidden = MathsLibrary.generateRandomWeights(inputs, hiddenNeurons);
        this.weightsOutput = MathsLibrary.generateRandomWeights(hiddenNeurons, outputNeurons);
        // Initialise and generate random biases
        this.biasHidden = MathsLibrary.generateRandomBias(hiddenNeurons);
        this.biasOutput = MathsLibrary.generateRandomBias(outputNeurons);

    }

    // redo so it won't be repetitive!!!
    private double[] feedforward(double[] input) {
        // Feedforward ops to hidden layer
        double[] weightedSumHidden = MathsLibrary.weightedSum(input, weightsHidden, this.hiddenNeurons);
        MathsLibrary.addBias(weightedSumHidden, this.biasHidden);
        MathsLibrary.activationFunction(weightedSumHidden);
        // Feedforward ops to output layer
        double[] weightedSumOutput = MathsLibrary.weightedSum(weightedSumHidden, weightsOutput, this.outputNeurons);
        MathsLibrary.addBias(weightedSumOutput, this.biasOutput);
        MathsLibrary.activationFunction(weightedSumHidden);

        return weightedSumOutput;
    }

    private void backpropagation(double[] input) {

    }

    protected double[][] train(double[][] trainingSet) {
        double[][] outputLayerResult = feedforward(trainingSet);

        return outputLayerResult;
    }
}
