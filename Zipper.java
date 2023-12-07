import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Zipper{
    public static void zip(File ogLoc, File saveLoc) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader(ogLoc)); //In holds file data
        HashMap map = new HashMap();
        
        for (String line = in.readLine(); line != null; line = in.readLine()) {         // Weird for loop to give line as each line in a file
            for(String s : line.split("")){                                       // Split the line into each individual char
                if(map.containsKey(s)){
                    map.put(s, (int)(map.get(s)) + 1);
                } else{
                    map.put(s, 1);
                }
            }
        }
        System.out.println(map);
    }

    public static void main(String[] args) {
        try {Zipper.zip(new File("mary.txt"), new File("save.txt"));}
        catch (IOException e) { e.printStackTrace();}
    }
}