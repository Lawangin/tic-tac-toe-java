package lawanginprojects;

public class Grid {

    /*
     * Initializes a 3 by 3 grid to default char values ('\u0000')
     * */
    char value = '\u0000';
    char[][] gridMatrix = {
            { value, value, value },
            { value, value, value },
            { value, value, value }
    };

    public Grid() {

    }


    /**
     * Formats the grid row to a String that consists of a space, the char,
     *  a space, a vertical pipe, a space, the char, a space, a vertical pipe,
     * a space, the char, and a final space,
     * for example: " X | X | X "
     *
     * @param rowIndex  the index of the row to convert to a String
     * @return a formatted String representation of the row
     * @throws IllegalArgumentException if an invalid row index is given
     */
    public String getRow(int rowIndex) throws IllegalArgumentException{
        /**
         * check to see if rowIndex passed in parameter is valid
         */
        if (rowIndex > gridMatrix.length - 1 || rowIndex < 0) {
            throw new IllegalArgumentException("Invalid row index. Please enter a number between 0-3.");
        }

        StringBuilder sb = new StringBuilder();

        /**
         * create string based on row with the format X | X | X. If statement in the middle to see if current value is
         * null and if it is to be replaced with space.
         */
        for (int columnIndex = 0; columnIndex < gridMatrix.length; columnIndex++) {
            sb.append(" ").append(gridMatrix[rowIndex][columnIndex] == '\u0000' ? " " : gridMatrix[rowIndex][columnIndex]).append(" ");
            if (columnIndex < 2) {
                sb.append("|");
            }
        }

        return sb.toString();
    }

    /**
     * Sets the grid location to the given value
     * @param value         char value for the grid location
     * @param rowIndex      the index of the row position
     * @param columnIndex   the index of the column position
     * @throws IllegalArgumentException if the row index or column index is invalid
     *                                  or if the position is not null
     */
    public void setPosition(char value, int rowIndex, int columnIndex) throws IllegalArgumentException{
        /**
         * check to see if rowIndex and columnIndex passed in parameter is valid
         */
        if (rowIndex > 2 || rowIndex < 0) {
            throw new IllegalArgumentException("Invalid row index. Please enter a number between 0-2.");
        }
        if (columnIndex > 2 || columnIndex < 0) {
            throw new IllegalArgumentException("Invalid column index. Please enter a number between 0-2.");
        }

        /**
         * validate that current position is taken
         */
        if (gridMatrix[rowIndex][columnIndex] != '\u0000') {
            throw new IllegalArgumentException("Spot is already taken by " + gridMatrix[rowIndex][columnIndex]);
        }

        gridMatrix[rowIndex][columnIndex] = value;
    }

    /**
     * Checks for valid input value
     * @param inputValue the char value to be checked
     * @return true if input value is X, x, O, or o
     * @throws IllegalArgumentException if character is not X or O
     */
    public boolean checkInput(char inputValue) throws IllegalArgumentException{
        /**
         * validate that X or O are passed regardless of capitalization
         */
        if (Character.toLowerCase(inputValue) == 'x' || Character.toLowerCase(inputValue) == 'o') {
            return true;
        } else {
            throw new IllegalArgumentException("Please enter X or O");
        }
    }

    /**
     * Checks if all positions have a char value
     * @return true if none of the grid locations contain the null character ('\u0000')
     */
    public boolean isFull(){
        boolean result = true;

        /**
         * validate if all parts of the grid are full but checking null status
         */
        for (char[] matrix : gridMatrix) {
            for (int j = 0; j < gridMatrix.length; j++) {
                if (matrix[j] == '\u0000') {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     *  Check if row has all the same characters
     * @param rowIndex  the row index to check
     * @return  true if row contains the same char value
     * @throws IllegalArgumentException if an invalid row index is given
     */
    public boolean isRowMatching(int rowIndex) throws IllegalArgumentException{
        /**
         * check to see if rowIndex passed in parameter is valid
         */
        if (rowIndex > gridMatrix.length - 1 || rowIndex < 0) {
            throw new IllegalArgumentException("Please enter a row between 0-2");
        }

        /**
         * return true if the row has similar values
         */
        return (gridMatrix[rowIndex][0] == gridMatrix[rowIndex][1] && gridMatrix[rowIndex][1] == gridMatrix[rowIndex][2]);
    }

    /**
     * Check if column has all the same characters
     * @param columnIndex   the column index to check
     * @return  true if column contains the same char value
     * @throws IllegalArgumentException if an invalid column index is given
     */
    public boolean isColumnMatching(int columnIndex) throws IllegalArgumentException{
        /**
         * check to see if columnIndex passed in parameter is valid
         */
        if (columnIndex > gridMatrix.length - 1 || columnIndex < 0) {
            throw new IllegalArgumentException("Please enter a row between 0-2");
        }
        /**
         * return true if column values are same
         */
        return gridMatrix[0][columnIndex] == gridMatrix[1][columnIndex] && gridMatrix[1][columnIndex] == gridMatrix[2][columnIndex];
    }

    /**
     * Checks if either diagonal has the same characters
     * @return true if grid position 0,0; 1,1; and 2,2 are the same
     *              if grid position 2,0; 1,1; and 0,2 are the same
     */
    public boolean hasDiagonalMatch() {
        /**
         * check to see if diagnol values match and return true if one of them is true
         */
        boolean diagOne = gridMatrix[0][0] == gridMatrix[1][1] && gridMatrix[1][1] == gridMatrix[2][2];
        boolean diagTwo = gridMatrix[2][0] == gridMatrix[1][1] && gridMatrix[1][1] == gridMatrix[0][2];
        return (diagOne || diagTwo);
    }

    /**
     * Checks if there is a character with three in a row on the grid
     * @return winning character if there is a row, column or diagonal match
     *          otherwise returns the null character
     */
    public char checkForWinner() {
        char result = '\u0000';
        for (int i = 0; i < gridMatrix.length; i++) {
            if (isRowMatching(i)) {
                result = gridMatrix[i][0];
            }
            if (isColumnMatching(i)) {
                result = gridMatrix[0][i];
            }
        }
        if (hasDiagonalMatch()) {
            result = gridMatrix[1][1];
        }
        return result;
    }

    @Override
    /**
     * Returns a string representation of the grid with each row separated by a line
     * @return string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int rowIndex = 0; rowIndex < gridMatrix.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < gridMatrix.length; columnIndex++) {
                sb.append(" ").append(gridMatrix[rowIndex][columnIndex] == '\u0000' ? " " : gridMatrix[rowIndex][columnIndex]).append(" ");
                if (columnIndex != 2) {
                    sb.append("|");
                }
            }
            if (rowIndex < 2) {
                sb.append("\n---------\n");
            }
            else {
                sb.append("\n");
            }

        }

        return sb.toString();
    }
}
