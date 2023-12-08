import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.w3c.dom.Node;

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

        HashMap<String, Integer> encodingMap = new HashMap<>();
        Map<Character, String> huffmanCode = new HashMap<>();  
        encodeData(root, "", huffmanCode); 
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

    public static void encodeData(HuffmanBaseNode root, String str, Map<Character, String> huffmanCode)  
    {  
        if (root == null)   
        {  
            return;  
        }  
        //checks if the node is a leaf node or not  
        if (root.isLeaf())   
        {  
            HuffmanLeafNode tempRoot = (HuffmanLeafNode)root;
            huffmanCode.put(tempRoot.value(), str.length() > 0 ? str : "1");  
        }  
        encodeData(root.left, str + '0', huffmanCode);  
        encodeData(root.right, str + '1', huffmanCode);  
    } 

    private static void recursiveEncoding(HuffmanBaseNode root, HashMap<String, Integer> map, String target){
        if (root == null){
            return;
        }
        
        if(root.isLeaf()){
            HuffmanLeafNode tempRoot = (HuffmanLeafNode)root;
            map.put(tempRoot.value(), target.length() > 0 ? Integer.parseInt(target) : 1);
        }
    }

    public static void main(String[] args) {
        try {Zipper.zip(new File("mary.txt"), new File("save.txt"));}
        catch (IOException e) { e.printStackTrace();}
    }
}