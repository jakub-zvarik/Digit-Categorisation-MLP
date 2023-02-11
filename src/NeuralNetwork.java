public class NeuralNetwork {
    final int numberOfInputs;
    final int numberOfHiddenNeurons;
    final int numberOfOutputs;
    double[][] bias_hidden;
    double[][] bias_output;
    double[][] weightsToHidden;
    double[][] weightsToOutput;

    // Constructor function
    public NeuralNetwork(int inputs, int hiddenNeurons, int outputs) {
        numberOfInputs = inputs;
        numberOfHiddenNeurons = hiddenNeurons;
        numberOfOutputs = outputs;

        weightsToHidden = MathsLibrary.generateRandomMatrix(numberOfHiddenNeurons, numberOfInputs);
        weightsToOutput = MathsLibrary.generateRandomMatrix(numberOfOutputs, numberOfHiddenNeurons);

        bias_hidden = MathsLibrary.generateRandomMatrix(numberOfHiddenNeurons, 1);
        bias_output = MathsLibrary.generateRandomMatrix(numberOfOutputs, 1);

    }

    protected double[][] feedforward(double[][] trainingSet) {
        double[][] weightedSumHidden = MathsLibrary.dotProduct(trainingSet[0], weightsToHidden);
        MathsLibrary.scalarAdd(weightedSumHidden, bias_hidden);
        // Activation
        MathsLibrary.activationFunction(weightedSumHidden);

        double[] weightedSumHidden1D = MathsLibrary.to1DArray(weightedSumHidden);
        double[][] weightedSumOutput = MathsLibrary.dotProduct(weightedSumHidden1D, weightsToOutput);
        // Activation
        double[][] result = MathsLibrary.scalarAdd(weightedSumOutput, bias_output);
        MathsLibrary.activationFunction(result);

        return result;
    }

}
