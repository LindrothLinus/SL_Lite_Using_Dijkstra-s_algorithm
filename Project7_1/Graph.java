package Project7_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.Consumer;

public class Graph {
    private Map<String, Stop> stops = new HashMap<>();
    private Map<String, Trip> trips = new HashMap<>();
    private Map<String, Route> routes = new HashMap<>();
    private Map<Stop, Set<Edge>> roads = new HashMap<>();

    private void readFile(String filePath,Consumer<String[]>lineParser){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split(",");
                lineParser.accept(lineParts);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

    }

    public void loadStops(String filePath){
        readFile(filePath, (String[] lineParts)->{
            stops.put(lineParts[0], new Stop(
                                    lineParts[0], 
                                    lineParts[1], 
                                    Double.parseDouble(lineParts[2]),
                                    Double.parseDouble(lineParts[3])));});
        
    }
    public void loadTrips(String filePath) {
        readFile(filePath, (String[] lineParts)->{
            trips.put(lineParts[2], new Trip(lineParts[1], routes.get(lineParts[0])));
        });
    }

    public void loadRoutes(String filePath) {
        readFile(filePath, (String[] lineParts)->{
            routes.put(lineParts[0],
            new Route(lineParts[2].equals("") ? lineParts[3] : lineParts[2], lineParts[4]));
        });
    }

    public void loadTimes(String filePath) {
        readFile(filePath, (String[] lineParts)->{
            trips.get(lineParts[0]).addStopTime(Integer.parseInt(lineParts[4]) - 1,
            new StopTime(lineParts[1], lineParts[2], stops.get(lineParts[3]),trips.get(lineParts[0])));
        });
    }

    public void connectStops() {
        for (String key : trips.keySet()) {
            Trip current = trips.get(key);
            ArrayList<StopTime> stopTimes = current.getStopTimes();
            for (int i = 0; i < stopTimes.size() - 1; i++) {
                Stop from = stopTimes.get(i).getStop();
                Stop to = stopTimes.get(i + 1).getStop();

                roads.putIfAbsent(to, new HashSet<>());
                roads.putIfAbsent(from, new HashSet<>());
                roads.get(to).add(new Edge(from, stopTimes.get(i), stopTimes.get(i + 1)));
                roads.get(from).add(new Edge(from, stopTimes.get(i), stopTimes.get(i + 1)));
            }
        }
    }


    public LinkedHashMap<Stop, String> findShotestPathWithId(String from, String to,String time) {
        /*Stop sFrom=null;
        Stop sTo = null;
        for(String key:stops.keySet()){
            Stop stop = stops.get(key);
            if(stop.getName().equals(from)){
                sFrom = stop;
            }
            if(stop.getName().equals(to)){
                sTo = stop;
            }

        }
        if(sFrom==null || sTo==null){
            throw new IllegalArgumentException(from+" eller " +to + "existerar ej"+sFrom+sTo);
        }*/
        return aStarSerch(stops.get(from), stops.get(to),time);
    }

    private LinkedHashMap<Stop, String> aStarSerch(Stop from, Stop to,String startTime) {
        PriorityQueue<Node> binaryHeap = new PriorityQueue<>();
        binaryHeap.add(new Node(from, 0, 0, null, null));

        Map<Stop, Double> visited = new HashMap<>();
        while (!binaryHeap.isEmpty()) {
            Node current = binaryHeap.poll();
            if (current.getStop().equals(to)) {
                return fromatAnswer(current);
            }

            for (Edge edge : roads.get(current.getStop())) {
                Stop stop = edge.getTo();
                double newCost;
                if (current.getUsedEdge() != null) {
                    newCost = current.getGn()+ edge.getTravelCost()+ edge.getWaitingCostFrom(current.getUsedEdge());
                } else {
                    newCost = current.getGn()+edge.getWaitingCostFrom(startTime)
                            + edge.getTravelCost();
                }
                if (!visited.containsKey(stop)|| visited.get(stop)>newCost) {
                    binaryHeap.add(new Node(stop,newCost,stop.calculateDistanceInTime(to), current, edge));
                    visited.put(stop, newCost);
                }

            }

        }
        return null;
    }

    private LinkedHashMap<Stop,String> fromatAnswer(Node lastNode){
        LinkedHashMap<Stop,String> answer = new LinkedHashMap<>();
        Node beforLast=null;
        String linje = getRouteNameFromNode(lastNode);
        answer.put(lastNode.getStop(), lastNode.getUsedEdge().getArrivelTime()+" Linje:"+linje);
            while (lastNode.getParent() != null) {
                beforLast = lastNode;
                lastNode = lastNode.getParent();
                linje = getRouteNameFromNode(beforLast);
                if (lastNode.getUsedEdge() != null && !getRouteNameFromNode(lastNode).equals(getRouteNameFromNode(beforLast))) {
                    answer.put(beforLast.getStop(), beforLast.getUsedEdge().getArrivelTime()+ " Linje:"+linje);
                }
                else if (lastNode.getUsedEdge()==null){
                    answer.put(lastNode.getStop(), beforLast.getUsedEdge().getDepatrureTime()+" Linje:"+linje);
                }
            }
        return answer;
    }

    private String getRouteNameFromNode(Node node){
        return node.getUsedEdge().getStopTime().getTrip().getRoute().getName();
    }

}
