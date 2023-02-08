public class MathsLibrary {

    public int[][] scalarAdd(int[][] data, int number) {
        for (int row = 0; row < data.length; row++) {
            for (int column = 0; column < 64; column++) {
                data[row][column] += number;
            }
        }
        return data;
    }

    public int[][] scalarMultiply(int[][] data, int number) {
        for (int row = 0; row < data.length; row++) {
            for (int column = 0; column < 64; column++) {
                data[row][column] *= number;
            }
        }
        return data;
    }



}
