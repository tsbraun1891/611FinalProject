import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class IO {

    public IO() {

    }

    /**
     * Reads a file and returns an array of all of the lines in the file
     * @param fileName - name of the file
     * @return string arraylist of all lines of the given file
     */
    public ArrayList<String> readFile(String fileName) {
        ArrayList<String> rhet = new ArrayList<String>();
        String row;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while((row = reader.readLine()) != null) {
                rhet.add(row);
            }

            reader.close();
        } catch (Exception e) {
            System.err.println(e);
        }

        return rhet;
    }
}
