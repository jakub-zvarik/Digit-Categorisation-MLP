import java.io.FileNotFoundException;

/*
Code written by Jakub Zvarik

This program categorise UCI digits provided as dataset with 65 values per number. 64
values are representing one handwritten number in 8x8 image, 65th value is a label.
There are 10 numbers represented in the dataset (0-9). Dataset has 5620 numbers in it.
This program uses euclidean distance and neural network (MLP) to categorise the numbers.
Dataset was folded into 2 datasets - training and test.
*/

/*
Main class
There are 4 other classes in this program. Three are initialised from this class.
One is support class (MathsLibrary) containing all the necessary maths for other libraries.
*/
public class DigitCategorisation {
    public static void main(String[] args) throws FileNotFoundException {
        // Variables storing the names of the files containing datasets
        String trainingDataSet = "cw2DataSet1.csv";
        String testDataSet = "cw2DataSet2.csv";
        // Initialise training and test datasets via Data class
        double[][] trainingSet = Data.sortData(trainingDataSet);
        double[][] testSet = Data.sortData(testDataSet);

        // Categorisation by euclidean distance
        EuclideanDistance euclidean = new EuclideanDistance();
        // Method for calculation takes in 2 parameters - training and test set
        euclidean.euclideanDistance(trainingSet, testSet);

        /*
        Categorisation by neural network
        Class takes 4 parameters - number of inputs (including label), number of neurons in hidden layer,
        learning rate and number of epochs (number of training cycles performed on the whole dataset)
        */
        NeuralNetwork neural = new NeuralNetwork(65, 46, 0.01, 100);
        // Method initialising the training - takes in training dataset
        neural.train(trainingSet);
        // Method performing accuracy test on the neural network - takes in test dataset
        neural.test(testSet);
    }
}