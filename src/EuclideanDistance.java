/*
Euclidean Distance class contains the process of classification based on euclidean
distance.
*/
class EuclideanDistance {
    /*
    Method euclideanDistance takes in two parameters - first fold and second fold of
    the dataset. It calls the euclideanDistanceCalculator method from the MathsLibrary
    to perform calculation of euclidean distance between chosen vectors from both datasets.
    */
    public void euclideanDistance(double[][] firstFold, double[][] secondFold) {
        double distance;
        double[] closestFoundArray;
        double closestDistance;
        int correctRulings = 0;
        // Initialising first loop to point to the row in first fold of the dataset
        for (int firstFoldRow = 0; firstFoldRow < firstFold.length; firstFoldRow++) {
            closestFoundArray = new double[65];
            closestDistance = 0;
            // Second loop to point to the row in the second fold of the dataset
            for (int secondFoldRow = 0; secondFoldRow < secondFold.length; secondFoldRow++) {
                // Perform euclidean distance calculation
                distance = MathsLibrary.euclideanDistanceCalculator(firstFold, secondFold, firstFoldRow, secondFoldRow);
                // Update closes distance if the newly found distance is better than the previous one
                if (closestDistance == 0 || distance < closestDistance) {
                    closestDistance = distance;
                    closestFoundArray = secondFold[secondFoldRow];
                }
            }
            // Increase correct rulings if the classification is correct (64th index is the number's label)
            if (firstFold[firstFoldRow][64] == closestFoundArray[64]) {
                correctRulings += 1;
            }
        }
        // Accuracy calculation
        double accuracy = (double) correctRulings / firstFold.length * 100.0;
        System.out.println("Number of correct rulings: " + correctRulings + " accuracy: " + accuracy);
    }
}
