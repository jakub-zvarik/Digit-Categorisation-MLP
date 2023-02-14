class EuclideanDistance {

    public void euclideanDistance(double[][] trainingSet, double[][] testSet) {
        double distance;
        double[] closestFoundArray;
        double closestDistance;
        int correctRulings = 0;

        for (int trainingSetRow = 0; trainingSetRow < trainingSet.length; trainingSetRow++) {
            closestFoundArray = new double[65];
            closestDistance = 0;
            for (int testSetRow = 0; testSetRow < testSet.length; testSetRow++) {
                distance = MathsLibrary.euclideanDistanceCalculator(trainingSet, testSet, trainingSetRow, testSetRow);
                if (closestDistance == 0 || distance < closestDistance) {
                    closestDistance = distance;
                    closestFoundArray = testSet[testSetRow];
                }
            }
            if (trainingSet[trainingSetRow][64] == closestFoundArray[64]) {
                correctRulings += 1;
            }
        }
        System.out.println("Number of correct rulings: " + correctRulings + " out of " + testSet.length);
    }
}
