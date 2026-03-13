package Project7_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Graph {
    private Map<String, Stop> stops = new HashMap<>();
    private Map<String, Trip> trips = new HashMap<>();
    private Map<String, Route> routes = new HashMap<>();
    private Map<Stop, Set<Edge>> roads = new HashMap<>();
    private Map<Stop, StopTime> stopTimes = new HashMap<>();

    // TODO: Load methoderna borde på någotsätt sättas ihop (Flera ctrl + V)
    public void loadStops(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            // TODO: Fult ändra?
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split(",");
                stops.put(lineParts[0], new Stop(lineParts[0], lineParts[1], Double.parseDouble(lineParts[2]),
                        Double.parseDouble(lineParts[3])));

            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public void loadTrips(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            // TODO: Fult ändra?
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split(",");
                trips.put(lineParts[2], new Trip(lineParts[1], lineParts[3], routes.get(lineParts[0])));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public void loadRoutes(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            // TODO: Fult ändra?
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split(",");
                routes.put(lineParts[0],
                        new Route(lineParts[2].equals("") ? lineParts[3] : lineParts[2], lineParts[4]));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public void loadEdges() {
        System.out.println("Inte implementerad");
    }

    public void loadTimes(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            // TODO: Fult ändra?
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split(",");
                trips.get(lineParts[0]).addStopTime(Integer.parseInt(lineParts[4]) - 1,
                        new StopTime(lineParts[1], lineParts[2], stops.get(lineParts[3])));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public Map<String, Stop> getStopsMap() {
        return stops;
    }

    public Map<String, Trip> getTripsMap() {
        return trips;
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

    public Map<Stop, Set<Edge>> getRoads() {
        return roads;
    }

    public void printRoads() {
        StringBuilder sb = new StringBuilder();
        for (Stop s : roads.keySet()) {
            sb.append(s + ": [");
            for (Edge e : roads.get(s)) {
                sb.append(e + ", ");
            }
            sb.append("] \n\n");
        }

        System.out.println(sb);
    }

    public Map<String, Route> getRoutes() {
        return routes;
    }

    public Map<Stop, String> findShotestPathWithId(String from, String to,String time) {
        return aStarSerch(stops.get(from), stops.get(to),time);
    }

    private Map<Stop, String> aStarSerch(Stop from, Stop to,String startTime) {
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

    private Map<Stop,String> fromatAnswer(Node lastNode){
        Map<Stop,String> answer = new LinkedHashMap<>();
        Node beforLast=null;
        answer.put(lastNode.getStop(), lastNode.getUsedEdge().getArrivelTime());
            while (lastNode.getParent() != null) {
                beforLast = lastNode;
                lastNode = lastNode.getParent();
                if (lastNode.getUsedEdge() != null) {
                    answer.put(lastNode.getStop(), lastNode.getUsedEdge().getArrivelTime());
                }
                else{
                    answer.put(lastNode.getStop(), beforLast.getUsedEdge().getDepatrureTime());
                }
            }
        return answer;
    }

}
