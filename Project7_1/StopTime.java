package Project7_1;

public class StopTime {
    private String arivelTime;
    private String depatureTime;
    private Stop stop;

    public StopTime(String arivelTime, String departureTime, Stop stop){
        this.arivelTime = arivelTime;
        this.depatureTime = departureTime;
        this.stop = stop;
    }

    public Stop getStop(){
        return stop;
    }

    public String getArivelTime() {
        return arivelTime;
    }

    public String getDepatureTime() {
        return depatureTime;
    }
    

    /*public int travelTime(StopTime other){
        String[] thisTimes = depatureTime.split(":");
        String[] otherTimes = other.arivelTime.split(":");

        int thisTimeSec = Integer.parseInt(thisTimes[0]) * 3600 + Integer.parseInt(thisTimes[1]) * 60 + Integer.parseInt(thisTimes[2]);
        int otherTimeSec = Integer.parseInt(otherTimes[0]) * 3600 + Integer.parseInt(otherTimes[1]) * 60 + Integer.parseInt(otherTimes[2]);

        int diffrence = otherTimeSec-thisTimeSec;
        if(diffrence<0){
            throw new IllegalArgumentException("Transportmedlet dykerupp innan det åkt ("+(diffrence) +")");
        }

        return diffrence;

    }*/

    @Override
    public String toString() {
        return "(" + arivelTime+" : "+depatureTime+ " " + stop+")";
    }
}
