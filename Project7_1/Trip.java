package Project7_1;

import java.util.ArrayList;
import java.util.Objects;

public class Trip {
    private String serviceId;
    private Route route;
    private ArrayList<StopTime> stopTimes = new ArrayList<>();
    
    public Trip(String serviceId, Route route){
        this.serviceId = serviceId;
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }

    public ArrayList<StopTime> getStopTimes(){
        return stopTimes;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Trip t){
            return t.serviceId.equals(serviceId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId);
    }

    public void addStopTime(int index, StopTime stopTime){
        stopTimes.add(index, stopTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(route.getName()+": [");
        for (StopTime stopTime : stopTimes) {
            sb.append(stopTime.getStop()+", ");
        }
        return sb.append("]\n\n").toString();
    }
}
