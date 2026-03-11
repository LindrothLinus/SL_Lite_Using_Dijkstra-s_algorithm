package Project7_1;

import java.util.Objects;

public class Edge {
    Stop to;
    String depatrureTime;
    String arrivelTime;

    public Edge(Stop to, StopTime stopTime) {
        this.to = to;
        this.arrivelTime = stopTime.getArivelTime();
        this.depatrureTime = stopTime.getDepatureTime();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return to.toString() + " [" +arrivelTime + " - " + depatrureTime+"]";
    }

    // TODO hashcode?
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (obj instanceof Edge e) {
            return to.equals(e.to) && arrivelTime.equals(e.arrivelTime)&& depatrureTime.equals(e.depatrureTime);
        }
        return false;
    }

    public int getTravelCost() {
        return calculateTimeDiffrence(depatrureTime, arrivelTime);

    }

    public int getWaitingCost(Edge other){
        if(other == null){
            return 0;
        }
        return calculateTimeDiffrence(arrivelTime, other.depatrureTime);
    }

    private int calculateTimeDiffrence(String t1, String t2){
        String[] t1Times = t1.split(":");
        String[] t2Times = t2.split(":");

        int t1Sec = Integer.parseInt(t1Times[0]) * 3600 + Integer.parseInt(t1Times[1]) * 60
                + Integer.parseInt(t1Times[2]);
        int t2Sec = Integer.parseInt(t2Times[0]) * 3600 + Integer.parseInt(t2Times[1]) * 60
                + Integer.parseInt(t2Times[2]);

        int diffrence = t2Sec - t1Sec;
        if (diffrence < 0) {
            throw new IllegalArgumentException("Transportmedlet dykerupp innan det åkt (" + t1+" - " +t2+" = "+ (diffrence) + ")");
        }

        return diffrence;
    }

    public Stop getTo() {
        return to;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return Objects.hash(to,depatrureTime,arrivelTime);
    }
}
