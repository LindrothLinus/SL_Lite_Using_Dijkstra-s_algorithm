package Project7_1;

import java.util.Objects;

public class Edge {
    Stop to;
    String depatrureTime;
    String arrivelTime;
    int cost;
    StopTime stopTime;

    public Edge(Stop to, StopTime stopTime, StopTime otherStopTime) {
        this.to = to;
        this.depatrureTime = stopTime.getDepatureTime();
        this.arrivelTime = otherStopTime.getArivelTime();
        cost = stopTime.travelTime(otherStopTime);
        this.stopTime = stopTime;

    }

    /*public Edge(Stop to, int cost){
        this.to = to;
        this.cost = cost;
    }*/
    public StopTime getStopTime() {
       return stopTime;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return to.toString() + " [" + depatrureTime + " - " + arrivelTime+"]";
    }

    // TODO hashcode?
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (obj instanceof Edge e) {
            return to.equals(e.to)&& arrivelTime.equals(e.arrivelTime)&& depatrureTime.equals(e.depatrureTime);
        }
        return false;
    }

    public int getTravelCost() {
        return cost;//calculateTimeDiffrence(depatrureTime, arrivelTime);

    }

    public int getWaitingCostFrom(String time){
        return calculateTimeDiffrence(time, depatrureTime);
    }

    public int getWaitingCostFrom(Edge other){
        if(other == null){
            throw new IllegalArgumentException("getWaitingCost: other är null.");
        }
        return calculateTimeDiffrence(other.arrivelTime, depatrureTime);
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
            diffrence += 86400;
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

    public String getArrivelTime() {
        return arrivelTime;
    }

    public String getDepatrureTime() {
        return depatrureTime;
    }
}
