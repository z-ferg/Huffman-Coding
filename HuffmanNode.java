public class HuffmanNode implements Comparable{
    private int count;
    private String character;
    HuffmanNode left;
    HuffmanNode right;

    public String getCharacter(){
        return this.character;
    }

    public int getCount(){
        return this.count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public int compareTo(HuffmanNode that) {
        return this.count - that.count;
    }

    @Override
    public int compareTo(Object o) {throw new UnsupportedOperationException("Unimplemented method 'compareTo'");}
}
