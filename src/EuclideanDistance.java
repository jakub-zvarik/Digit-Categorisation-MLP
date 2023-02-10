class EuclideanDistance {

    public int euclideanDistance(double[][] trainingSet, double[][] testSet) {
        double distance;
        double[] closestFoundArray;
        double closestDistance;
        int correctRulings = 0;

        for (int trainingSetRow = 0; trainingSetRow < trainingSet.length; trainingSetRow++) {
            closestFoundArray = new double[65];
            closestDistance = 0;

            for (int testSetRow = 0; testSetRow < testSet.length; testSetRow++) {

                distance = euclideanDistanceCalculator(trainingSet, testSet, trainingSetRow, testSetRow);

                if (closestDistance == 0 || distance < closestDistance) {
                    closestDistance = distance;
                    closestFoundArray = testSet[testSetRow];
                }
            }

            if (trainingSet[trainingSetRow][64] == closestFoundArray[64]) {
                correctRulings += 1;
            }

        }

        return correctRulings;
    }

    private double euclideanDistanceCalculator(double[][] trainingSet, double[][] testSet, int trainingSetRow, int testSetRow) {
        double distance = 0;
        for (int numPointer = 0; numPointer < 64; numPointer++) {
            double difference = trainingSet[trainingSetRow][numPointer] - testSet[testSetRow][numPointer];
            distance += difference * difference;

        }
        distance = (int) Math.sqrt(distance);

        return distance;
    }
}
