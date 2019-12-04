import java.io.*;

public class textFileReader {
    private String fileAddress;

    public textFileReader(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public void read() throws IOException {
        File file = new File(fileAddress);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String initial="";
        while ((br.readLine()) != null)
            initial=br.readLine();

        convert(initial);
    }

    public String[][] convert(String initial){
        return null;
    }

}
