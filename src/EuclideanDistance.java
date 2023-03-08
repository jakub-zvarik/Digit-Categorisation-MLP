/*
Euclidean Distance class contains the process of classification based on euclidean
distance.
*/
class EuclideanDistance {
    /*
    Method euclideanDistance takes in two parameters - the first fold and the second fold of
    the dataset. It calls the euclideanDistanceCalculator method from the MathsLibrary
    to perform the calculation of the euclidean distance between chosen vectors from both datasets.
    The closest vectors are most probably representing the same number.
    */
    public void euclideanDistance(double[][] firstFold, double[][] secondFold) {
        double distance;
        double[] closestFoundArray;
        double closestDistance;
        final int labelIndex = 64;
        int correctRulings = 0;
        // Loop to point to the row in first fold of the dataset
        for (int firstFoldRow = 0; firstFoldRow < firstFold.length; firstFoldRow++) {
            closestFoundArray = new double[65];
            closestDistance = 0;
            // Loop to point to the row in the second fold of the dataset
            for (int secondFoldRow = 0; secondFoldRow < secondFold.length; secondFoldRow++) {
                // Perform euclidean distance calculation
                distance = MathsLibrary.euclideanDistanceCalculator(firstFold[firstFoldRow], secondFold[secondFoldRow]);
                // Update the closest distance if the newly found distance is smaller than the previous one
                if (closestDistance == 0 || distance < closestDistance) {
                    closestDistance = distance;
                    closestFoundArray = secondFold[secondFoldRow];
                }
            }
            // Increase correct rulings if the classification is correct
            if (firstFold[firstFoldRow][labelIndex] == closestFoundArray[labelIndex]) {
                correctRulings += 1;
            }
        }
        // Accuracy calculation
        double accuracy = (double) correctRulings / firstFold.length * 100.0;
        System.out.println("Euclidean Distance Results:");
        System.out.println("Number of correct rulings: " + correctRulings + " accuracy: " + accuracy + "%");
    }
}
