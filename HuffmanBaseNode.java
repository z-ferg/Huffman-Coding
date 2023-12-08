public interface HuffmanBaseNode extends Comparable {
    boolean isLeaf();
    int weight();
    int compareTo(Object o);
}