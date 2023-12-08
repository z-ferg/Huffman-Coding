public class HuffmanLeafNode implements HuffmanBaseNode, Comparable{
    private String element;
    private int weight;

    public HuffmanLeafNode(String el, int wt){
        element = el;
        weight = wt;
    }

    public String value(){
        return element;
    }

    public int weight(){
        return weight;
    }

    public boolean isLeaf(){
        return true;
    }

    public int compareTo(Object other){
        HuffmanBaseNode temp = (HuffmanBaseNode)(other);

        if(this.weight == temp.weight()){
            return 0;
        } else if(this.weight() < temp.weight()){
            return -1;
        } else {
            return 1;
        }
    }
}
