import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Zipper{
    private static BufferedReader in;
    private static HashMap<Character, Integer> frequencyTable;
    private static PriorityQueue<HuffmanBaseNode> pq;
    private static HashMap<Character, Integer> codes;

    public static void zip(File ogLoc, File saveLoc) throws IOException{
        in = new BufferedReader(new FileReader(ogLoc)); //In holds file data
        frequencyTable = Zipper.createMap(in);
        pq = new PriorityQueue<HuffmanBaseNode>();
        codes = new HashMap<Character, Integer>();

        for(Character s : frequencyTable.keySet()){
            pq.add(new HuffmanLeafNode(s, frequencyTable.get(s))); // PriorityQueue now full of HuffmanLeafNodes
        }

        while(pq.size() != 1){  // Break PriorityQueue into just internal nodes
            HuffmanBaseNode left = pq.poll();
            HuffmanBaseNode right = pq.poll();
            int weight = left.weight() + right.weight();
            HuffmanInternalNode IntNode = new HuffmanInternalNode(left, right, weight);

            pq.add(IntNode);
        }

        HuffmanBaseNode root = pq.poll();
        
        encodeData(root, "");   
        
        System.out.println(codes);
    }

    public static HashMap<Character, Integer> createMap(BufferedReader in) throws IOException{
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        
        for (String line = in.readLine(); line != null; line = in.readLine()) {      // Weird for loop to give line as each line in a file
            for(String s : line.split("")){                                    // Split the line into each individual char
                if(map.containsKey(s.toCharArray()[0])){
                    map.put(s.toCharArray()[0], (int)(map.get(s.toCharArray()[0])) + 1);  // Increase count of character if already in map
                } else{
                    map.put(s.toCharArray()[0], 1);                // Else, just put the character in with a count of 1 to start
                }
            }
        }

        return map;
    }

    public static void encodeData(HuffmanBaseNode root, String str)  // This method will give the char to char-encodings
    {  
        if (root instanceof HuffmanLeafNode){
            codes.put(((HuffmanLeafNode)root).value(), Integer.parseInt(str));
            return; // End the recursive search at this point since its a leaf node
        }

        HuffmanInternalNode tempRoot = (HuffmanInternalNode)root;
        encodeData(tempRoot.left(), str + "0"); // Recursively search left of root, adding a 0 to encoding
        encodeData(tempRoot.right(), str + "1"); // Recursively search right of root, adding a 1 to encoding
    }

    public static void main(String[] args) {
        try {Zipper.zip(new File("mary.txt"), new File("save.txt"));}
        catch (IOException e) { e.printStackTrace();}
    }
}