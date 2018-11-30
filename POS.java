package POS18S2;

import javax.swing.text.DateFormatter;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This is public Main class which is responsible to run main method to start program
 * This class will use objects and methods from Resident and Instructon classes to read and write files
 * */

public class POS {

    /**
     * main method to execute program and start processing on input and output files
     * */

    public static void main(String[] args) {

        //Arguments validation
        if (args.length != 4) {

            System.out.println("Invalid number of arguments provided.");
            System.out.println("Example Usage: java POS18s2.POS " +
                    "u:\\records\\rec01.txt " +
                    "u:\\ins01.txt " +
                    "w:\\out\\res01.txt " +
                    "W:\\report.txt");
            return;
        } else if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty()) {
            System.out.println("One of the required arguments is null");
            return;
        } else {
            String inputMembersFilePath = args[0];
            String inputInstructionsFilePath = args[1];
            String outputResultsFilePath = args[2];
            String outputReportFilePath = args[3];

            ProcessInputFiles pif = new ProcessInputFiles();
            pif.readMembersFile(inputMembersFilePath);
            pif.readAndExecuteInstructionsFile(inputInstructionsFilePath);

            ProcessOutputFiles pof = new ProcessOutputFiles();
            pof.writeResultsFile(outputResultsFilePath);
            pof.writeReportFile(outputReportFilePath);
        }


    }
}
