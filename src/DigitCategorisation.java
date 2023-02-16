import java.io.FileNotFoundException;

/*
Code written by Jakub Zvarik

This program categorises UCI digits provided as a dataset with 65 values per number. 64
values representing one handwritten number in an 8x8 image, 65th value is a label (represented number).
There are 10 numbers represented in the dataset (0-9). The dataset has 5620 numbers in it.
This program uses euclidean distance and neural network (MLP) to categorise the numbers.
The dataset was folded into 2 datasets - training and testing.
*/

/*
Main class
There are 4 other classes in this program. Three are initialised from this class.
One is a support class (MathsLibrary) containing all the necessary maths for other libraries.
*/
public class DigitCategorisation {
    public static void main(String[] args) throws FileNotFoundException {
        // Variables storing the names of the files containing datasets
        String firstDataFold = "cw2DataSet1.csv";
        String secondDataFold = "cw2DataSet2.csv";
        // Initialise training and test datasets via Data class
        double[][] firstFold = Data.sortData(firstDataFold);
        double[][] secondFold = Data.sortData(secondDataFold);

        // Categorisation by euclidean distance
        EuclideanDistance euclidean = new EuclideanDistance();
        // Method for calculation takes in 2 parameters - training and test set
        euclidean.euclideanDistance(secondFold, firstFold);


        // Categorisation by neural network
        NeuralNetwork neural = new NeuralNetwork(65, 86, 0.01, 800);
        // Initialising the training - takes in training dataset
        neural.train(firstFold);
        // Testing the network with the second fold of the dataset
        neural.test(secondFold);
    }
}