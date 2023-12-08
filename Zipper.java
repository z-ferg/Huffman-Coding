import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Zipper{
    public static void zip(File ogLoc, File saveLoc) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader(ogLoc)); //In holds file data
        HashMap<String, Integer> frequencyTable = Zipper.createMap(in);
        PriorityQueue<HuffmanBaseNode> pq = new PriorityQueue<HuffmanBaseNode>();

        for(String s : frequencyTable.keySet()){
            pq.add(new HuffmanLeafNode(s, frequencyTable.get(s))); // PriorityQueue now full of HuffmanLeafNodes
        }

        while(pq.size() != 1){  // Break PriorityQueue into just internal nodes
            HuffmanBaseNode left = pq.poll();
            HuffmanBaseNode right = pq.poll();
            int weight = left.weight() + right.weight();
            HuffmanInternalNode IntNode = new HuffmanInternalNode(left, right, weight);

            System.out.println(IntNode.weight());

            pq.add(IntNode);
        }

        HuffmanBaseNode root = pq.poll();
    }

    public static HashMap<String, Integer> createMap(BufferedReader in) throws IOException{
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        for (String line = in.readLine(); line != null; line = in.readLine()) {         // Weird for loop to give line as each line in a file
            for(String s : line.split("")){                                       // Split the line into each individual char
                if(map.containsKey(s)){
                    map.put(s, (int)(map.get(s)) + 1);  // Increase count of character if already in map
                } else{
                    map.put(s, 1);                // Else, just put the character in with a count of 1 to start
                }
            }
        }

        return map;
    }

    public static HashMap<String, Integer> getEncodings(HuffmanBaseNode root, HashMap<String, Integer> map){
        HashMap<String, Integer> endcodings = new HashMap<String, Integer>();

        for(String s : map.keySet()){
            endcodings.put(s, Integer.parseInt(recursiveEncoding(root, "", s)));
        }

        return endcodings;
    }

    private static String recursiveEncoding(HuffmanBaseNode root, String encoding, String target){
        String left; = "";
        String right = "";

        if(root.isLeaf()){  // Root is a leaf node
            HuffmanLeafNode tempRoot = (HuffmanLeafNode)(root);
            if(tempRoot.value() == target){
                return encoding;
            } else {
                return "Not found";
            }
        } else {    // Root is a internal node
            HuffmanInternalNode tempRoot = (HuffmanInternalNode)(root);
            if(tempRoot.left() != null){
                left = recursiveEncoding(tempRoot, encoding + "0", target);
            }
            if(tempRoot.right() != null){
                right = recursiveEncoding(tempRoot, encoding + "1", target);
            }
        }
        if(right.contains("Not found")){return encoding}

    }

    public static void main(String[] args) {
        try {Zipper.zip(new File("mary.txt"), new File("save.txt"));}
        catch (IOException e) { e.printStackTrace();}
    }
}