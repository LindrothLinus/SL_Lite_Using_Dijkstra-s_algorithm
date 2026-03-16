package Project7_1;

public class Node implements Comparable<Node>{
    private Stop stop;
    private double gn; 
    private double hn;
    private Node parent;
    private Edge usedEdge;

    public Node(Stop stop, double gn, double hn,Node parent,Edge usedEdge){
        this.stop=stop;
        this.gn = gn;
        this.hn = hn;
        this.parent=parent;
        this.usedEdge = usedEdge;
    }

    public Stop getStop() {
        return stop;
    }


    public double getFn() {
        return gn+hn;
    }
    
    public double getGn() {
        return gn;
    }
    public double getHn() {
        return hn;
    }
 
    public Node getParent() {
        return parent;
    }

    public Edge getUsedEdge() {
        return usedEdge;
    }

    @Override
    public int compareTo(Node o) {
        if(getFn()<o.getFn()){
            return -1;
        }else if(getFn()>o.getFn()){
            return 1;
        }
        return 0;
    }


    @Override
    public String toString() {
        return stop.toString();
    }
}
