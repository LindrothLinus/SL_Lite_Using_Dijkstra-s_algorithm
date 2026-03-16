package Project7_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();
        initGraph(g);
        System.out.print(g.findShotestPathWithId("740021647", "740012883", "08:30:00"));

        //System.out.println(g.getRoads());
    }

    private static void initGraph(Graph g){
        g.loadRoutes("Project7_1/sl_routes.txt");
        g.loadStops("Project7_1/sl_stops.txt");
        g.loadTrips("Project7_1/sl_trips.txt");
        g.loadTimes("Project7_1/sl_stop_times.txt");
        g.connectStops();
    }

    private static void startUserIteraction(Graph g){
        System.out.println("Från: \tTill: \tStarttid:  (. för nu)");
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = r.readLine();
            String[] inputs = input.split(",");
            printMap( g.findShotestPathWithId(inputs[0], inputs[1],inputs[2]));

        } catch (IOException e) {
            System.out.println("Problem med readern Försök igen....");
        }
    }

    private static void printMap(LinkedHashMap<Stop,String> map){
        StringBuilder sb = new StringBuilder();
        for(Stop stop:map.keySet()){
            sb.insert(0, stop+" "+map.get(stop) + "\n");
        }
        System.out.println(sb);
    }
}
