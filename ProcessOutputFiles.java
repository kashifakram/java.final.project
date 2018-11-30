package POS18S2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProcessOutputFiles {

    private File f;
    private String filePath;
    private PrintWriter pw;

    ProcessOutputFiles() {
        this.filePath = null;
        f = null;
        pw = null;
    }

    public void writeResultsFile(String filePath){
        try {
            this.filePath = filePath;
            f = new File(filePath);
            pw = new PrintWriter(new FileOutputStream(f));

            for (Resident r : Resident.getResidentsList()){
                pw.println(r.toString());
            }

            pw.close();

        } catch (FileNotFoundException fex){
            System.out.println(fex.getMessage());
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }


    public void writeReportFile(String filePath){
        try {
            this.filePath = filePath;
            f = new File(filePath);
            pw = new PrintWriter(new FileOutputStream(f));

            for (Query q : Query.queries) {
                    pw.println(q.toString());
            }

            pw.close();

        } catch (FileNotFoundException fex){
            System.out.println(fex.getMessage());
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }


}
