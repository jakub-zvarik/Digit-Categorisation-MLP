The goal of this coursework is to program a machine-learning system for handwritten digits recognition. 
These digits are represented by 64 number values (8x8 pixels images). 
The first step is to read the data provided in the .txt format. With the structure of the dataset in mind, 
the approach chosen was to sort the datasets into 2D arrays of 2810 rows (there are 2810 in one dataset) and 65 columns 
(64 columns represent coordinates in the 8x8 image and the 65th column is the label of the data, 
aka number the previous 64 columns are representing).

In the main class (Digit Categorisation), data are already loaded and labelled as firstFold and secondFold. 
There is a Euclidean distance class initialised and euclidean distance method to perform the calculation.
For the neural network, there is a neural network class initialised with some customisable parameters like 
the number of neurons in the hidden layer, the learning rate, and the number of epochs. 
The train method trains the neural network on the inserted data and the test method performs 
the test of the neural network on the inserted data.
There are 5 classes – DigitCategorsiation (main class), 
MathsLibrary (class with some mathematical operations for euclidean distance and forward algorithm in a neural network), 
NeuralNetwork class – containing actual neural network’s algorithms, EuclideanDistance, 
and Data – containing methods for reading and sorting data.

Everything is also well documented in the code via comments.
