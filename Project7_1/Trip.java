package Project7_1;

import java.util.ArrayList;
import java.util.Objects;

public class Trip {
    private String serviceId;
    private String headsign;
    private Route route;
    private ArrayList<StopTime> stopTimes = new ArrayList<>();
    
    public Trip(String serviceId, String headsign, Route route){
        this.serviceId = serviceId;
        this.headsign = headsign;
        this.route = route;
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

    //TODO: Dålig ta bort/ ändra
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(route.getName()+": [");
        for (StopTime stopTime : stopTimes) {
            sb.append(stopTime.getStop()+", ");
        }
        return sb.append("]\n\n").toString();
    }

    //TODO: borde kanske inte returnera katiska listan?
    public ArrayList<StopTime> getStopTimes(){
        return stopTimes;
    }

    public Route getRoute() {
        return route;
    }
}
