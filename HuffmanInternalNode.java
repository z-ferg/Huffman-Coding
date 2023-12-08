public class HuffmanInternalNode implements HuffmanBaseNode{
    private int weight;
    private HuffmanBaseNode left;
    private HuffmanBaseNode right;

    public HuffmanInternalNode(HuffmanBaseNode l, HuffmanBaseNode r, int w){
        left = l;
        right = r;
        weight = w;
    }

    public void setWeight(int w){
        this.weight = w;
    }

    public HuffmanBaseNode left(){
        return left;
    }

    public HuffmanBaseNode right(){
        return right;
    }

    public int weight(){
        return weight;
    }

    public boolean isLeaf(){
        return false;
    }

    @Override
    public int compareTo(Object o) {
        HuffmanBaseNode temp = (HuffmanBaseNode)(o);

        if(this.weight() == temp.weight()){return 0;}
        else if(this.weight() < temp.weight()){return -1;}
        else{return 1;}
    }
}
