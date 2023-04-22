import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
* This program calculates amount of mean median and mode.
*
* @author  Jedidiah Alfred
* @version 1.0
* @since   2023-02-08
*/

public final class Java2Darrays {
    /**
    * This is a private constructor used to satisfy the
    * style checker.
    *
    * @exception IllegalStateException Utility Class
     */
    private Java2Darrays() {
        throw new IllegalStateException("Utility Class");
    }
    /**
    * This is the main method.
    *
    * @param args Unused
    * @throws Exception incase of errors
    */

    public static void main(String[] args) throws Exception {

        // Initializing variables
        final String err = "Err";
        String list;
        try {
            // Creating the writer
            final FileWriter writer = new FileWriter("output.csv");

            try {
                // Creating the input file objects
                final File assignments = new File("assignments.txt");
                final File students = new File("students.txt");

                // Creating the scanner.
                final Scanner scanner1 = new Scanner(students);
                final Scanner scanner2 = new Scanner(assignments);

                // ArrayList to hold the lines
                final ArrayList<String> lists = new ArrayList<>();

                // Getting the input from the first file
                while (scanner1.hasNextLine()) {

                    // getting next line and put it in the list
                    list = scanner1.nextLine();
                    lists.add(list);
                }
                // set a counter
                int counter = 0;

                // create an array to hold the info in the list
                final String[] nameArray = new String[lists.size()];

                // For loop takes the info in the list and places it into array
                for (String name : lists) {
                    nameArray[counter] = name;
                    counter++;
                }
                // resetting counter and interim list
                counter = 0;
                lists.clear();

                // getting data in the second file and inputting it to the
                // newly emptied list
                while (scanner2.hasNextLine()) {
                    list = scanner2.nextLine();
                    lists.add(list);
                }

                // creating a new array to hold this data
                final String[] assignArray = new String[lists.size()];

                // taking the data from the list and inputting it into
                // newly made array
                for (String assignment : lists) {
                    assignArray[counter] = assignment;
                    counter++;
                }
                // call function to generate marks
                final String[][] scores = generateMarks(nameArray, assignArray);

                // setting up what is displayed in the csv file
                for (String[] row : scores) {

                    // creating the blocks
                    final StringBuilder blocks = new StringBuilder();

                    // repeat loop through rows and columns
                    for (int col = 0; col < row.length; col++) {

                        // adding to row
                        blocks.append(row[col]);
                        if (col != row.length - 1) {
                            blocks.append(", ");
                        } else {
                            blocks.append("\n");
                        }
                    }

                    // write to the csv file
                    writer.write(blocks.toString());
                }
            } catch (IOException error) {
                System.out.println(err + error.getMessage());
            }
            // closes the writer
            writer.close();
        } catch (IOException error) {
            System.out.println(err + error.getMessage());
        }
    }
    /**
    * This is the method.
    *
    * @param names needed for the rows
    * @param assignments needed for the columns
    * @return the marks 2dArray
    */

    public static String[][] generateMarks(
        String[] names, String[] assignments) {

        // creating the mean and standard Deviation
        final int mean = 75;
        final int stanDeviation = 10;

        // creating random object
        final Random randNum = new Random();

        // creating the 2D array
        final String[][] marks = new String[
            names.length + 1][assignments.length + 1];

        // Setting up the header for the first column
        marks[0][0] = "names";

        // Setting up the headers for the rest of the columns
        for (int counter = 1; counter < assignments.length + 1; counter++) {
            marks[0][counter] = assignments[counter - 1];
        }

        // Setting up the names for the rows
        for (int i = 1; i < names.length + 1; i++) {
            marks[i][0] = names[i - 1];
        }

        // Randomly generating the data and inputting it into the 2D Array
        for (int row = 1; row < names.length + 1; row++) {
            for (int col = 1; col < assignments.length + 1; col++) {

                // This makes random numbers that correlate a mean and
                // standard deviation
                final int rNum = (int) (
                    randNum.nextGaussian() * mean + stanDeviation);
                marks[row][col] = String.valueOf(rNum);
            }
        }
        // Returning to main
        return marks;
    }
}
